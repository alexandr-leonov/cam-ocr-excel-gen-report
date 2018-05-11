package finance.doctype;

import finance.doctype.item.ProductItem;

import java.util.List;

public interface DocumentType {
    String getDate();

    void setDate(String date);

    float getSum();

    void setSum(float sum);

    void setProductItemList(List<ProductItem> productItemList);

    List<ProductItem> getProductItemList();

}
