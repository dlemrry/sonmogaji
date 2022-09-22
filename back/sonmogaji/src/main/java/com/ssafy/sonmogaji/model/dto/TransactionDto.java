package com.ssafy.sonmogaji.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
	// tx 주소
	private String txAddress;

	// 각서 제목
	private String txTitle;

	// 각서 내용
	private String txContent;

	// 추억사진 파일이름
	private String imageTitle;

	// 추억사진 주소
	private String imageUrl;

	// 추억사진 비공개 여부
	private Boolean imageIsSecret;

	// 비공개 여부
	private Boolean txIsSecret;

	// 각서 생성날짜
	private LocalDate txCreateDate;

	// 각서 만료날짜
	private LocalDate txExpDate;

	// 각서 nft 주소
	private String txNftUrl;

}
