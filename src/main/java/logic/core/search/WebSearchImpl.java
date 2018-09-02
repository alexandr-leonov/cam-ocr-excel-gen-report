package logic.core.search;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSearchImpl implements WebSearch {

    @Override
    public void runSearchInBrowser(String content) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(prepareContent(content)));
    }

    private String prepareContent(String content) {
        StringBuilder result = new StringBuilder("https://www.google.com/search?q=");
        String main = content.replaceAll(" ", "%20")
                .replaceAll("\\+", "%2B")
                .replaceAll("=", "%3D")
                .replaceAll("/", "%2F")
                .replaceAll("\\\\", "%5C")
                .replaceAll(",", "%2C")
                .replaceAll("\\?", "%3F")
                .replaceAll(":", "%3A")
                .replaceAll("\\'","%27")
                .replaceAll("\n","");
        result.append(main);
        return result.toString();
    }
}
