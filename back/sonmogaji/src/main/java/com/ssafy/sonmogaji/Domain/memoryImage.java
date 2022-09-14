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
public class memoryImage {
    @Id
    @GeneratedValue
    private Long image_id;

    private String image_url;

    private String image_name;

    private String tx_hash;

    @Builder
    public memoryImage(Long image_id, String image_url, String image_name, String tx_hash) {
        this.image_id = image_id;
        this.image_url = image_url;
        this.image_name = image_name;
        this.tx_hash = tx_hash;
    }
}
