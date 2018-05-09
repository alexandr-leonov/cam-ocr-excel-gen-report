package finance.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.Logger;

import java.io.File;

public class UsefulTesseract {
    private final static Logger LOGGER = Logger.getLogger(UsefulTesseract.class);
    private String result;

    public void scanTextWithImage(String fileName){
        File imageFile = new File(fileName);
        Tesseract instance = new Tesseract();
        instance.setDatapath("D:\\cam-ocr-excel-gen-report-master\\tessdata");
        instance.setLanguage("rus");
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            LOGGER.error("",e);
        }
    }

    public String getResult() {
        return result;
    }
}
