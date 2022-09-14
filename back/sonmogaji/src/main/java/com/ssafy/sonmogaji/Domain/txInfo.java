package com.ssafy.sonmogaji.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class txInfo {
    @Id
    @GeneratedValue
    private String tx_address;

    private String sign_address;

    private boolean isSecret;

    @Builder
    public txInfo(String tx_address, String sign_address, boolean isSecret) {
        this.tx_address = tx_address;
        this.sign_address = sign_address;
        this.isSecret = isSecret;
    }
}
