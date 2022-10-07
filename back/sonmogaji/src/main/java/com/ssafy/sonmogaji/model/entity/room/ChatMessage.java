package com.ssafy.sonmogaji.model.entity.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
//    private String roomId;

    private String sender;
    private String message;
//    private String type;

}
