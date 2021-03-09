package com.model;

public interface WebProcessed {
    void webPageProcessing();
    void save(String page);
    void countUniqueWords(String content);
}
