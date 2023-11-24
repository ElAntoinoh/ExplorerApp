package com.esiea.pootd2;

import java.util.Scanner;

import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.interfaces.HttpInterface;
import com.esiea.pootd2.interfaces.TextInterface;

public class ExplorerApp {
    private final static String TEXT_KEYWORD = "text";
    private final static String HTTP_KEYWORD = "http";

    private final static String STARTING_STRING = "Mode d'exécution (" + TEXT_KEYWORD + " / " + HTTP_KEYWORD + ") : ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = "";

        do {
            System.out.print(STARTING_STRING);
            input = sc.nextLine();
        } while (!input.equals(TEXT_KEYWORD) && !input.equals(HTTP_KEYWORD));

        ExplorerController explorerController = new ExplorerController();

        switch (input) {
            case TEXT_KEYWORD -> { new TextInterface(explorerController).run(); }
            case HTTP_KEYWORD -> { new HttpInterface(explorerController).run(); }
        }

        sc.close();
    }
}