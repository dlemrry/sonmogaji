package com.ssafy.sonmogaji.model.repository;

import com.ssafy.sonmogaji.model.entity.MemorandumMessage;
import com.ssafy.sonmogaji.model.entity.MemorandumParticipant;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class RoomParticipantRepository {

	private List<MemorandumParticipant> MemorandumParticipantMap;


    @PostConstruct
    private void init(){
    	MemorandumParticipantMap = new ArrayList<MemorandumParticipant>();
    }
    
    public void addMemorandumParticipant(MemorandumMessage message, String sessionId) {
    	MemorandumParticipant MemorandumParticipant = new MemorandumParticipant();
    	MemorandumParticipant.setRoomId(message.getRoomId());
    	MemorandumParticipant.setSessionId(sessionId);

		MemorandumParticipantMap.add(MemorandumParticipant);
	}


	public int findRoomBySesssionId(String sessionId) {
		int roomid=-1;
		for (int i=0; i<MemorandumParticipantMap.size(); i++){
			System.out.println(MemorandumParticipantMap.get(i).getSessionId());
			System.out.println(sessionId);
			if(MemorandumParticipantMap.get(i).getSessionId()==sessionId){
				roomid= MemorandumParticipantMap.get(i).getRoomId();
			}
		}
		return roomid;
	}
    
    public boolean deleteMemorandumParticipant(int roomId, String sessionId) {
    	List<MemorandumParticipant> gp = getMemorandumParticipant(roomId);
    	
    	boolean flag = false;
    	
    	for(int i=0; i<gp.size(); i++) {
    		//나간애면
    		if(gp.get(i).getSessionId().equals(sessionId)) {

    			MemorandumParticipantMap.remove(gp.get(i));
    		}
    	}
    	
    	return flag;
    }

    public List<MemorandumParticipant> getMemorandumParticipant(int roomId){
    	List<MemorandumParticipant> gp = new ArrayList<MemorandumParticipant>();
    	for(MemorandumParticipant MemorandumParticipant : MemorandumParticipantMap) {
    		if(MemorandumParticipant.getRoomId()==roomId) {
    			gp.add(MemorandumParticipant);
    		}
    	}
    	return gp;
    }

	//CALL 베팅하기 - call하려면 몇개 베팅해야되는지 돌려주자
	public int callBetting(int roomId, String sessionId){
		List<MemorandumParticipant> MemorandumParticipantList = getMemorandumParticipant(roomId);
		int callBettingCnt = 0;

		return callBettingCnt;
	}


}
