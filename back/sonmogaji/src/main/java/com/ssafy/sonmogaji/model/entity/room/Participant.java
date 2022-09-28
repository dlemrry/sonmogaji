package com.ssafy.sonmogaji.model.entity.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클라이언트에게 보내주기 위한 정보들
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
//    private int roomId;
    private String nickname;
    private String sessionId;
    private String signUrl;
    private boolean isHost;

}
