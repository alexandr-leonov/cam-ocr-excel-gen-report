import finance.excel.ExcelDataStructure;
import finance.excel.ExcelService;
import finance.excel.ExcelServiceImpl;
import finance.excel.ExcelSimpleStructure;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TestAddFieldToExcel {
    private ExcelDataStructure structure, structure2;
    private ExcelService service;

    @Before
    public void start() throws Exception {
        structure = new ExcelSimpleStructure();
        structure2 = new ExcelSimpleStructure();
        service = new ExcelServiceImpl();
    }

    private void setCollection() {
        Collection<String> collection = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd.MM.YYYY");
        collection.add(simpleDateFormat.format(new Date()));
        collection.add("Name of market");
        collection.add(String.valueOf((float) 100 / 12));
        collection.add("Name of gift");
        collection.add(String.valueOf(12));
        structure.setDataToExcelField(collection);

        Collection<String> collection2 = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat2 =new SimpleDateFormat("dd.MM.YYYY");
        collection2.add(simpleDateFormat2.format(new Date(10,10,10)));
        collection2.add("Name second Market");
        collection2.add(String.valueOf((float) 150 / 12));
        collection2.add("Second gift");
        collection2.add(String.valueOf(1));
        structure2.setDataToExcelField(collection2);
    }

    @Ignore
    public void writeToExcel(){
        setCollection();
        Collection<ExcelDataStructure> array = new ArrayList<ExcelDataStructure>();
        array.add(structure);
        array.add(structure2);
        service.writeIntoExcelDocument(array);
    }

    @After
    public void end(){
        structure = structure2 = null;
        service = null;
    }
}
