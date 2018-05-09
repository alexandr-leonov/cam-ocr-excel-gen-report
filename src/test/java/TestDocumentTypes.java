import finance.doctype.DocumentType;
import finance.doctype.DocumentTypeLinker;
import org.apache.log4j.Logger;
import org.junit.Test;

public class TestDocumentTypes {
    private static final Logger LOGGER = Logger.getLogger(TestDocumentTypes.class);

    @Test
    public void testDTLinker(){
        String testNameDT = "МЕГАПОЛИС";
        DocumentType dt = DocumentTypeLinker.INSTANCE.getDocTypeByName(testNameDT);
        LOGGER.info(dt.toString());
    }
}
