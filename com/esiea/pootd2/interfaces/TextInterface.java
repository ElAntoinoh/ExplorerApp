package com.esiea.pootd2.interfaces;

import java.util.Scanner;

import com.esiea.pootd2.controllers.IExplorerController;

public class TextInterface implements IUserInterface {
    private final static String EXIT_COMMAND  = "exit";
    private final static String PROMPT_ASPECT = "> ";

    private IExplorerController controller;

    public TextInterface(IExplorerController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        listen();
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);

        String input = "";

        while (!input.equals(EXIT_COMMAND)) {
            System.out.print(PROMPT_ASPECT);

            input = sc.nextLine();

            if (input.equals("")) continue;

            System.out.println(controller.executeCommand(input));
        }

        sc.close();
    }
}
