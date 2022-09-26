package com.ssafy.sonmogaji.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumAction {
	private int roomId;
	private String senderNickName;
	private String senderSocketId;
	private String message;
	private String action;
//	private String type;

}

//class MemorandumAction{
//	private String message;
//	private String action;
//
//}