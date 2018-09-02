package logic.possibilities.search;

import logic.core.ocr.UsefulTesseract;
import logic.core.search.WebSearch;
import logic.core.search.WebSearchImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class WebSearchServiceImpl implements WebSearchService {
    @Override
    public void searchResultInWebBrowser(String imageFileName,String language) throws IOException, URISyntaxException {
        UsefulTesseract ocr = new UsefulTesseract();
        ocr.scanTextWithImage(imageFileName,language);
        WebSearch search = new WebSearchImpl();
        search.runSearchInBrowser(ocr.getResult());
    }

    @Override
    public void searchResultInWebBrowser(String imageFileName) throws IOException, URISyntaxException {
        searchResultInWebBrowser(imageFileName,UsefulTesseract.Language.EN.get());
    }
}
