package com.ssafy.sonmogaji.model.entity.room;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Getter
public class RoomList{

    private static final List<Room> roomList = new LinkedList<>();

    public RoomList(){
//        roomList.add(new Room("testroom"));
    }
    public List<Room> getRoomList (){
        return this.roomList;
    }

    public Room findRoomByHostId(String hostSessionId){
        for (int i = 0; i < this.roomList.size(); i++) {
            if(this.roomList.get(i).getHostSessionId().equals(hostSessionId)){
                return this.roomList.get(i);
            }
        }
        return null;
    }
    public Room findRoomBySessionId(String SessionId){
        for (int i = 0; i < this.roomList.size(); i++) {
            List<Participant> list = this.roomList.get(i).getParticipants();
            for (int j = 0; j < list.size(); j++) {
                if(list.get(j).getSessionId().equals(SessionId)){
                    return this.roomList.get(i);
                }
            }
        }

        return null;
    }
    public Room findRoomByRoomCode(String roomCode){
        for (int i = 0; i < this.roomList.size(); i++) {
            if(this.roomList.get(i).getRoomCode().equals(roomCode)){
                return this.roomList.get(i);
            }
        }
        return null;
    }


    public String deleteParticipant( String sessionId) {
//    	List<Participant> gp = getParticipants(roomId);
        Room r =findRoomByHostId(sessionId);
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
            if(this.getRoomList().get(i).getHostSessionId().equals(roomId)){
                this.getRoomList().remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean deleteRoom(Room room) {
        return this.getRoomList().remove(room);
//        for (int i = 0; i < this.getRoomList().size(); i++) {
//            if(this.getRoomList().get(i).getHostSessionId().equals(roomId)){
//                this.getRoomList().remove(i);
//                return true;
//            }
//        }
//        return false;
    }

}
