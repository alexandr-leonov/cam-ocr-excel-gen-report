package finance.ocr;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class UsefulTesseract {
    public void start(){
        File imageFile = new File("D:\\samoanalyze.png");
        Tesseract instance = new Tesseract();
        instance.setDatapath("D:\\tes\\tess4j-3.4.4");
        instance.setLanguage("eng");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
