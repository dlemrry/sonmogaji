package com.ssafy.sonmogaji.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sonmogaji.model.dto.TransactionDto;
import com.ssafy.sonmogaji.model.entity.Signee;
import com.ssafy.sonmogaji.model.entity.Transaction;
import com.ssafy.sonmogaji.model.reopository.SigneeRepository;
import com.ssafy.sonmogaji.model.reopository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final SigneeRepository signeeRepository;
	
	// 각서 인증하기
	@Override
	public String verifyTransaction(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
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
	public List<TransactionDto> readAllMyTransaction(String memberAddress) {
		List<TransactionDto> readTransactionDtos = new ArrayList<>();

		List<Signee> signees = signeeRepository.findByMemberMemberAddress(memberAddress);
		
		for(Signee signee : signees) {
			readTransactionDtos.add(transactionRepository.findByTxAddress(signee.getTransaction().getTxAddress()).get().toTransactionDto());
		}
		
		return readTransactionDtos;
	}

	// 각서 상세조회
	@Override
	public TransactionDto readOneTransaction(String txAddress) {
		
		Transaction transaction = transactionRepository.findByTxAddress(txAddress).get();
		
		if(transaction == null ) {
			return null;
		}
		
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

}
