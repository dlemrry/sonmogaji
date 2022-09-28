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

    private String roomId;
    private List<ChatMessage> chatLog;
    private List<Participant> Participants;

    public Room(String roomId) {
        this.roomId = roomId;
        this.chatLog=new LinkedList<>();
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

        roomId="";
    }

    public void addParticipant(MemorandumAction message, String sessionId) {
        Participant Participant = new Participant();
        Participant.setSessionId(sessionId);
        Participant.setNickname(message.getSenderNickName());
        //방장이면
        if(roomId.equals(sessionId)){
            Participant.setHost(true);
        }
        else{
            Participant.setHost(false);
        }


        Participants.add(Participant);
    }



    public boolean deleteParticipant(int roomId, String sessionId) {
//    	List<Participant> gp = getParticipants(roomId);

        boolean flag = false;

        for(int i = 0; i< Participants.size(); i++) {
            //나간애면
            if(Participants.get(i).getSessionId().equals(sessionId)) {

                Participants.remove(Participants.get(i));
            }
        }

        return flag;
    }

    public void addChatMessage(String sender, String message){

        chatLog.add(new ChatMessage(sender,message));

    }








}
