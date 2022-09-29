package com.ssafy.sonmogaji.model.service.room;

import com.ssafy.sonmogaji.model.dto.RoomResponseDto;
import com.ssafy.sonmogaji.model.entity.room.RoomList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomList roomList;

	@Override
	public RoomResponseDto isAvail(String roomId) {
	
		if(roomList.findRoomByRoomId(roomId)==null) {
			return new RoomResponseDto(roomId, "no");

		}

		return new RoomResponseDto(roomId, "yes");
	}

}
