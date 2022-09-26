package com.ssafy.sonmogaji.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
@Getter
@Setter
public class Room {

	private String roomId;
	private List<ChatMessage> chatLog;
	private List<Participant> Participants;

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


//	public int findRoomBySesssionId(String sessionId) {
//		int roomid=-1;
//		for (int i=0; i<ParticipantMap.size(); i++){
//			System.out.println(ParticipantMap.get(i).getSessionId());
//			System.out.println(sessionId);
//
//		}
//		return roomid;
//	}
    
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
