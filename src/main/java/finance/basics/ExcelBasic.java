package finance.basics;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelBasic {

    public String test() {
        return "test ";
    }

    public void writeIntoExcel(String fileName, String bookName) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(bookName);

        // Нумерация начинается с нуля
        Row row = sheet.createRow(3);

        Cell name = row.createCell(0);
        name.setCellValue("John");

        Cell name2 = row.createCell(1);
        name2.setCellValue("Дижон");

        Cell birthdate = row.createCell(2);

        DataFormat format = book.createDataFormat();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        birthdate.setCellStyle(dateStyle);


        // Нумерация лет начинается с 1900-го
        birthdate.setCellValue(new Date(118, 10, 10));

        // Меняем размер столбца
        sheet.autoSizeColumn(2);

        Row secondRow = sheet.createRow(4);
        secondRow.createCell(0).setCellValue("Привет");

        // Записываем всё в файл
        FileOutputStream fos = new FileOutputStream(fileName, true);
        System.out.println(fos);
        book.write(fos);
        book.close();

    }


    public void readFromExcel(String fileName, String bookName) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheet(bookName);
        HSSFRow row = myExcelSheet.getRow(0);

        if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            Date birthdate = row.getCell(1).getDateCellValue();
            System.out.println("birthdate :" + birthdate);
        }

        myExcelBook.close();

    }


}
