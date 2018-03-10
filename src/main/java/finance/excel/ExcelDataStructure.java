package finance.excel;

import java.util.Collection;
import java.util.Map;

public interface ExcelDataStructure {

    void setDataToExcelField(Collection<?> objects);

    Map<?, ?> getExcelField();

    Object[] getColumnValues();

}
