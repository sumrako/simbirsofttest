package com.model;

import com.service.Logging;
import com.service.Outputs;
import com.service.WebHelper;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class HtmlProcess implements WebProcessed {
    private final static String REGEX_SEPARATORS = "(\\s|,|\\t|\\.|!|\\?|\\\"|;|:|\\[|\\]|\\(|\\))+";

    private String fileName;

    public void webPageProcessing() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String url = reader.readLine();

            Document document = WebHelper.getDocument(url);
            fileName = new URI(url).getHost();

            String pageInString = document.html();
            save(pageInString);

            String content = document.text();
            countUniqueWords(content);

            reader.close();
        } catch (URISyntaxException | IOException e) {
            Logging.log(e, getClass());
            System.err.println("Error of input");
            System.exit(1);
        }
    }

    public void save(String page) {
        try {
            Path path = Path.of(String.format("pages\\%s%s.html", fileName, UUID.randomUUID()));
            Path directory = Path.of("pages");
            File pathToFile = path.toFile();

            if (page.getBytes("UTF-8").length > pathToFile.getUsableSpace())
                throw new FileSystemException("Don't have free space in disk");

            if (Files.notExists(directory))
                Files.createDirectory(Path.of("pages"));

            if (Files.notExists(path))
                Files.createFile(path);

            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile, false));
            writer.write(page);
            writer.close();
        } catch (FileSystemException e) {
            System.err.println("Don't have free space in disk");
            Logging.log(e, getClass());
        } catch (IOException e) {
            System.err.println("Error of file");
            Logging.log(e, getClass());
        }
    }

    public void countUniqueWords(String content) {
        String[] words = content.split(REGEX_SEPARATORS);
        Map<String, Integer> wordsCount = new HashMap<>();

        for (String word : words) {
            String curWord = word.toLowerCase(Locale.ROOT);
            if (wordsCount.containsKey(curWord)) {
                wordsCount.put(curWord, wordsCount.get(curWord) + 1);
            } else {
                wordsCount.put(curWord, 1);
            }
        }

        Outputs.consoleOutput(wordsCount);
    }

    public static String getRegexSeparators() {
        return REGEX_SEPARATORS;
    }
}
