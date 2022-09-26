package com.ssafy.sonmogaji.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.ssafy.sonmogaji.model.entity.Signee;
import com.ssafy.sonmogaji.model.entity.Transaction;

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
	
	// 각서 서명자들
	private List<String> signees;

	public Transaction toTransaction() {
		return Transaction.builder()
				.txAddress(txAddress)
				.txTitle(txTitle)
				.txContent(txContent)
				.imageTitle(imageTitle)
				.imageUrl(imageUrl)
				.imageIsSecret(imageIsSecret)
				.txIsSecret(txIsSecret)
				.txCreateDate(txCreateDate)
				.txExpDate(txExpDate)
				.txNftUrl(txNftUrl)
				.build();
	}
	
	public Signee toSignee() {
		return Signee.builder()
				.signeeID(null)
				.member(null)
				.transaction(null)
				.build();
	}
	
}
