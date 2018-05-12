package finance.excel;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelSimpleStructure implements ExcelDataStructure{
    private Map<ReportColumnStructure,String> map = new LinkedHashMap<>();

    @Override
    public void setDataToExcelField(Collection<?> objects) {
        if(objects.isEmpty()) throw new NullPointerException("Collection is empty!");
        for (int i = 0; i<ReportColumnStructure.getValues().length;i++) {
            map.put(ReportColumnStructure.getValues()[i], (String) objects.toArray()[i]);
        }
    }

    @Override
    public Map<ReportColumnStructure,?> getExcelField() {
        return map;
    }

    @Override
    public Object[] getColumnValues() {
        return ReportColumnStructure.getValues();
    }
}
