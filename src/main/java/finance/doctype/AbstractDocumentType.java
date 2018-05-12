package finance.doctype;

import finance.doctype.item.ProductItem;
import finance.excel.ExcelDataStructure;
import finance.excel.ExcelService;
import finance.excel.ExcelServiceImpl;
import finance.excel.ExcelSimpleStructure;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDocumentType implements DocumentType, DocumentEntity {
    private String date;
    private float sum;

    protected List<ExcelDataStructure> fullData = new ArrayList<>();
    protected List<ProductItem> productItems= new ArrayList<>();

    public List<ProductItem> getProductItemList() {
        return productItems;
    }

    protected ExcelDataStructure prepareStructure(ProductItem productItem,String marketName) throws ParseException {
        ExcelDataStructure structure = new ExcelSimpleStructure();
        Collection<String> collection = new ArrayList<>();
        collection.add(getDate());
        collection.add(marketName);
        collection.add(productItem.getName());
        collection.add(String.valueOf(productItem.getCount()));
        collection.add(String.valueOf(productItem.getCost()));
        structure.setDataToExcelField(collection);
        return structure;
    }

    protected void writeToExcel(String reportName) {
        ExcelService service = new ExcelServiceImpl();
        Collection<ExcelDataStructure> array = new ArrayList<>();
        for (ExcelDataStructure structure : fullData) {
            array.add(structure);
        }
        if(new File(reportName+".xlsx").exists()){
            service.updateExcelDocument(array,reportName);
        } else {
            service.writeIntoExcelDocument(array, reportName);
        }
    }

    public void setProductItemList(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getSum() {
        return this.sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }
}
