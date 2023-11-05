package com.esiea.pootd2.interfaces;

import java.util.Scanner;

public class TextInterface implements IUserInterface {
    private final static String EXIT_COMMAND  = "exit";
    private final static String PROMPT_ASPECT = "> ";

    @Override
    public void run() {
        listen();
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);

        String input;

        do {
            System.out.print(PROMPT_ASPECT);
        } while (!(input = sc.nextLine()).equals(EXIT_COMMAND));

        sc.close();
    }
}
