package old;

import finance.excel.*;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TestExcelUpdate {
    private final static Logger LOGGER = Logger.getLogger(TestExcelUpdate.class);
    private int maxRowNumber;
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

    @Test
    public void getPositionWithExistExcelDocument(){

        setCollection();
        Collection<ExcelDataStructure> array = new ArrayList<ExcelDataStructure>();
        array.add(structure);
        array.add(structure2);

        File file = new File("report"+".xls");
        // Read XSL file
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            // Get the workbook instance for XLS file
            Workbook workbook = new HSSFWorkbook(inputStream);
            // Get first sheet from the workbook
            Sheet sheet = workbook.getSheet("Finance report");
            maxRowNumber = sheet.getLastRowNum()+1;
            inputStream.close();
            appendDataToExcelDocument(array, workbook, file);
        } catch (IOException e) {
            LOGGER.error("Error in reading document",e);
        }
    }

    public void appendDataToExcelDocument(Collection<ExcelDataStructure> fields, Workbook book,File file) {
        Sheet sheet = book.getSheet("Finance report");
        int rowCounter = maxRowNumber;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
                sheet.autoSizeColumn(j);
                Cell cell = row.createCell(j);
                cell.setCellValue(field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[j])]
                        .toString());
            }
        }

        FileOutputStream fos = null;
        try {
            //generate report in EXCEL 2003 format
            fos = new FileOutputStream(file);
            book.write(fos);
            fos.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        LOGGER.info("Report was updated successfully!");
    }
}
