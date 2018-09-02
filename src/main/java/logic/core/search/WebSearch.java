package logic.core.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface WebSearch {
    void runSearchInBrowser(String content) throws IOException, URISyntaxException;
}
