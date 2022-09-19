package com.ssafy.sonmogaji.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

	// 로그인
	public ResponseEntity<?> login () throws Exception {
		
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
