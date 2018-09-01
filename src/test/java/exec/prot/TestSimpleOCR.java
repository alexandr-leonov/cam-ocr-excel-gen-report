package exec.prot;

import logic.core.ocr.UsefulTesseract;
import org.junit.Test;

public class TestSimpleOCR {

    @Test
    public void qualityOCR() {
        UsefulTesseract ocr = new UsefulTesseract();
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img002.png",UsefulTesseract.Language.RU.get());
        System.out.println(ocr.getResult());
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img001.png");
        System.out.println(ocr.getResult());
    }
}
