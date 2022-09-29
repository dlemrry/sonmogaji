package com.ssafy.sonmogaji.model.entity.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumAction {
    private String roomCode;
    private String senderNickName;
    private String senderSocketId;
    private String message;
    private String action;
//	private String type;

}