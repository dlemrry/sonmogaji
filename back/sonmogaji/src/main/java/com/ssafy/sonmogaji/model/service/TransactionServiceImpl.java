package com.ssafy.sonmogaji.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
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

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	private String uploadPath = File.separator + "app" + File.separator + "Feed";
	private String frontPath = File.separator + "sonmogaji" + File.separator + "Feed";
			
	
	private final TransactionRepository transactionRepository;
	private final SigneeRepository signeeRepository;
	private final MemberRepository memberRepository;
	private final Steganographer steganographer;
	
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
		List<String> signeeDtos = transactionDto.getSignees(); 
		for(String signeeDto : signeeDtos) {
			Member member = memberRepository.findByMemberAddress(signeeDto).orElseGet(Member :: new);
			Signee signee = Signee.builder().member(member).transaction(transaction).build();
			signeeRepository.save(signee);
		}
		
		
		
	}

}
