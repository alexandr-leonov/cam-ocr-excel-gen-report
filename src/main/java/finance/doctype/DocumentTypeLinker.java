package finance.doctype;

import java.util.LinkedHashMap;
import java.util.Map;

public class DocumentTypeLinker {
    public static final DocumentTypeLinker INSTANCE=new DocumentTypeLinker();
    private static final Map<String,DocType> linkMap = new LinkedHashMap<>();

    static {
        linkMap.put("МЕГАПОЛИС",DocType.MEGAPOLIS);
        linkMap.put("ОПЛАТЫ",DocType.GROZD);
    }

    public AbstractDocumentType getDocTypeByName(String name){
        String value=null;
        for(String key :linkMap.keySet()){
            if(name.contains(key)){
                value=key;
            }
        }
        if(!linkMap.containsKey(value)) return null;
        return linkMap.get(value).getDocType();
    }

    enum DocType{
        MEGAPOLIS(new CafeMegapolisDT()),
        GROZD(new MarketGrozdDT());

        private AbstractDocumentType docType;

        DocType(AbstractDocumentType docType){
            this.docType = docType;
        }

        public AbstractDocumentType getDocType(){
            return docType;
        }
    }

    private DocumentTypeLinker(){
    }
}
