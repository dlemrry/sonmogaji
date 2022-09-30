package com.ssafy.sonmogaji.model.entity.room;

import lombok.Getter;
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
public class Room {

    private String hostSessionId; //방장 sessionId 와 동일
    private String roomCode;
    private List<ChatMessage> chatLog;
    private List<Participant> Participants;
    private boolean isStart;

    public Room( String nickname) {
        this.hostSessionId = "";
        this.chatLog=new LinkedList<>();
        this.isStart=false;
        this.Participants=new LinkedList<>();
        this.roomCode="";
        //this.Participants.add(new Participant(nickname,hostSessionId));
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.roomCode+=rd.nextInt(9);
        }
        log.info("room created : "+ this.roomCode);
        //roomcode 랜덤 6자리

    }
    public Room( String nickname,String hostSessionId) {
        this.hostSessionId = hostSessionId;
        this.chatLog=new LinkedList<>();
        this.isStart=false;
        this.Participants=new LinkedList<>();
        this.roomCode="";
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.roomCode+=rd.nextInt(9);
        }
        log.info("room created : "+ this.roomCode);

    }

//	public Room(String roomId){
//		this.roomId=roomId;
//		this.Participants = new ArrayList<>();
//		this.chatLog = new ArrayList<>();
//	}

    @PostConstruct
    private void init(){
        Participants = new ArrayList<>();
        chatLog = new ArrayList<>();
        isStart=false;
        hostSessionId ="";
    }

    public void addParticipant(String nickname, String sessionId) {
        Participant Participant = new Participant();
        Participant.setSessionId(sessionId);
        Participant.setNickname(nickname);



        this.Participants.add(Participant);
    }




    public void addChatMessage(String sender, String message){

        chatLog.add(new ChatMessage(sender,message));

    }


    public boolean startRoom(String senderSessionId){
        for (int i = 0; i < this.Participants.size(); i++) {
            if(this.Participants.get(i).getSessionId()==senderSessionId){
                if(this.getHostSessionId().equals(senderSessionId)){
                    this.isStart=true;
                    return true;
                }
            }
        }
        return false;
    }





}
