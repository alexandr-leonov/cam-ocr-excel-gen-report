package logic.core.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.Logger;

import java.io.File;

public class UsefulTesseract {
    private final static Logger LOGGER = Logger.getLogger(UsefulTesseract.class);
    private String result;

    public void scanTextWithImage(String fileName,String language){
        File imageFile = new File(fileName);
        Tesseract instance = new Tesseract();
        instance.setDatapath("D:\\OCR\\tessdata");
        instance.setLanguage(language);
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            LOGGER.error("",e);
        }
    }

    public void scanTextWithImage(String fileName){
        scanTextWithImage(fileName,Language.EN.get());
    }

    public String getResult() {
        return result;
    }

    public enum Language{
        RU("rus"),
        EN("eng");

        private String lang;

        Language(String lang){
            this.lang=lang;
        }

        public String get(){
            return lang;
        }
    }
}
