package com.ssafy.sonmogaji.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumMessage {
	private int roomId;
	private String senderNickName;
	private String socketId;
	private String message;
	private List<MemorandumParticipant> playerInfo;
	private String type;

}
