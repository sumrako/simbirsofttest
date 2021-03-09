package com.model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ModelSimbirSoftTest {
    @Test
    public void testRegex() {
        String regex = HtmlProcess.getRegexSeparators();
        String[] expectedMas = {"Programming", "languages", "are", "popular"};

        String testString1 = "Programming, languages(, )[, are!popular]";
        String testString2 = "Programming,,,,, \"languages   are: popular?";

        assertArrayEquals(expectedMas, testString1.split(regex));
        assertArrayEquals(expectedMas, testString2.split(regex));
    }

}