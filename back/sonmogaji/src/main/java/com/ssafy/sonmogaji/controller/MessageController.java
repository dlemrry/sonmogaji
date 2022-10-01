package com.ssafy.sonmogaji.controller;

import com.ssafy.sonmogaji.model.entity.room.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.web3j.abi.datatypes.Bool;
import org.web3j.crypto.Hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("*")
public class MessageController {

    // 메세지를 보내는 양식을 지정해둔 template
    // 이걸 사용하면 편하게 메세지를 클라이언트쪽으로 보낼 수 있음.
    @Autowired
    private final SimpMessagingTemplate template;
    @Autowired
    private RoomList roomList;


    @MessageMapping(value = "/memorandum/join")
    // headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
    public void join(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
//        log.info(message.getSenderNickName() + " join");
        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        if (r.getParticipants().size() < 1) {
            r.setHostSessionId(headerAccessor.getSessionId());
        }
        if (r.isStart()) {
            message.setRoomCode(message.getRoomCode());
            message.setMessage("no" + message.getRoomCode());
            template.convertAndSendToUser(headerAccessor.getSessionId(), "/sub/memorandum/join/" + message.getRoomCode(), message);
        } else {
            log.info(message.getSenderNickName() + " join into " + r.getRoomCode());
            log.info(headerAccessor.getSessionId());
            r.addParticipant(message.getSenderNickName(), headerAccessor.getSessionId());
            MemorandumState state=r.getMemorandumState();
//            HashMap<String,Boolean>[] agreearray=state.getAgree();
            List<Map<String, Boolean>> agreelist = state.getAgree();
//            HashMap<String, Boolean> agree = r.getMemorandumState().getAgree().get(0);
//            log.info(agreelist.get(0));
            agreelist.get(0).put(headerAccessor.getSessionId(),false);
            agreelist.get(1).put(headerAccessor.getSessionId(),false);
            agreelist.get(2).put(headerAccessor.getSessionId(),false);
            agreelist.get(3).put(headerAccessor.getSessionId(),false);
            agreelist.get(4).put(headerAccessor.getSessionId(),false);
            agreelist.get(5).put(headerAccessor.getSessionId(),false);

            r.getMemorandumState().getSign().put(headerAccessor.getSessionId(), "");
            message.setRoomCode(message.getRoomCode());
            message.setMemorandumState(r.getMemorandumState());
            message.setMessage("ok");
            template.convertAndSend("/sub/memorandum/join/" + message.getRoomCode(), message);
        }
    }

    @MessageMapping(value = "/memorandum/action")
    // headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
    public void action(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
        log.info(message.getSenderNickName() + " action");

        template.convertAndSend("/sub/memorandum/action/" + message.getRoomCode(), message);

    }

    @MessageMapping(value = "/memorandum/start")
    // headerAccessor는 소켓서버의 주인ID를 확인하기 위해서 사용
    public void start(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {

        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        if (r.startRoom(headerAccessor.getSessionId())) {
            log.info(message.getRoomCode() + " start");
            message.setMessage("start");
            message.setMemorandumState(r.getMemorandumState());
            template.convertAndSend("/sub/memorandum/start/" + message.getRoomCode(), message);
        } else {
            message.setMessage("you are not host");
            template.convertAndSendToUser(headerAccessor.getSessionId(), "/sub/memorandum/start/" + message.getRoomCode(), message);
        }
    }


    @MessageMapping(value = "/memorandum/vote")
    public void vote(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {

        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        if (r.getHostSessionId().equals(headerAccessor.getSessionId())) {
            message.setMessage("you are host");
            template.convertAndSendToUser(headerAccessor.getSessionId(), "/sub/memorandum/vote/" + message.getRoomCode(), message);

        } else {
//            HashMap<String, Boolean> agree = r.getMemorandumState().getAgree().get(Integer.parseInt(message.getMessage()));
//            agree.put(headerAccessor.getSessionId(), !agree.get(headerAccessor.getSessionId()));
            int index=Integer.parseInt(message.getMessage());
            Map<String, Boolean> agree = r.getMemorandumState().getAgree().get(index);
            agree.put(headerAccessor.getSessionId(), !agree.get(headerAccessor.getSessionId()));
            message.setMemorandumState(r.getMemorandumState());
            message.setMessage(Integer.toString(index+1));
            template.convertAndSend("/sub/memorandum/vote/" + message.getRoomCode(), message);
        }

    }

    @MessageMapping(value = "/memorandum/next")
    public void next(MemorandumAction message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {

        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        if (!r.getHostSessionId().equals(headerAccessor.getSessionId())) {
            message.setMessage("you are not host");
            template.convertAndSendToUser(headerAccessor.getSessionId(), "/sub/memorandum/vote/" + message.getRoomCode(), message);

        } else {
//            HashMap<String, Boolean> agree = r.getMemorandumState().getAgree().get(Integer.parseInt(message.getMessage()));
//            agree.put(headerAccessor.getSessionId(), !agree.get(headerAccessor.getSessionId()));

            //Map<String, Boolean> agree = r.getMemorandumState().getAgree().get(Integer.parseInt(message.getMessage()));
            //agree.put(headerAccessor.getSessionId(), !agree.get(headerAccessor.getSessionId()));
            int index= Integer.parseInt(message.getMessage())-1;
            Map<String, Boolean> agreemap = r.getMemorandumState().getAgree().get(index);
            int participantCount=r.getParticipants().size();
            int count=0;
            for( Map.Entry<String, Boolean> elem : agreemap.entrySet() ){
                if(elem.getValue()){
                    count++;
                }
            }

            if(count==(participantCount-1)){
                message.setMemorandumState(r.getMemorandumState());
                message.setMessage(Integer.toString(index+2));
                template.convertAndSend("/sub/memorandum/next/" + message.getRoomCode(), message);
            }
            else{
                message.setMessage("not agreed");
                template.convertAndSendToUser(headerAccessor.getSessionId(), "/sub/memorandum/vote/" + message.getRoomCode(), message);

            }


        }

    }


    @MessageMapping("/chat/message")
    public void message(chatFormat message, SimpMessageHeaderAccessor headerAccessor) {
        Room r = roomList.findRoomByRoomCode(message.getRoomCode());
        r.addChatMessage(message.getSender(), message.getMessage());
        log.info(message.getSender() + ": " + message.getMessage() + " , roomCode: " + message.getRoomCode());
        template.convertAndSend("/sub/chat/message/" + message.getRoomCode(), message);
    }


    //소켓 끊김 감지
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        log.info("disconnect : " + sessionId);
        log.info(roomList.deleteParticipant(sessionId));

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
