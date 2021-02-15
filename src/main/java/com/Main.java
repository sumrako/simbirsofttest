package com;

import com.model.Model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);
        model.webPageProcessing();
        scanner.close();
    }
}
