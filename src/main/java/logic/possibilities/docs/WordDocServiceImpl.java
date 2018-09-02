package logic.possibilities.docs;

import logic.core.ocr.UsefulTesseract;
import logic.core.word.WordDocument;
import logic.core.word.WordDocumentImpl;

import java.io.IOException;

public class WordDocServiceImpl implements WordDocService{

    public void createWordDocument(String imageFileName,String docFileName, String language) throws IOException {
        WordDocument doc = new WordDocumentImpl();
        UsefulTesseract ocr = new UsefulTesseract();
        ocr.scanTextWithImage(imageFileName,language);
        doc.generate(docFileName,ocr.getResult());
    }

    public void createWordDocument(String imageFileName,String docFileName) throws IOException {
        createWordDocument(imageFileName,docFileName,UsefulTesseract.Language.EN.get());
    }
}
