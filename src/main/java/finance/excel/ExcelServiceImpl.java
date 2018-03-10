package finance.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ExcelServiceImpl implements ExcelService {

    @Override
    public void writeIntoExcelDocument(Collection<ExcelDataStructure> fields) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Finance report");
        int rowCounter = 0;
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
            rowCounter++;
        }
        // Записываем всё в файл
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("test.xls", true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book.write(fos);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Report was created successfully!");
    }
}
