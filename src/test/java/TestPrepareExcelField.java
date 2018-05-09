import finance.excel.ExcelDataStructure;
import finance.excel.ExcelSimpleStructure;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class TestPrepareExcelField {
    private final static Logger LOGGER = Logger.getLogger(TestPrepareExcelField.class);
    private ExcelDataStructure structure;

    @Before
    public void start() throws Exception {
        structure = new ExcelSimpleStructure();
    }

    @Test
    public void calculateStructure() {
        Collection<String> collection = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd.MM.YYYY");
        collection.add(simpleDateFormat.format(new Date()));
        collection.add("Name of market");
        collection.add(String.valueOf((float) 100 / 12));
        collection.add("Name of gift");
        collection.add(String.valueOf(12));
        structure.setDataToExcelField(collection);
        LOGGER.info(structure.getExcelField());
    }
}
