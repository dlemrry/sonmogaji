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

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
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

        try {
            File file = new File(sample);

            XWPFDocument doc = new XWPFDocument(new FileInputStream(file));

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

            fos = new FileOutputStream(new File(sample));
            doc.write(fos);

            if(fos != null) fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 각서 이미지로 변환하기
        File file = new File(sample);

        Document document = new Document();
        document.loadFromFile(sample);

        BufferedImage image = document.saveToImages(0, ImageType.Bitmap);

        return image;

//        File imgFile = new File("Preview.PNG");
//        ImageIO.write(image, "PNG", imgFile);
    }

    @Override
    public File createImg(File preview, String transactionAddress) {

        steganographer.encode(preview, transactionAddress);

        return null;
    }
}
