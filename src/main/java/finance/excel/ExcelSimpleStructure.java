package finance.excel;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ExcelSimpleStructure implements ExcelDataStructure{
    private Map<ReportColumnStructure,String> map = new HashMap<ReportColumnStructure,String>();

    @Override
    public void setDataToExcelField(Collection<?> objects) {
        if(objects.isEmpty()) throw new NullPointerException("Collection is empty!");
        for (int i = 0; i<ReportColumnStructure.values().length;i++)
            map.put(ReportColumnStructure.values()[i], (String) objects.toArray()[i]);
    }

    @Override
    public Map<ReportColumnStructure,?> getExcelField() {
        return map;
    }

    @Override
    public Object[] getColumnValues() {
        return ReportColumnStructure.values();
    }
}
