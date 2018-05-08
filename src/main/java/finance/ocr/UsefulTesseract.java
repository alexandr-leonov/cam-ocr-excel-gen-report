package finance.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class UsefulTesseract {
    public void start(String fileName){
        File imageFile = new File(fileName);
        Tesseract instance = new Tesseract();
        instance.setDatapath("D:\\cam-ocr-excel-gen-report-master\\tessdata");
        instance.setLanguage("rus");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
