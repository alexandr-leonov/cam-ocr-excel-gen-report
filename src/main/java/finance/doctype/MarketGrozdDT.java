package finance.doctype;

import finance.doctype.item.ProductItem;
import org.apache.log4j.Logger;

import java.util.List;

public class MarketGrozdDT extends AbstractDocumentType{
    private final static Logger LOGGER = Logger.getLogger(MarketGrozdDT.class);
    private final static String NAME = "Магазин ГРОЗДЬ";
    private List<ProductItem> productItems;

    @Override
    public void calculateDocument(String reportName) {

    }

    @Override
    public void setProductItemList(List<ProductItem> productItemList) {
        this.productItems=productItems;
    }

    @Override
    public List<ProductItem> getProductItemList() {
        return productItems;
    }

    @Override
    public String toString() {
        return "GROZD MARKET CHECK DOCUMENT TYPE";
    }
}
