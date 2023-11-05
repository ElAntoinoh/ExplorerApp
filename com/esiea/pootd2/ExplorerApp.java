package com.esiea.pootd2;

import com.esiea.pootd2.commands.ChangeDirectoryCommand;
import com.esiea.pootd2.commands.Command;
import com.esiea.pootd2.commands.ErrorCommand;
import com.esiea.pootd2.commands.ListCommand;
import com.esiea.pootd2.commands.MakeDirectoryCommand;
import com.esiea.pootd2.commands.TouchCommand;

public class ExplorerApp {
    public static void main(String[] args) {
        Command c1 = new ListCommand();
        Command c2 = new ChangeDirectoryCommand("../home");
        Command c3 = new MakeDirectoryCommand("Documents");
        Command c4 = new TouchCommand("Photo_de_vacances.png");
        Command c5 = new ErrorCommand("Internal error");
    }
}