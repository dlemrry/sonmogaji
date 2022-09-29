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

@Component
@Getter
public class RoomList{

    private static final List<Room> roomList = new LinkedList<>();

    public RoomList(){
        roomList.add(new Room("testroom"));
    }
    public List<Room> getRoomList (){
        return this.roomList;
    }

    public Room findRoomByRoomId(String roomId){
        for (int i = 0; i < this.roomList.size(); i++) {
            if(this.roomList.get(i).getRoomId().equals(roomId)){
                return this.roomList.get(i);
            }
        }
        return null;
    }


    public String deleteParticipant( String sessionId) {
//    	List<Participant> gp = getParticipants(roomId);
        Room r =findRoomByRoomId(sessionId);
        //boolean flag = false;

        for(int i = 0; i< r.getParticipants().size(); i++) {
            //나간애면
            if(r.getParticipants().get(i).getSessionId().equals(sessionId)) {
                if(r.getParticipants().get(i).getSessionId().equals(sessionId)){
                    //방 폭파
                    this.deleteRoom(sessionId);

                }else{
                    r.getParticipants().remove(r.getParticipants().get(i));
                    break;
                }


            }
        }

        return "deleted";
    }


    public boolean deleteRoom(String roomId) {
        for (int i = 0; i < this.getRoomList().size(); i++) {
            if(this.getRoomList().get(i).getRoomId().equals(roomId)){
                this.getRoomList().remove(i);
                return true;
            }
        }
        return false;
    }

}
