package logic.finance.doctype;

import java.util.LinkedHashMap;
import java.util.Map;

public class DocumentTypeLinker {
    public static final DocumentTypeLinker INSTANCE=new DocumentTypeLinker();
    private static final Map<String,DocType> linkMap = new LinkedHashMap<>();

    static {
        linkMap.put("МЕГАПОЛИС",DocType.MEGAPOLIS);
        linkMap.put("ГРОЗДЬ",DocType.GROZD);
    }

    public RealDocumentType getDocTypeByName(String name){
        String value=null;
        for(String key :linkMap.keySet()){
            if(name.contains(key)){
                value=key;
            }
        }
        if(!linkMap.containsKey(value)) return null;
        return linkMap.get(value).getDocType();
    }

    public enum DocType{
        MEGAPOLIS(new RealDocumentType("MEGAPOLIS")),
        GROZD(new RealDocumentType("GROZD"));

        private RealDocumentType docType;

        DocType(RealDocumentType docType){
            this.docType = docType;
        }

        public RealDocumentType getDocType(){
            return docType;
        }
    }

    private DocumentTypeLinker(){
    }
}
