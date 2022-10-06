package com.ssafy.sonmogaji.model.entity;

import javax.persistence.*;

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
public class Signee {
	
	//서명 id
	@Id
	@GeneratedValue
	@Column(name = "signee_id")
	private Long signeeID;

	// 사인한 멤버
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_address")
	private Member member;
	
	// 사인한 각서
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_address")
	private Transaction transaction;
	
}
