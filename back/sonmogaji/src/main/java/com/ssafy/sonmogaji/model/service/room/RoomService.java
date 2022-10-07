package com.ssafy.sonmogaji.model.service.room;

import com.ssafy.sonmogaji.model.dto.RoomResponseDto;

public interface RoomService {


    // 방 참가 가능한지
    public RoomResponseDto isAvail(String roomId);


    public RoomResponseDto create( String senderNickname);
}
