package com.ssafy.sonmogaji.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Signees {
	
	//서명 id
	@Id
	@Column(name = "signee_id")
	private Long signeeID;

	// 사인한 멤버
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_address")
	private Member member;
	
	// 사인한 각서
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tx_address")
	private Transaction transaction;
	
}
