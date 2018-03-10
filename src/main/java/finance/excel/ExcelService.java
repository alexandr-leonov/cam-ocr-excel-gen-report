package finance.excel;

import java.util.*;

public interface ExcelService {

    //void readFromExcelDocument(Collection<ExcelDataStructure> fields);

    void writeIntoExcelDocument(Collection<ExcelDataStructure> fields);

}
