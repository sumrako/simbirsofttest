package com.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.service.ErrorHanding;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Model {
    private final static String REGEX_SEPARATORS = "(\\s|,|\t|\\.|!|\\?|\"|;|:|(\\[', '\\])|(\\(', '\\)))+";

    private String fileName;
    private Map<String, Integer> wordsCount = new HashMap<>();
    private BufferedReader reader;
    private String url;

    public void webPageProcessing() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            url = reader.readLine();

            Document document = getDocument(url);
            fileName = new URI(url).getHost();

            String pageInString = document.html();
            saveHtmlPage(pageInString);

            String content = document.text();
            countUniqueWords(content);

            reader.close();
        } catch (URISyntaxException | IOException e) {
            ErrorHanding.errorHanding(e, getClass());
        }
    }

    private Document getDocument(String url) throws IOException {
        while (true) {
            try {
                return Jsoup.connect(url).get();
            } catch (IllegalArgumentException | IOException e) {
                ErrorHanding.errorHanding(e, getClass());
                System.out.println("Error. Please repeat input URL:");

                url = reader.readLine();
            } catch (OutOfMemoryError e) {
                ErrorHanding.errorHanding(e, getClass());
                System.exit(1);
            }
        }
    }

    private void saveHtmlPage(String page) {
        try {
            Path path = Path.of(String.format("pages\\%s.html", fileName));
            Path directory = Path.of("pages");

            if (Files.notExists(directory))
                Files.createDirectory(Path.of("pages"));

            if (Files.notExists(path))
                Files.createFile(path);

            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
            writer.write(page);
            writer.close();
        } catch (IOException e) {
            ErrorHanding.errorHanding(e, getClass());
        }
    }

    private void countUniqueWords(String content) {
        String[] words = content.split(REGEX_SEPARATORS);

        for (String word : words) {
            String curWord = word.toLowerCase(Locale.ROOT);
            if (wordsCount.containsKey(curWord)) {
                wordsCount.put(curWord, wordsCount.get(curWord) + 1);
            } else {
                wordsCount.put(curWord, 1);
            }
        }

        for (Map.Entry<String, Integer> pair : wordsCount.entrySet())
            System.out.printf("%S - %d\n", pair.getKey(), pair.getValue());

    }

    public static String getRegexSeparators() {
        return REGEX_SEPARATORS;
    }
}
