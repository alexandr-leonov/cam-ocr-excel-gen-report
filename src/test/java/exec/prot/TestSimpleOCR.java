package exec.prot;

import com.google.common.io.BaseEncoding;
import logic.core.ocr.UsefulTesseract;
import logic.core.search.WebSearch;
import logic.core.search.WebSearchImpl;
import logic.core.word.WordDocument;
import logic.core.word.WordDocumentImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestSimpleOCR {

    private UsefulTesseract ocr = new UsefulTesseract();

    @Ignore
    public void qualityOCR() {
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img002.png",UsefulTesseract.Language.RU.get());
        System.out.println(ocr.getResult());
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img001.png");
        System.out.println(ocr.getResult());
    }

    @Test
    public void writeWordDocument() throws IOException {
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img002.png",UsefulTesseract.Language.RU.get());
        WordDocument document = new WordDocumentImpl();
        document.generate("D:\\IdeaProjects\\resources\\doc002.docx",ocr.getResult());
    }

    @Test
    public void showInBrowser() throws IOException, URISyntaxException {
        ocr.scanTextWithImage("D:\\IdeaProjects\\resources\\img002.png",UsefulTesseract.Language.RU.get());
        WebSearch search = new WebSearchImpl();
        search.runSearchInBrowser(ocr.getResult());
    }
}
