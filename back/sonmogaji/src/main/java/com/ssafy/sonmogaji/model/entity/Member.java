package com.ssafy.sonmogaji.model.entity;

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
public class Member {
		
	// 멤버 지갑 주소
	@Id
	@Column(name = "member_address")
	private String memberAddress;
	
	// nonce
	@Column(name = "nonce")
	private Integer nonce;
	
	@Column(name = "role")
	private Enum role;

}
