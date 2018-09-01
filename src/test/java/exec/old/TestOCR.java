package exec.old;

import logic.finance.service.GenerateReportService;
import logic.finance.service.GenerateReportServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

public class TestOCR {
    private final static Logger LOGGER = Logger.getLogger(TestOCR.class);
    @Test
    public void fullCycle() throws IOException {
        GenerateReportService service = new GenerateReportServiceImpl();
        service.generate("D:\\2311.jpg","report");
    }
}
