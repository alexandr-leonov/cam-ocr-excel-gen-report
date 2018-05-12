import finance.core.GenerateReportService;
import finance.core.GenerateReportServiceImpl;
import finance.doctype.DocumentType;
import finance.doctype.DocumentTypeLinker;
import finance.ocr.UsefulTesseract;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.StringTokenizer;

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
        tesseract.scanTextWithImage("D:\\2314.jpg");
        LOGGER.info(tesseract.getResult());
        StringTokenizer st = new StringTokenizer(tesseract.getResult(),"\n");
        int cx=0;

        String cost = "";
        String count = "";
        while (st.hasMoreTokens()){
            String str=st.nextToken();
            if(cx==15){
                System.out.println(str);
                for(int i=str.length()-1;i>=0;i--){
                    int j=-1;
                    //cost have format 888.88 - max 6 numbers (for my small byes)
                    if(str.toCharArray()[i]!=' '){
                        if(i>=str.length()-6) {
                            cost += str.toCharArray()[i];
                            j=i;
                        }
                        if(i>str.length()-9 && j!=i){
                            count+=str.toCharArray()[i];
                        }
                    }
                }
            }
            cx++;
        }
        StringBuilder genCost = new StringBuilder(cost).reverse();
        if(!genCost.toString().contains(".")){
            genCost.insert(genCost.length()-2,".");
        }
        System.out.println("cost = " + genCost.toString());
        System.out.println("count = " + new StringBuilder(count).reverse());
        DocumentType dt = DocumentTypeLinker.INSTANCE.getDocTypeByName("ОПЛАТЫ");
        LOGGER.info(dt.toString());
    }
}
