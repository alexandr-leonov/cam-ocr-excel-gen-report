package finance.excel;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
        int numberCostColumn = 0;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
                sheet.autoSizeColumn(j);
                Cell cell = row.createCell(j);
                String value = field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[j])].toString();
                if (ReportColumnStructure.COST_GIFT.equals(field.getColumnValues()[j])) {
                    cell.setCellType(CellType.NUMERIC);
                    numberCostColumn=j;
                    float cost = Float.parseFloat(value);
                    cell.setCellValue(cost);
                } else {
                    cell.setCellValue(value);
                }
            }
            rowCounter++;
        }
        Row row = sheet.createRow(rowCounter);
        calculateSum(row,numberCostColumn);
        try {
            //generate report in EXCEL 2003 format
            FileOutputStream fos = new FileOutputStream(reportName + ".xls", true);
            book.write(fos);
            fos.close();
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
            maxRowNumber = sheet.getLastRowNum();
            inputStream.close();
            appendDataToExcelDocument(fields, workbook, file);
        } catch (IOException e) {
            LOGGER.error("Error in reading document",e);
        }
    }

    private void appendDataToExcelDocument(Collection<ExcelDataStructure> fields, Workbook book,File file) {
        Sheet sheet = book.getSheet("Finance report");
        int rowCounter = maxRowNumber;
        int numberCostColumn = 0;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
                sheet.autoSizeColumn(j);
                Cell cell = row.createCell(j);
                String value = field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[j])].toString();
                if (ReportColumnStructure.COST_GIFT.equals(field.getColumnValues()[j])) {
                    cell.setCellType(CellType.NUMERIC);
                    numberCostColumn=j;
                    float cost = Float.parseFloat(value);
                    cell.setCellValue(cost);
                } else {
                    cell.setCellValue(value);
                }
            }
            rowCounter++;
        }
        Row row = sheet.createRow(rowCounter);
        calculateSum(row,numberCostColumn);
        try {
            //generate report in EXCEL 2003 format
            FileOutputStream fos = new FileOutputStream(file);
            book.write(fos);
            fos.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        LOGGER.info("Report was updated successfully!");
    }

    private void calculateSum(Row row,int columnNumber){
        Cell cell = row.createCell(columnNumber, CellType.FORMULA);
        String nameCell=cell.getAddress().formatAsString().replaceAll("\\d","");
        String startCell = (new StringBuilder(nameCell).append(1)).toString();
        String endCell = (new StringBuilder(nameCell).append(row.getRowNum())).toString();
        cell.setCellFormula("SUM("+startCell+":"+endCell+")");
    }
}
