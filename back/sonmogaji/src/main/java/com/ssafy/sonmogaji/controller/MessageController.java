package com.ssafy.sonmogaji.controller;

import com.ssafy.sonmogaji.model.service.*;
import com.ssafy.sonmogaji.model.entity.*;
import com.ssafy.sonmogaji.model.repository.*;
import com.ssafy.sonmogaji.model.entity.MemorandumMessage;
import com.ssafy.sonmogaji.model.entity.MemorandumParticipant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Log4j2
/**
 * @MessageMapping을 통해 Websocket으로 들어오는 메시지 발행을 처리합니다. 클라이언트에서는 prefix를 붙여서
 *                  /pub/game/message로 발행 요청을 하면 Controller가 해당 메시지를 받아 처리합니다.
 *                  메시지가 발행되면 /sub/game/room/{roomId}로 메시지를 send 하는 것을 볼 수 있는데
 *                  클라이언트에서는 해당 주소를(/sub/game/room/{roomId}) 구독(subscribe)하고 있다가
 *                  메시지가 전달되면 화면에 출력하면 됩니다. 여기서 /sub/game/room/{roomId}는 채팅룸을
 *                  구분하는 값이므로 pub/sub에서 Topic의 역할이라고 보면 됩니다.
 */
//기존의 WebSocketHandler가 했던 역할을 대체한다!!
public class MessageController {

	// 메세지를 보내는 양식을 지정해둔 template
	// 이걸 사용하면 편하게 메세지를 클라이언트쪽으로 보낼 수 있음.
	@Autowired
	private final SimpMessagingTemplate template;
	@Autowired
	private RoomParticipantRepository roomParticipantRepository;


	// 클라이언트에서 메세지가 날라왔다.
	@MessageMapping(value = "/room/message")
	// headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
	public void message(MemorandumMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
		log.info(message.getType());
		//이 방에서 게임하고있는 플레이어들
		List<MemorandumParticipant> gpList = roomParticipantRepository.getMemorandumParticipant(message.getRoomId());

		if (message.getType().equals("join")) {

			template.convertAndSend("/sub/room" + message.getRoomId(), message);

		}

		// 게임이 시작버튼이 눌렸다.
		if (message.getType().equals("vote")) {
			template.convertAndSend("/sub/room" + message.getRoomId(), message);

		}



	}

	//소켓 끊김 감지
	public void onDisconnectEvent(String sessionId) {
		int roomId = roomParticipantRepository.findRoomBySesssionId(sessionId);

		// 방에 나가면 player를 한명 내려준다.

		// MemorandumParticipant에서 빼준다.
		//지운애가 방장이면 true 반환한다.
		boolean flag = roomParticipantRepository.deleteMemorandumParticipant(roomId, sessionId);

		List<MemorandumParticipant> gpList = roomParticipantRepository.getMemorandumParticipant(roomId);


	}//session나가기 함수

}

// todo 게임끝날때 그전에 사람이 누른거 메시지 보내주기 -> Message에 알려주고 승리자는 winnerIdx로 알려주기
// todo 게임끝났을때 unitBetting보다 돈 없는애 강퇴 -> 프론트에서 구현
// todo GameEnd 메시지에도 정보 넣어주기 -> 해결
// todo EXIT 메시지에 turnIdx 넣어주기 -> 해결
// todo 게임방 6명이상이면 못들어가게 -> 해결

// todo gameTotalBet 구하는 방식 변경 : 사람 나갔을 때 고려(GP에 totalBet만들기) -> 해결
// todo room에 현재인원 추가 -> 해결
// todo mission null값으로 넘어가는것 알려주기 -> 해결
// todo 돈 적은애가 콜 누르면 allIn으로 가게? 올인이 아직 완벽하지 않다..?