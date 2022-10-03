package com.ssafy.sonmogaji.model.dto;

import com.ssafy.sonmogaji.model.entity.Signee;
import lombok.Data;

@Data
public class SigneeDto {
	private Long signeeId;
	private String memberAddress;
	private String txAddress;
	private String signeeName;
	private String signBase64;

}
