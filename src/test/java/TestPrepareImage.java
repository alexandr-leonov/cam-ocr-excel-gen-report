import finance.camera.PrepareImage;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestPrepareImage {
    private final static Logger LOGGER = Logger.getLogger(TestPrepareImage.class);
    private PrepareImage img;

    @Before
    public void setUp(){
        img = new PrepareImage(new File("D:\\2311.jpg"));
    }

    @Ignore
    public void getPartOfImage() throws IOException {
        img.createSnippingImage("D:\\2311y.jpg",0,0,170,45);
    }

    @Ignore
    public void getAllRowsOfImage() throws IOException {
        LOGGER.info(img.splitImageToRows("D:\\2311.jpg", 45));
    }

    @Test
    public void getSourceImageSize(){
        LOGGER.info("X=" + img.getSourceImageWidth() + " Y="+img.getSourceImageHeigh());
    }
}
