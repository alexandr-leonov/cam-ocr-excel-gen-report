package logic.finance.service;

import logic.core.ocr.UsefulTesseract;
import logic.finance.doctype.DocumentEntity;
import logic.finance.doctype.DocumentTypeLinker;
import logic.finance.doctype.RealDocumentType;
import logic.finance.doctype.item.ProductItem;

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
        StringBuilder res = new StringBuilder();
        for (String part : imageParts) {
            ocr.scanTextWithImage(part);
            res.append(ocr.getResult());
        }
        DocumentEntity entity = getEntityByType(res.toString());
        entity.calculateDocument(reportName);
    }

    private DocumentEntity getEntityByType(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        RealDocumentType documentType = null;
        ArrayList<String> rows = new ArrayList<>();
        while (st.hasMoreTokens()) {
            rows.add(st.nextToken());
        }
        for (String row : rows) {
            documentType = DocumentTypeLinker.INSTANCE.getDocTypeByName(row);
            if (DocumentTypeLinker.DocType.MEGAPOLIS.name().equals(documentType.getMarketName())) {
                return fillMegapolisDocType(text);
            }
        }
        throw new IllegalArgumentException("Unique document type");
    }

    private DocumentEntity fillMegapolisDocType(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        RealDocumentType documentType = null;
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
        return documentType;
    }

    private float parseNumeral(String text, int index) {
        String sum = text;
        sum = sum.substring(index, sum.length());
        StringBuilder numbers = new StringBuilder();
        for (int j = 1; j < sum.length(); j++) {
            try {
                Integer.parseInt(String.valueOf(sum.toCharArray()[j]));
                numbers.append(sum.toCharArray()[j]);
            } catch (Exception ex) {
            }
        }
        StringBuilder sb = new StringBuilder(numbers.toString());
        sb.insert(numbers.length() - 2, ".");
        return Float.parseFloat(sb.toString());
    }
}
