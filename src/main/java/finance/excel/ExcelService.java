package finance.excel;

import java.util.*;

public interface ExcelService {

    void writeIntoExcelDocument(Collection<ExcelDataStructure> fields);

}
