package com.ssafy.sonmogaji.model.entity.room;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Log4j2
@Getter
@Setter
public class Room {

    private String roomId; //방장 sessionId 와 동일
    private List<ChatMessage> chatLog;
    private List<Participant> Participants;
    private boolean isStart;

    public Room(String roomId) {
        this.roomId = roomId;
        this.chatLog=new LinkedList<>();
        this.isStart=false;
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
        roomId="";
    }

    public void addParticipant(MemorandumAction message, String sessionId) {
        Participant Participant = new Participant();
        Participant.setSessionId(sessionId);
        Participant.setNickname(message.getSenderNickName());



        Participants.add(Participant);
    }




    public void addChatMessage(String sender, String message){

        chatLog.add(new ChatMessage(sender,message));

    }


    public boolean startRoom(String senderSessionId){
        for (int i = 0; i < this.Participants.size(); i++) {
            if(this.Participants.get(i).getSessionId()==senderSessionId){
                if(this.getRoomId().equals(senderSessionId)){
                    this.isStart=true;
                    return true;
                }
            }
        }
        return false;
    }





}
