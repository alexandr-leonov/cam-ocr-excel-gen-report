package finance.core;

import finance.doctype.*;
import finance.doctype.item.ProductItem;
import finance.ocr.UsefulTesseract;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GenerateReportServiceImpl implements GenerateReportService {
    @Override
    public void generate(String imageFileName, String reportName) {
        UsefulTesseract ocr = new UsefulTesseract();
        ocr.scanTextWithImage(imageFileName);
        String res = ocr.getResult();
        DocumentEntity entity = getEntityByType(res);
        entity.calculateDocument(reportName);
    }

    @Override
    public void generate(List<String> imageParts, String reportName) {
        UsefulTesseract ocr = new UsefulTesseract();
        String res = "";
        for (String part : imageParts) {
            ocr.scanTextWithImage(part);
            res += ocr.getResult();
        }
        DocumentEntity entity = getEntityByType(res);
        entity.calculateDocument(reportName);
    }

    private DocumentEntity getEntityByType(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        AbstractDocumentType documentType = null;
        ArrayList<String> rows = new ArrayList<>();
        while (st.hasMoreTokens()) {
            rows.add(st.nextToken());
        }
        for (String row : rows) {
            documentType = DocumentTypeLinker.INSTANCE.getDocTypeByName(row);
            if (documentType instanceof CafeMegapolisDT) {
                return fillMegapolisDocType(text);
            } else if (documentType instanceof MarketGrozdDT) {
                return fillGrozdDocType(text);
            }
        }
        throw new IllegalArgumentException("Unique document type");
    }

    private DocumentEntity fillMegapolisDocType(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        AbstractDocumentType documentType = null;
        ArrayList<String> rows = new ArrayList<>();
        while (st.hasMoreTokens()) {
            rows.add(st.nextToken());
        }
        List<ProductItem> products = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            if (i == 2) {
                documentType = DocumentTypeLinker.INSTANCE.getDocTypeByName(rows.get(i));
            }
            if (documentType != null) {
                if (documentType instanceof CafeMegapolisDT) {
                    if (i == 4) {//parse date
                        String date = rows.get(i).substring(0, 8);
                        documentType.setDate(date);
                    }
                    if (i > 5 && !(rows.get(i).contains("ИТОГ") || rows.get(i).contains("итог"))) {//parse product items
                        ProductItem item = null;
                        if (rows.get(i + 1).contains("Код") || rows.get(i + 1).contains("Кол")) {//product name
                            item = new ProductItem();
                            item.setName(rows.get(i).substring(2, rows.get(i).length()));
                        }
                        if (item != null) {
                            if ((rows.get(i + 2).contains("x")
                                    || rows.get(i + 2).contains("›‹")
                                    || rows.get(i + 2).contains("><"))) {//count product
                                item.setCount(parseNumeral(rows.get(i + 2), 5));
                                item.setCost(parseNumeral(rows.get(i + 3), 2));
                            } else {//product cost
                                item.setCost(parseNumeral(rows.get(i + 2), 2));
                            }
                            products.add(item);
                        }
                    }
                    if (rows.get(i).contains("ИТОГ") || rows.get(i).contains("итог")) {//parse main sum
                        documentType.setProductItemList(products);
                        documentType.setSum(parseNumeral(rows.get(i + 1), 10));
                        break;
                    }
                }
            }
        }
        return documentType;
    }

    private DocumentEntity fillGrozdDocType(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        AbstractDocumentType documentType = null;
        ArrayList<String> rows = new ArrayList<>();
        while (st.hasMoreTokens()) {
            rows.add(st.nextToken());
        }
        List<ProductItem> products = new ArrayList<>();

        documentType = DocumentTypeLinker.INSTANCE.getDocTypeByName("ОПЛАТЫ");
        String cost = "";
        String count = "";
        for (int i = 0; i < rows.size(); i++) {
            if (i == 5) {//parse date
                String date = rows.get(i).substring(9, 19).replaceAll(" ", "\\.");
                documentType.setDate(date);
            }
            if ((i > 5 && i % 2 == 0) && !rows.get(i).contains("ОПЛАТЫ")) {//parse product items
                ProductItem item = new ProductItem();
                item.setName(rows.get(i));
                String str = rows.get(i + 1);
                for (int u = str.length() - 1; u >= 0; u--) {
                    int j = -1;
                    //cost have format 888.88 - max 6 numbers (for my small byes)
                    if (str.toCharArray()[u] != ' ') {
                        if (u >= str.length() - 6) {
                            cost += str.toCharArray()[u];
                            j = u;
                        }
                        if (u > str.length() - 9 && j != u) {
                            count += str.toCharArray()[u];
                        }
                    }
                }
                StringBuilder genCost = new StringBuilder(cost).reverse();
                if (!genCost.toString().contains(".")) {
                    genCost.insert(cost.length() - 2, ".");
                }
                cost = genCost.toString();
                System.out.println(i + " - " + cost);
                item.setCost(Float.parseFloat(cost));
                item.setCount(Float.parseFloat(new StringBuilder(count).reverse().toString()));
                products.add(item);
            }
            cost = count = "";
            if (rows.get(i).contains("ОПЛАТЫ")) break;
        }
        documentType.setProductItemList(products);
        return documentType;
    }

    private float parseNumeral(String text, int index) {
        String sum = text;
        sum = sum.substring(index, sum.length());
        String numbers = "";
        for (int j = 1; j < sum.length(); j++) {
            try {
                Integer.parseInt(String.valueOf(sum.toCharArray()[j]));
                numbers += sum.toCharArray()[j];
            } catch (Exception ex) {
            }
        }
        StringBuilder sb = new StringBuilder(numbers);
        sb.insert(numbers.length() - 2, ".");
        return Float.parseFloat(sb.toString());
    }
}
