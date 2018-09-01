package finance.doctype;

import finance.doctype.item.ProductItem;
import finance.excel.ExcelDataStructure;
import finance.excel.ExcelService;
import finance.excel.ExcelServiceImpl;
import finance.excel.ExcelSimpleStructure;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RealDocumentType implements DocumentType, DocumentEntity {
    private String date;
    private float sum;
    private Logger LOGGER = Logger.getLogger(this.getClass());

    private List<ExcelDataStructure> fullData = new ArrayList<>();
    private List<ProductItem> productItems= new ArrayList<>();
    private String marketName;

    public RealDocumentType(String marketName){
        this.marketName = marketName;
    }

    @Override
    public void calculateDocument(String reportName) {
        try {
            for (ProductItem product : productItems) {
                String NAME = marketName;
                fullData.add(prepareStructure(product, NAME));
            }
            writeToExcel(reportName);
        } catch (ParseException e) {
            LOGGER.error("Exception preparing report structure.", e);
        }
    }

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

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}
