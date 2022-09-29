package com.ssafy.sonmogaji.controller;


import com.ssafy.sonmogaji.model.dto.RoomRequestDto;
import com.ssafy.sonmogaji.model.dto.RoomResponseDto;
import com.ssafy.sonmogaji.model.entity.room.RoomList;
import com.ssafy.sonmogaji.model.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {



    private final RoomService roomService;

    @Autowired
    private RoomList roomList;


    @PostMapping("/isAvail")
    public ResponseEntity<?> isAvail (@RequestBody RoomRequestDto memoDto, HttpServletResponse response) throws Exception {

        try {
            RoomResponseDto responseDto= roomService.isAvail(memoDto.getRoomId());
            //LoginResponseDto responseDto = memberService.login(loginDto.getMemberAddress());
//			String token = memberService.login(memberAddress);
//			response.setHeader("authorization", "bearer " + token);

            return new ResponseEntity<RoomResponseDto>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }


    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
