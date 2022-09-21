package com.ssafy.sonmogaji.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemorandumRoom {
    private int roomId;


    //roomIsStart의 default값은 false로 준다.
    private boolean roomIsStart = false;

    //roomIsClose의 default값은 false로 준다.
    private boolean roomIsClose = false;


}