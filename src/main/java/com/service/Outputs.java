package com.service;

import java.util.Map;

public class Outputs {
    private Outputs() {
    }

    public static void consoleOutput(Map<String, Integer> content) {
        for (Map.Entry<String, Integer> pair : content.entrySet())
            System.out.printf("%S - %d\n", pair.getKey(), pair.getValue());
    }

    //Output in file, database, to form and other
}
