package com.ssafy.sonmogaji.model.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sonmogaji.model.dto.TransactionDto;
import com.ssafy.sonmogaji.model.entity.Member;

public interface TransactionService {
	
	// 각서 검증
	public String verifyTransaction(MultipartFile file);
	
	// 공개된 각서 목록 조회
	public List<TransactionDto> readAllTransactions(Pageable pageable);
	
	// 내 각서 목록 조회
	public List<TransactionDto> readAllMyTransaction(String memberAddress, Pageable pageable);
	
	// 각서 내용 조회
	public TransactionDto readOneTransaction(String txAddress);
	
	// 추억 이미지 보기
	public String readMemoryImage(String txAddress);
	
	// 각서 작성
	public void writeTransaction(TransactionDto transactionDto, MultipartFile file);

	//각서 이미지 저장
	public String uploadFile(MultipartFile file);



}
