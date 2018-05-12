package finance.excel;

import java.util.*;

public interface ExcelService {

    void writeIntoExcelDocument(Collection<ExcelDataStructure> fields,String reportName);

    void updateExcelDocument(Collection<ExcelDataStructure> fields,String reportName);

}
