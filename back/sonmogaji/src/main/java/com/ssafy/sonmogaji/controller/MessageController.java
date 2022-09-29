package com.ssafy.sonmogaji.controller;

import com.ssafy.sonmogaji.model.entity.room.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("*")
//@Profile("stomp")
public class MessageController {

    // 메세지를 보내는 양식을 지정해둔 template
    // 이걸 사용하면 편하게 메세지를 클라이언트쪽으로 보낼 수 있음.
    @Autowired
    private final SimpMessagingTemplate template;
    //	@Autowired
//	private RoomRepository roomRepository;
//
    //싱글톤 방 리스트
//    private static final List<Room> roomList = new LinkedList<>();
    @Autowired
    private RoomList roomList;


//    @MessageMapping(value = "/memorandum/create")
//    public void create(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
//        log.info(message.getSenderNickName() + " created room");
//        Room newroom = new Room(headerAccessor.getSessionId(),message.getSenderNickName());
//        //newroom.setHostSessionId(headerAccessor.getSessionId());
//        roomList.getRoomList().add(newroom);
//        template.convertAndSend("/sub/memorandum/join/" + message.getRoomCode(), message);
//
//    }
    @MessageMapping(value = "/memorandum/join")
    // headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
    public void join(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
//        log.info(message.getSenderNickName() + " join");
        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        log.info(message.getSenderNickName() + " join into " +r.getRoomCode());
        r.addParticipant(message.getSenderNickName(),headerAccessor.getSessionId());
        message.setRoomCode(message.getRoomCode());
        message.setMessage("joined" +message.getRoomCode());
        template.convertAndSend("/sub/memorandum/join/" + message.getRoomCode(), message);


    }

    @MessageMapping(value = "/memorandum/action")
    // headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
    public void action(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
        log.info(message.getSenderNickName() + " action");

        template.convertAndSend("/sub/memorandum/action/" + message.getRoomCode(), message);


    }


    // 클라이언트에서 메세지가 날라왔다.
//	@MessageMapping(value = "/memorandum")
//	// headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
//	public void memo(MemorandumMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
//		log.info(message.getType());
//		//이 방에서 게임하고있는 플레이어들
//		List<MemorandumParticipant> gpList = roomParticipantRepository.getMemorandumParticipant(message.getRoomId());
//
//		if (message.getType().equals("join")) {
//			message.setType("join");
//			template.convertAndSend("/memorandum" + message.getRoomId(), message);
//
//		}
//
//		// 게임이 시작버튼이 눌렸다.
//		if (message.getType().equals("vote")) {
//			message.setType("vote");
//			template.convertAndSend("/memorandum" + message.getRoomId(), message);
//
//		}
//
//
//
//	}

    //roomId는 방장 id로


    @MessageMapping("/chat/message")
    public void message(chatFormat message, SimpMessageHeaderAccessor headerAccessor) {
        Room r=roomList.findRoomByRoomCode(message.getRoomCode());
        r.addChatMessage(message.getSender(), message.getMessage() );
        log.info(message.getSender() + ": " +message.getMessage() + " , roomCode: " +message.getRoomCode());
        template.convertAndSend("/sub/chat/message/" + message.getRoomCode(), message);
    }

//
//	@MessageMapping(value = "/chat")
//	// headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
//	public void chat(MemorandumMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
//		log.info(message.getType());
//		//이 방에서 게임하고있는 플레이어들
//		List<MemorandumParticipant> gpList = roomParticipantRepository.getMemorandumParticipant(message.getRoomId());
//
//		if (message.getType().equals("join")) {
//			message.setType("join");
//			template.convertAndSend("/chat" + message.getRoomId(), message);
//
//		}
//
//
//		if (message.getType().equals("send")) {
//			String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
//
//			message.setType("send");
//			template.convertAndSend("/chat" + sessionId, message);
//
//		}
//
//
//
//	}

    //소켓 끊김 감지
    public void onDisconnectEvent(String sessionId) {

        boolean found = false;
        String userName = "";
        //모든 방 순회해서 해당 유저 찾아냄
        Room r= roomList.findRoomBySessionId(sessionId);


        for (int i = 0; i < r.getParticipants().size(); i++) {


                if (r.getParticipants().get(i).getSessionId().equals(sessionId)) {
                    Participant p = r.getParticipants().get(i);
                    found = true;
                    //방장이면 방 폭파
                    if (r.getHostSessionId().equals(sessionId)) {
                        roomList.deleteRoom(r);
//                        roomList.getRoomList().remove(i);
                    } else {
                        userName = r.getParticipants().get(i).getNickname();
                        r.getParticipants().remove(i);
                    }
                    break;
                }

            if (found) break;
        }


    }


}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class chatFormat {
    private String roomCode;
    private String sender;
    private String message;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class chatLogFormat {
    private String roomId;
    private String senderNickName;
    private List<ChatMessage> chatLog;
}
