package com.ssafy.sonmogaji.model.service;

import com.ssafy.sonmogaji.model.dto.TransactionDto;

import java.io.File;

public interface ApachePOIService {
    // 미리보기 문서 만들기
    public void createPreview(TransactionDto transactionDto) throws Exception;

    // 각서 이미지 만들기
    public File createImg(File preview, String transactionAddress);


}
