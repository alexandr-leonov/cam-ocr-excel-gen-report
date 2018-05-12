package finance.excel;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ExcelServiceImpl implements ExcelService {
    private final static Logger LOGGER = Logger.getLogger(ExcelServiceImpl.class);
    private int maxRowNumber;

    @Override
    public void writeIntoExcelDocument(Collection<ExcelDataStructure> fields, String reportName) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Finance report");
        int rowCounter = 0;
//        float summ = 0;
//        int numberCostColumn = -1;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
                sheet.autoSizeColumn(j);
                Cell cell = row.createCell(j);
                cell.setCellValue(field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[j])]
                        .toString());
//                if (ReportColumnStructure.COST_GIFT.equals(field.getColumnValues()[j])) {
//                    numberCostColumn = j;
//                }
            }
//            if (numberCostColumn != -1) {
//                summ += Float.parseFloat(field.getExcelField().values()
//                        .toArray()[keys.indexOf(field.getColumnValues()[numberCostColumn])].toString());
//            }
            rowCounter++;
        }
//        if (numberCostColumn != -1) {
//            Row row = sheet.createRow(rowCounter);
//            Cell cell = row.createCell((numberCostColumn > 0) ? numberCostColumn - 1 : numberCostColumn + 1);
//            cell.setCellValue((numberCostColumn > 0) ? "Итог:" : "<- Итог");
//            Cell cell2 = row.createCell(numberCostColumn);
//            cell2.setCellValue(summ);
//        }
        FileOutputStream fos = null;
        try {
            //generate report in EXCEL 2003 format
            fos = new FileOutputStream(reportName + ".xls", true);

        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
        }
        try {
            book.write(fos);
            book.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        LOGGER.info("Report was created successfully!");
    }

    @Override
    public void updateExcelDocument(Collection<ExcelDataStructure> fields, String reportName) {
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
            appendDataToExcelDocument(fields, workbook, file);
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
            rowCounter++;
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
