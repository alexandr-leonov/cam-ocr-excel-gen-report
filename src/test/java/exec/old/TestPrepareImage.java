package exec.old;

import logic.core.image.PrepareImage;
import logic.finance.service.GenerateReportService;
import logic.finance.service.GenerateReportServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestPrepareImage {
    private final static Logger LOGGER = Logger.getLogger(TestPrepareImage.class);
    private PrepareImage img;

    @Before
    public void start(){
        img = new PrepareImage(new File("D:\\2311.jpg"));
    }

    @Ignore
    public void getPartOfImage() throws IOException {
        img.createSnippingImage("D:\\2311y.jpg",0,0,170,45);
    }

    @Test
    public void getAllRowsOfImage() throws IOException {
        img = new PrepareImage(new File("D:\\2312.jpg"));
        List<String> parts = img.splitImageToRows("D:\\2312.jpg", (int) img.getSourceImageHeigh() / 2);
        LOGGER.info(parts);
        GenerateReportService service = new GenerateReportServiceImpl();
        service.generate(parts,"report");
    }

    @Ignore
    public void getSourceImageSize(){
        LOGGER.info("X=" + img.getSourceImageWidth() + " Y="+img.getSourceImageHeigh());
    }
}
