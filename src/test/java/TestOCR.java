import finance.core.GenerateReportService;
import finance.core.GenerateReportServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class TestOCR {

    @Test
    public void fullCycle() throws IOException {
        GenerateReportService service = new GenerateReportServiceImpl();
        service.generate("D:\\2311.jpg","report");
    }
}
