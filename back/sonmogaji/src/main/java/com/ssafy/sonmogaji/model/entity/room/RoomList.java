package com.ssafy.sonmogaji.model.entity.room;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Log4j2
public class RoomList {

    private static final List<Room> roomList = new LinkedList<>();

    public RoomList() {
//        roomList.add(new Room("testroom"));
    }

    public List<Room> getRoomList() {
        return this.roomList;
    }

    public Room findRoomByHostId(String hostSessionId) {
        for (int i = 0; i < this.roomList.size(); i++) {
            if (this.roomList.get(i).getHostSessionId().equals(hostSessionId)) {
                return this.roomList.get(i);
            }
        }
        return null;
    }

    public Room findRoomBySessionId(String SessionId) {
        for (int i = 0; i < this.roomList.size(); i++) {
            List<Participant> list = this.roomList.get(i).getParticipants();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSessionId().equals(SessionId)) {
                    return this.roomList.get(i);
                }
            }
        }

        return null;
    }

    public Room findRoomByRoomCode(String roomCode) {
        for (int i = 0; i < this.roomList.size(); i++) {
            if (this.roomList.get(i).getRoomCode().equals(roomCode)) {
                return this.roomList.get(i);
            }
        }
        return null;
    }


    public String deleteParticipant(String sessionId) {
//    	List<Participant> gp = getParticipants(roomId);
        Room r = findRoomBySessionId(sessionId);
        //boolean flag = false;
        if (r.getHostSessionId().equals(sessionId)) {

            //방장이면 방 폭파
            this.deleteRoom(sessionId);
            log.info("room " + r.getRoomCode() + " deleted");
            return "room deleted";
        }

        for (int i = 0; i < r.getParticipants().size(); i++) {
            //나간애면
            if (r.getParticipants().get(i).getSessionId().equals(sessionId)) {
                log.info("user " + r.getParticipants().get(i).getNickname() + " deleted");
                r.getParticipants().remove(r.getParticipants().get(i));
                List<Map<String,Boolean>> list = r.getMemorandumState().getAgree();

                for (int j = 0; j < list.size(); j++) {
                    list.get(j).remove(sessionId);

                }
                r.getMemorandumState().getSign().remove(sessionId);

                break;

            }
        }

        return "user deleted";
    }


    public boolean deleteRoom(String roomId) {
        for (int i = 0; i < this.getRoomList().size(); i++) {
            if (this.getRoomList().get(i).getHostSessionId().equals(roomId)) {
                this.getRoomList().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteRoom(Room room) {
        return this.getRoomList().remove(room);
    }

}
