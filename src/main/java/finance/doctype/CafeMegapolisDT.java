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

public class CafeMegapolisDT extends AbstractDocumentType {
    private final static Logger LOGGER = Logger.getLogger(CafeMegapolisDT.class);
    private final static String NAME = "Кафе Мегаполис";
    private List<ProductItem> productItems= new ArrayList<>();
    private List<ExcelDataStructure> fullData = new ArrayList<>();

    @Override
    public void calculateDocument(String reportName) {
        try {
            for (ProductItem product : productItems) {
                fullData.add(prepareStructure(product));
            }
            writeToExcel(reportName);
        } catch (ParseException e) {
            LOGGER.error("Exception preparing report structure.", e);
        }
    }

    private ExcelDataStructure prepareStructure(ProductItem productItem) throws ParseException {
        ExcelDataStructure structure = new ExcelSimpleStructure();
        Collection<String> collection = new ArrayList<String>();
        collection.add(getDate());
        collection.add(NAME);
        collection.add(productItem.getName());
        collection.add(String.valueOf(productItem.getCount()));
        collection.add(String.valueOf(productItem.getCost()));
        structure.setDataToExcelField(collection);
        return structure;
    }

    private void writeToExcel(String reportName) {
        ExcelService service = new ExcelServiceImpl();
        Collection<ExcelDataStructure> array = new ArrayList<ExcelDataStructure>();
        for (ExcelDataStructure structure : fullData) {
            array.add(structure);
        }
        if(new File(reportName+".xlsx").exists()){
            service.updateExcelDocument(array,reportName);
        } else {
            service.writeIntoExcelDocument(array, reportName);
        }
    }

    @Override
    public List<ProductItem> getProductItemList() {
        return productItems;
    }

    @Override
    public void setProductItemList(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    @Override
    public String toString() {
        return "MEGAPOLIS CHECK DOCUMENT TYPE";
    }
}
