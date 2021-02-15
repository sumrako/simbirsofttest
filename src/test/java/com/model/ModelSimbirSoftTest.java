package com.model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ModelSimbirSoftTest {
    private Model model;

    @Test
    public void testRegex() {
        String regex = Model.getRegexSeparators();
        String[] expectedMas = {"Programming", "languages", "are", "popular"};

        String testString1 = "Programming, languages(', ')[', ']are!popular";
        String testString2 = "Programming,,,,, languages   are: popular?";
        String testString3 = " Programming[', ']languages\nare                  popular";

        assertArrayEquals(expectedMas, testString1.split(regex));
        assertArrayEquals(expectedMas, testString2.split(regex));
        assertArrayEquals(expectedMas, testString3.split(regex));
    }

}