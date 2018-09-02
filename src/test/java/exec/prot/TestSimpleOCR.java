package exec.prot;

import logic.core.ocr.UsefulTesseract;
import logic.core.search.WebSearch;
import logic.core.search.WebSearchImpl;
import logic.core.word.WordDocument;
import logic.core.word.WordDocumentImpl;
import logic.possibilities.docs.WordDocService;
import logic.possibilities.docs.WordDocServiceImpl;
import logic.possibilities.search.WebSearchService;
import logic.possibilities.search.WebSearchServiceImpl;
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
        WordDocService service = new WordDocServiceImpl();
        service.createWordDocument("D:\\IdeaProjects\\resources\\img002.png",
                "D:\\IdeaProjects\\resources\\newDoc002.docx",
                UsefulTesseract.Language.RU.get());
    }

    @Test
    public void showInBrowser() throws IOException, URISyntaxException {
        WebSearchService service = new WebSearchServiceImpl();
        service.searchResultInWebBrowser("D:\\IdeaProjects\\resources\\img002.png", UsefulTesseract.Language.RU.get());
    }
}
