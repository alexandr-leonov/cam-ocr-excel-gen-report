package finance.excel;

import org.apache.log4j.Logger;
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
    private final static Logger LOGGER = Logger.getLogger(ExcelServiceImpl.class);

    @Override
    public void writeIntoExcelDocument(Collection<ExcelDataStructure> fields,String reportName) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Finance report");
        int rowCounter = 0;
        float summ = 0;
        int numberCostColumn = -1;
        for (ExcelDataStructure field : fields) {
            Row row = sheet.createRow(rowCounter);
            ArrayList<Object> keys = new ArrayList<Object>(field.getExcelField().keySet());
            for (int j = 0; j < field.getColumnValues().length; j++) {
                sheet.autoSizeColumn(j);
                Cell cell = row.createCell(j);
                cell.setCellValue(field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[j])]
                        .toString());
                if(ReportColumnStructure.COST_GIFT.equals(field.getColumnValues()[j])){
                    numberCostColumn=j;
                }
            }
            if(numberCostColumn!=-1){
                summ+=Float.parseFloat(field.getExcelField().values()
                        .toArray()[keys.indexOf(field.getColumnValues()[numberCostColumn])].toString());
            }
            rowCounter++;
        }
        if(numberCostColumn!=-1) {
            Row row = sheet.createRow(rowCounter);
            Cell cell = row.createCell((numberCostColumn>0) ? numberCostColumn-1 : numberCostColumn+1);
            cell.setCellValue((numberCostColumn>0) ? "Итог:" :"<- Итог");
            Cell cell2 = row.createCell(numberCostColumn);
            cell2.setCellValue(summ);
        }
        FileOutputStream fos = null;
        try {
            //generate report in EXCEL 2003 format
            fos = new FileOutputStream(reportName+".xls", true);

        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
        }
        try {
            book.write(fos);
            book.close();
        } catch (IOException e) {
            LOGGER.error("",e);
        }
        LOGGER.info("Report was created successfully!");
    }
}
