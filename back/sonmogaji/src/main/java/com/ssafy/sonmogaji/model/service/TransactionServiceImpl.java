package com.ssafy.sonmogaji.model.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sonmogaji.model.dto.SigneeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sonmogaji.model.dto.TransactionDto;
import com.ssafy.sonmogaji.model.entity.Member;
import com.ssafy.sonmogaji.model.entity.Signee;
import com.ssafy.sonmogaji.model.entity.Transaction;
import com.ssafy.sonmogaji.model.repository.MemberRepository;
import com.ssafy.sonmogaji.model.repository.SigneeRepository;
import com.ssafy.sonmogaji.model.repository.TransactionRepository;
import com.ssafy.sonmogaji.util.Steganographer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	private String uploadPath = File.separator + "app" + File.separator + "Feed";
	private String frontPath = File.separator + "sonmogaji" + File.separator + "Feed";

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
			
	
	private final TransactionRepository transactionRepository;
	private final SigneeRepository signeeRepository;
	private final MemberRepository memberRepository;
	private final Steganographer steganographer;
	private final AmazonS3 amazonS3;
	
	// 각서 인증하기
	@Override
	public String verifyTransaction(MultipartFile imgFile) {
		
		try {
			File file = new File(imgFile.getOriginalFilename());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(imgFile.getBytes());
			fos.close();
			String txHash = steganographer.decode(file);
			
			return txHash;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	// 각서 목록 조회
	@Override
	public List<TransactionDto> readAllTransactions(Pageable pageable) {
		List<TransactionDto> readTransactionDtos = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByTxIsSecret(false, pageable);
		
		for(Transaction transaction : transactions) {
			readTransactionDtos.add(transaction.toTransactionDto());
		}
		
		return readTransactionDtos;
	}

	// 유저 지갑 주소로 각서 목록 조회
	@Override
	public List<TransactionDto> readAllMyTransaction(String memberAddress, Pageable pageable) {
		
		Member member = memberRepository.findByMemberAddress(memberAddress).orElseGet(Member :: new);
		
		List<TransactionDto> readTransactionDtos = new ArrayList<>();
		List<Signee> signees = signeeRepository.findByMember(member);
		
		for(Signee signee : signees) {
			readTransactionDtos.add(transactionRepository.findByTxAddress(signee.getTransaction().getTxAddress()).get().toTransactionDto());
		}
		
		return readTransactionDtos;
	}

	// 각서 상세조회
	@Override
	public TransactionDto readOneTransaction(String txAddress) {
		
		Transaction transaction = transactionRepository.findByTxAddress(txAddress).orElseGet(Transaction :: new);
		
		return transaction.toTransactionDto();
	}

	// 추억사진 조회
	@Override
	public String readMemoryImage(String txAddress) {
		
		Transaction transaction = transactionRepository.findByTxAddress(txAddress).get();
		
		if(!transaction.getImageIsSecret()) {
			
			return transaction.toTransactionDto().getImageUrl();
		}
		
		return null;
	}

	// 추억 이미지 저장
	public String uploadFile(MultipartFile file) {
		String fileName = createFileName(file.getOriginalFilename());
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());


		try(InputStream inputStream = file.getInputStream()) {
			amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
		}

		return amazonS3.getUrl(bucket, fileName).toString();
	}

	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}

	private String getFileExtension(String fileName) {
		try{
			return  fileName.substring(fileName.lastIndexOf("."));
		} catch (StringIndexOutOfBoundsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일입니다");
		}
	}


	// 각서 정보 저장
	@Override
	public void writeTransaction(TransactionDto transactionDto, MultipartFile file) {
		
		// 각서 내용 저장
		if (!file.isEmpty()) {
			String fileType = file.getContentType();
			String OriginalName = file.getOriginalFilename();
			String fileName = OriginalName.substring(OriginalName.lastIndexOf('\\') + 1);
			String saveName = UUID.randomUUID().toString() + "_" + fileName;
			String savePath = uploadPath + File.separator + saveName;
			String frontSavePath = frontPath + File.separator + saveName;
			
			transactionDto.setImageTitle(OriginalName);
			transactionDto.setImageUrl(frontSavePath);

		}
		
		Transaction transaction = transactionDto.toTransaction();
		transactionRepository.save(transaction);
		
		// 각서 서명인들 저장
		List<SigneeDto> signeeDtos = transactionDto.getSignees();
		for(SigneeDto signeeDto : signeeDtos) {
			Member member = memberRepository.findByMemberAddress(signeeDto.getMemberAddress()).orElseGet(Member :: new);
			Signee signee = Signee.builder().signeeID(0L).member(member).transaction(transaction).build();
			signeeRepository.save(signee);
		}
		
		
		
	}

}
