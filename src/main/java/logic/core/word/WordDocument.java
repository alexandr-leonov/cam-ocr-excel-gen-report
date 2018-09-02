package logic.core.word;

import java.io.IOException;

public interface WordDocument {
    void generate(String fileName,String content) throws IOException;
}
