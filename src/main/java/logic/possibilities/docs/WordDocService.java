package logic.possibilities.docs;

import java.io.IOException;

public interface WordDocService {

    void createWordDocument(String imageFileName,String docFileName, String language) throws IOException;

    void createWordDocument(String imageFileName,String docFileName) throws IOException;
}
