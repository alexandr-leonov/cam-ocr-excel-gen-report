import finance.ocr.UsefulTesseract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestOCR {
    private UsefulTesseract aspire;

    @Before
    public void start() {
        aspire = new UsefulTesseract();
    }

    @Test
    public void textWithPNG() {
        aspire.start("D:\\2310.png");
    }

    @After
    public void end() {
        aspire = null;
    }
}
