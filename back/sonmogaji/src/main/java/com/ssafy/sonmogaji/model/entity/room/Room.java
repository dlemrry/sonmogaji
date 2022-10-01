package com.ssafy.sonmogaji.model.entity.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Log4j2
@Getter
@Setter
@RequiredArgsConstructor
public class Room {

    private String hostSessionId; //방장 sessionId 와 동일
    private String roomCode;
    private List<ChatMessage> chatLog;
    private List<Participant> Participants;
    private boolean isStart;

    private MemorandumState memorandumState;

    public Room(String nickname) {
        this.hostSessionId = "";
        this.chatLog = new LinkedList<>();
        this.isStart = false;
        this.Participants = new LinkedList<>();
        this.roomCode = "";
        this.memorandumState=new MemorandumState();

        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.roomCode += rd.nextInt(9);
        }
        log.info("room created : " + this.roomCode);
        //roomcode 랜덤 6자리

    }

    public Room(String nickname, String hostSessionId) {
        this.hostSessionId = hostSessionId;
        this.chatLog = new LinkedList<>();
        this.isStart = false;
        this.Participants = new LinkedList<>();
        this.roomCode = "";
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.roomCode += rd.nextInt(9);
        }
        log.info("room created : " + this.roomCode);

    }


    @PostConstruct
    private void init() {
        Participants = new ArrayList<>();
        chatLog = new ArrayList<>();
        isStart = false;
        hostSessionId = "";
        memorandumState=new MemorandumState();
    }

    public void addParticipant(String nickname, String sessionId) {
        Participant Participant = new Participant();
        Participant.setSessionId(sessionId);
        Participant.setNickname(nickname);

        this.Participants.add(Participant);
    }


    public void addChatMessage(String sender, String message) {

        chatLog.add(new ChatMessage(sender, message));

    }


    public boolean startRoom(String senderSessionId) {
        if(this.getHostSessionId().equals(senderSessionId)){
            this.isStart = true;
            return true;
        }

        return false;
    }


}
