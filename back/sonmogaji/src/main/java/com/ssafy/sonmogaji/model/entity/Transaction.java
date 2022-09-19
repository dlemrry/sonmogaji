package com.ssafy.sonmogaji.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Transaction {

	// tx 주소
	@Id
	@Column(name = "tx_address")
	private String txAddress;
	
	// 각서 제목
	@Column(name = "tx_title")
	private String txTitle;
	
	// 각서 내용
	@Column(name = "tx_content")
	private String txContent;
	
	// 추억사진 파일이름
	@Column(name = "image_title")
	private String imageTitle;
	
	// 추억사진 주소
	@Column(name = "image_url")
	private String imageUrl;
	
	// 추억사진 비공개 여부
	@Column(name = "image_is_secret")
	private Boolean imageIsSecret;
	
	// 비공개 여부
	@Column(name = "is_secret")
	private Boolean isSecret;
	
	// 각서 생성날짜
	@Column(name = "tx_create_date")
	private LocalDate txCreateDate;
	
	// 각서 만료날짜
	@Column(name = "tx_exp_date")
	private LocalDate txExpDate;
	
	// 각서 nft 주소
	@Column(name = "tx_nft_url")
	private String txNftUrl;
	
}
