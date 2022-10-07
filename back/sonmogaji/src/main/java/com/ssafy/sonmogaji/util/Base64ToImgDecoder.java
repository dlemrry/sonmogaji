package com.ssafy.sonmogaji.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Component
public class Base64ToImgDecoder {
    public boolean decoder(String base64, String target){

        String data = base64.split(",")[1];

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);

        try {

            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));

            ImageIO.createImageInputStream(bufImg);

//            ImageIO.write(bufImg, "PNG", new File(target));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return false;
        }

        return true;

    }

}
