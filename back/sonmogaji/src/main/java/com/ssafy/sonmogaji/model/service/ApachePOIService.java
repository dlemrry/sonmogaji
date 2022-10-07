package com.ssafy.sonmogaji.model.service;

import com.ssafy.sonmogaji.model.dto.TransactionDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ApachePOIService {
    // 미리보기 문서 만들기
    public BufferedImage createPreview(TransactionDto transactionDto,String sessionId) throws Exception;

    // 각서 이미지 만들기
    public String createImg(MultipartFile preview, String transactionAddress) throws IOException;


}
