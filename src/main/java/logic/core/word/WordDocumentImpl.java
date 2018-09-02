package logic.core.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordDocumentImpl implements WordDocument {

    @Override
    public void generate(String fileName, String content) throws IOException {
        FileOutputStream writer = new FileOutputStream(new File(fileName));
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);
        document.write(writer);
        writer.flush();
        writer.close();
    }
}
