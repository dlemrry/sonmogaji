package com.ssafy.sonmogaji.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ssafy.sonmogaji.model.service.ApachePOIService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sonmogaji.model.dto.TransactionDto;
import com.ssafy.sonmogaji.model.entity.Member;
import com.ssafy.sonmogaji.model.service.TransactionService;

import lombok.RequiredArgsConstructor;

// 각서 컨트롤러
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;
    private final ApachePOIService apachePOIService;

    // 각서 확인
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestPart(name = "file", required = true) MultipartFile imgFile) throws Exception {

        try {
            return new ResponseEntity<String>(transactionService.verifyTransaction(imgFile), HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }

    // 각서 미리보기 이미지 만들기
    @PostMapping("/createPreview")
    public ResponseEntity<?> createPreview(@RequestPart TransactionDto transactionDto) throws Exception {
        try {
            apachePOIService.createPreview(transactionDto);
            return new ResponseEntity<String>("파일 생성!", HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    // 각서 이미지 만들기
    @PostMapping("/createImg")
    public ResponseEntity<?> createImg(@RequestPart(name = "file", required = false) MultipartFile preview, String txAddress) throws IOException {

            apachePOIService.createImg(preview, txAddress);
        return null;
    }

    // 각서 작성
    @PostMapping("/create")
    public ResponseEntity<?> createTx(
            @RequestPart TransactionDto transactionDto, @RequestPart(name = "file", required = false) MultipartFile file)
//				@RequestBody TransactionDto transactionDto, @RequestParam(name="file", required = false) MultipartFile file)
            throws Exception {

        try {
            transactionService.uploadFile(file);
            transactionService.writeTransaction(transactionDto, file);

            return new ResponseEntity<String>("tx is created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }


    // 공개된 각서 리스트 조회
    @GetMapping
    public ResponseEntity<?> readAllTransactions(@PageableDefault(size = 10, sort = "txCreateDate", direction = Direction.DESC) Pageable pageable) throws Exception {
        try {
//				transactionService.readAllTransactions(pageable);
            return new ResponseEntity<List<TransactionDto>>(transactionService.readAllTransactions(pageable), HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 내 각서 조회
    @GetMapping("/myTx/{walletAddress}")
    public ResponseEntity<?> readAllMyTransactions(@PageableDefault(size = 10, sort = "txCreateDate", direction = Direction.DESC) Pageable pageable, @PathVariable String walletAddress) throws Exception {
        try {

            List<TransactionDto> transactionDtos = transactionService.readAllMyTransaction(walletAddress, pageable);

            if(transactionDtos != null) {
                return new ResponseEntity<List<TransactionDto>>(transactionDtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 각서 내용 조회
    @GetMapping("/{txAddress}")
    public ResponseEntity<?> readOneTransaction(@PathVariable String txAddress) throws Exception {
        try {
//				transactionService.readOneTransaction(txAddress);
            return new ResponseEntity<TransactionDto>(transactionService.readOneTransaction(txAddress), HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 추억 이미지 조회
    @GetMapping("/{txAddress}/img")
    public ResponseEntity<?> readMemoryImage(@PathVariable String txAddress) throws Exception {
        try {
            transactionService.readMemoryImage(txAddress);
            return null;
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 예외처리
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
