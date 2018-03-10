import finance.excel.ExcelDataStructure;
import finance.excel.ExcelSimpleStructure;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class TestPrepareExcelField {
    private ExcelDataStructure structure;
    @Before
    public void start() throws Exception {
        structure = new ExcelSimpleStructure();
    }

    @Ignore
    public void first() {
        Collection<String> collection = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd.MM.YYYY");
        collection.add(simpleDateFormat.format(new Date()));
        collection.add("Name of market");
        collection.add(String.valueOf((float) 100 / 12));
        collection.add("Name of gift");
        collection.add(String.valueOf(12));
        structure.setDataToExcelField(collection);
        System.out.println(structure.getExcelField());
    }

    @After
    public void end(){
        structure = null;
    }
}
