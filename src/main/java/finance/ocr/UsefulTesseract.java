package finance.ocr;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class UsefulTesseract {
    public void start(){
        File imageFile = new File("2310.jpg");
        Tesseract instance = new Tesseract();

        instance.setDatapath("/home/venum/tess/tessdata");
        instance.setLanguage("eng");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
