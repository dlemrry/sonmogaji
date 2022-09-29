package com.ssafy.sonmogaji.model.service;

import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;
import com.ssafy.sonmogaji.model.dto.TransactionDto;
import org.apache.poi.xwpf.usermodel.*;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ApachePOIServiceImpl implements ApachePOIService{

//    private static final String PATH = "classpath:/static/";

    @Override
    public void createPreview(TransactionDto transactionDto) throws Exception {
        String sample = "memorandom.docx";
        FileOutputStream fos = null;

        try {
            File file = new File(sample);

            XWPFDocument doc = new XWPFDocument(new FileInputStream(file));

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

            String txTitle = transactionDto.getTxTitle();
            String txContent = transactionDto.getTxContent();


//            XWPFParagraph paragraph = doc.createParagraph();

            for(int i = 0; i < transactionDto.getSignees().size(); i++ ) {
                XWPFTableRow row = table.getRow(i);
                row.getCell(0).setText(transactionDto.getSignees().get(i));
                table.createRow();
//                row.getCell(1).setParagraph(paragraph.createRun().addPicture());

            }


//            row.getCell(1).setParagraph(paragraph.createRun().addPicture());


            fos = new FileOutputStream(new File(sample));
            doc.write(fos);

            if(fos != null) fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(sample);

        Document document = new Document();
        document.loadFromFile(sample);

        BufferedImage image = document.saveToImages(0, ImageType.Bitmap);

        File imgFile = new File("Preview.PNG");
        ImageIO.write(image, "PNG", imgFile);
    }

    @Override
    public File createImg(File preview) {
        return null;
    }
}
