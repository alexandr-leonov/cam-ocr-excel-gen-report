package logic.possibilities.search;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WebSearchService {

    void searchResultInWebBrowser(String imageFileName,String language) throws IOException, URISyntaxException;

    void searchResultInWebBrowser(String imageFileName) throws IOException, URISyntaxException;
}
