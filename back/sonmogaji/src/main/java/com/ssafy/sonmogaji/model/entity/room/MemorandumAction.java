package com.ssafy.sonmogaji.model.entity.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumAction {
    private String roomCode;
    private String senderNickName;
    private String senderSocketId;
    private String message;
    private String title;
    private String content;
    private String action;
    private Date expire;
    private boolean secret;
    private boolean memorySecret;
    private MemorandumState memorandumState;
//	private String type;

}