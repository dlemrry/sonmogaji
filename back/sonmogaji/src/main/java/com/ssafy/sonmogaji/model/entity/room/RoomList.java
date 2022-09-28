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
        return roomList;
    }

    public Room findRoomByRoomId(String roomId){
        for (int i = 0; i < roomList.size(); i++) {
            if(roomList.get(i).getRoomId().equals(roomId)){
                return roomList.get(i);
            }
        }
        return null;
    }

}
