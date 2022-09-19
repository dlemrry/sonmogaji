package com.ssafy.sonmogaji.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sonmogaji.model.dto.TransactionDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {@Override
	public String verifyTransaction(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionDto> readAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionDto> readAllMyTransaction(String memberAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionDto readOneTransaction(String txAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readMemoryImage(String txAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
