package com.ssafy.sonmogaji.model.service.room;

import com.ssafy.sonmogaji.model.dto.RoomResponseDto;
import com.ssafy.sonmogaji.model.entity.room.Room;
import com.ssafy.sonmogaji.model.entity.room.RoomList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomList roomList;

	@Override
	public RoomResponseDto isAvail(String roomCode) {

		Room r = roomList.findRoomByRoomCode(roomCode);
		if(r==null) {
			return new RoomResponseDto(roomCode, "no");

		}

		return new RoomResponseDto(r.getRoomCode(), "yes");
	}

	@Override
	public RoomResponseDto create(String senderNickname) {
		log.info(senderNickname + " created room");
		Room newroom = new Room(senderNickname);
		//newroom.setHostSessionId(headerAccessor.getSessionId());
		roomList.getRoomList().add(newroom);
		return new RoomResponseDto(newroom.getRoomCode(), "created");
	}

}
