package com.ssafy.sonmogaji.controller;


import com.ssafy.sonmogaji.model.dto.RoomRequestDto;
import com.ssafy.sonmogaji.model.dto.RoomResponseDto;
import com.ssafy.sonmogaji.model.entity.room.Room;
import com.ssafy.sonmogaji.model.entity.room.RoomList;
import com.ssafy.sonmogaji.model.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Log4j2
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoomController {


    private final RoomService roomService;

    @Autowired
    private RoomList roomList;


    @PostMapping("/isAvail")
    public ResponseEntity<?> isAvail(@RequestBody RoomRequestDto memoDto, HttpServletResponse response) throws Exception {

        try {
            RoomResponseDto responseDto = roomService.isAvail(memoDto.getRoomCode());

            return new ResponseEntity<RoomResponseDto>(responseDto, HttpStatus.OK);

        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoomRequestDto memoDto, HttpServletResponse response) throws Exception {

        try {
            RoomResponseDto responseDto = roomService.create(memoDto.getSenderNickName());


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
