package com;

import com.model.HtmlProcess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HtmlProcess model = new HtmlProcess();
        System.out.println("Enter URL of site:");
        Scanner scanner = new Scanner(System.in);
        model.webPageProcessing();
        scanner.close();
    }
}
