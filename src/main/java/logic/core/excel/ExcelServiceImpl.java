package logic.core.excel;

import logic.core.config.Constants;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ExcelServiceImpl implements ExcelService {
    private final static Logger LOGGER = Logger.getLogger(ExcelServiceImpl.class);

    @Override
    public void writeIntoExcelDocument(Collection<ExcelDataStructure> fields, String reportName) {
        File file = new File(reportName + Constants.EXCEL_2007_EXTENSION);
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet(Constants.EXCEL_SHEET_NAME);
        addDataToExcelDocument(book,sheet,fields,0,file);
        LOGGER.info(Constants.Messages.REPORT_SUCCESS_CREATING);
    }

    @Override
    public void updateExcelDocument(Collection<ExcelDataStructure> fields, String reportName) {
        File file = new File(reportName+Constants.EXCEL_2007_EXTENSION);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(Constants.EXCEL_SHEET_NAME);
            inputStream.close();
            addDataToExcelDocument(workbook,sheet,fields,sheet.getLastRowNum(),file);
            LOGGER.info(Constants.Messages.REPORT_SUCCESS_UPDATING);
        } catch (IOException e) {
            LOGGER.error("Error in reading document",e);
        }
    }

    private void addDataToExcelDocument(Workbook book, Sheet sheet, Collection<ExcelDataStructure> fields, int startRowIndex,File file){
        int rowCounter = startRowIndex;
        int numberCostColumn = 0;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
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
                cell.getSheet().autoSizeColumn(j);
            }
            rowCounter++;
        }
        Row row = sheet.createRow(rowCounter);
        calculateSum(row,numberCostColumn);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            book.write(fos);
            fos.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }
    }

    private void calculateSum(Row row,int columnNumber){
        Cell cell = row.createCell(columnNumber, CellType.FORMULA);
        String nameCell=cell.getAddress().formatAsString().replaceAll("\\d", "");
        String startCell = nameCell + 1;
        String endCell = nameCell + row.getRowNum();
        cell.setCellFormula("SUM("+startCell+":"+endCell+")");
    }
}
