package com.ssafy.sonmogaji.model.service;

import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;
import com.ssafy.sonmogaji.model.dto.TransactionDto;
import com.ssafy.sonmogaji.util.Base64ToImgDecoder;
import com.ssafy.sonmogaji.util.Steganographer;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApachePOIServiceImpl implements ApachePOIService{

//    private static final String PATH = "classpath:/static/";

    private final Base64ToImgDecoder base64ToImgDecoder;
    private final Steganographer steganographer;



    @Override
    public BufferedImage createPreview(TransactionDto transactionDto) throws Exception {
        String sample = "memorandom.docx";
        FileOutputStream fos = null;

        // 각서 원본 docx 파일 생성
        try {
            // 각서 샘플파일 복사하기
            File file = new File(sample);
            File newFile = new File("memorandom_preview.docx");

            Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            XWPFDocument doc = new XWPFDocument(new FileInputStream(newFile));
            // 본문 입력하기
            for(XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if(runs != null) {
                    for(XWPFRun r : runs) {
                        String text = r.getText(0);
                        if(text != null && text.contains("제목")) {
                            text = text.concat(transactionDto.getTxTitle());
                            r.setText(text, 0);
                        }
                        if(text != null && text.contains("내용")) {
                            text = text.concat(transactionDto.getTxContent());
                            r.setText(text, 0);
                        }
                        if(text != null && text.contains("날짜")) {
                            text = text.concat(transactionDto.getTxCreateDate().toString());
                            r.setText(text, 0);
                        }
                    }
                }
            }

            XWPFTable table = null;
            // 테이블 요소 구하기
            Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();
            while(docElementsIterator.hasNext()) {
                IBodyElement docElement = docElementsIterator.next();

                if("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                    List<XWPFTable> xwpfTableList = docElement.getBody().getTables();

                    table = xwpfTableList.get(0);
                }
            }

            for(int i = 0; i<transactionDto.getSignees().size() -2 ; i++) {
                table.createRow();
            }

            for(int i = 0; i < transactionDto.getSignees().size(); i++ ) {
                XWPFTableRow row = table.getRow(i);
                row.getCell(0).setText(transactionDto.getSignees().get(i).getMemberAddress());

                // 사인 이미지 넣기
                String data = transactionDto.getSignees().get(i).getSignBase64().split(",")[1];
                byte[] imgBytes = DatatypeConverter.parseBase64Binary(data);
                BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));

                InputStream is = new ByteArrayInputStream(imgBytes);



                int imgType = XWPFDocument.PICTURE_TYPE_PNG;
                String imgFileName = transactionDto.getSignees().get(i).getSigneeName();
                int width = 200;
                int height = 100;

                XWPFParagraph paragraph = doc.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.addPicture(is, imgType, imgFileName,Units.toEMU(width), Units.toEMU(height));

                row.getCell(1).setParagraph(paragraph);

            }

            fos = new FileOutputStream(new File("memorandom_preview.docx"));
            doc.write(fos);

            if(fos != null) fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 각서 이미지로 변환하기
        File file = new File("memorandom_preview.docx");

        Document document = new Document();
        document.loadFromFile("memorandom_preview.docx");

        BufferedImage image = document.saveToImages(0, ImageType.Bitmap);

        return image;

//        File imgFile = new File("Preview.PNG");
//        ImageIO.write(image, "PNG", imgFile);
    }

    @Override
    public File createImg(MultipartFile file, String transactionAddress) throws IOException {

        File preview = new File(file.getOriginalFilename());
        file.transferTo(preview);
        steganographer.encode(preview, transactionAddress);

        return null;
    }
}
