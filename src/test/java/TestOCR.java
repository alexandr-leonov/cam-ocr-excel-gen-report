import finance.core.GenerateReportService;
import finance.core.GenerateReportServiceImpl;
import finance.ocr.UsefulTesseract;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class TestOCR {
    private final static Logger LOGGER = Logger.getLogger(TestOCR.class);
    @Test
    public void fullCycle() throws IOException {
        GenerateReportService service = new GenerateReportServiceImpl();
        service.generate("D:\\2311.jpg","report");
    }

    @Ignore
    public void viewOCR() throws IOException {
        UsefulTesseract tesseract = new UsefulTesseract();
        tesseract.scanTextWithImage("D:\\2311.jpg");
        LOGGER.info(tesseract.getResult());
//        StringTokenizer st = new StringTokenizer(tesseract.getResult(),"\n");
//        ArrayList<String> array = new ArrayList<>();
//        while (st.hasMoreTokens()){
//            array.add(st.nextToken());
//        }
//        DocumentType dt = DocumentTypeLinker.INSTANCE.getDocTypeByName(array.get(array.size()-1));
//        LOGGER.info(dt.toString());
    }
}
