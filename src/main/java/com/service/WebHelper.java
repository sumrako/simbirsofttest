package com.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebHelper {
    private WebHelper(){}

    public static Document getDocument(String url) throws IllegalArgumentException {
        while (true) {
            try {
                return Jsoup.connect(url).get();
            } catch (IllegalArgumentException | IOException e) {
                Logging.log(e, WebHelper.class);
                System.err.println("Error. Uncorrect URL");
                throw new IllegalArgumentException();
            } catch (OutOfMemoryError e) {
                Logging.log(e, WebHelper.class);
                System.exit(1);
            }
        }
    }
}
