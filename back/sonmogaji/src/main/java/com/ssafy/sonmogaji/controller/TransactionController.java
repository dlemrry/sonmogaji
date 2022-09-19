package com.ssafy.sonmogaji.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

// 각서 컨트롤러
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
	
	// 각서 확인
		public ResponseEntity<?> verify (@RequestPart(name = "file", required = true) MultipartFile file) throws Exception {
	
			try {
				
				return null;
			} catch (Exception e) {
				return exceptionHandling(e);
			}
			
		}
	
	
	// 공개된 각서 리스트 조회
		public ResponseEntity<?> readAllTransactions() throws Exception {
			try {
				
				return null;
			} catch (Exception e) {
				return exceptionHandling(e);
			}
		}
	
	// 내 각서 조회
		public ResponseEntity<?> readAllMyTransactions() throws Exception {
			try {
				return null;
			} catch (Exception e) {
				return exceptionHandling(e);
			}
		}
	
	// 각서 내용 조회
		public ResponseEntity<?> readOneTransaction() throws Exception {
			try {
				return null;
			} catch (Exception e) {
				return exceptionHandling(e);
			}
		}
	
	// 추억 이미지 조회
		public ResponseEntity<?> readMemoryImage() throws Exception {
			try {
				return null;
			} catch (Exception e) {
				return exceptionHandling(e);
			}
		}
		
	// 예외처리
		private ResponseEntity<String> exceptionHandling(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
