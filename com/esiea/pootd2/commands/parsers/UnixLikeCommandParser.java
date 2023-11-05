package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.ChangeDirectoryCommand;
import com.esiea.pootd2.commands.Command;
import com.esiea.pootd2.commands.ErrorCommand;
import com.esiea.pootd2.commands.ListCommand;
import com.esiea.pootd2.commands.MakeDirectoryCommand;
import com.esiea.pootd2.commands.TouchCommand;

public class UnixLikeCommandParser implements ICommandParser {
    @Override
    public Command parse(String input) {
        return mapCommand(splitArguments(input));
    }

    public String[] splitArguments(String s) {
        return s.split(" ");
    }

    public String removeCommandNameAndJoin(String[] arguments) {
        String s = "";

        for (int i = 1; i < arguments.length; i++) {
            s += arguments[i];
        }

        return s;
    }

    public Command mapCommand(String[] arguments) {
        Command command;

        switch (arguments[0]) {
            case "cd": {
                command = new ChangeDirectoryCommand(removeCommandNameAndJoin(arguments));
                break;
            }

            case "ls": {
                command = new ListCommand();
                break;
            }

            case "mkdir": {
                command = new MakeDirectoryCommand(removeCommandNameAndJoin(arguments));
                break;
            }

            case "touch": {
                command = new TouchCommand(removeCommandNameAndJoin(arguments));
                break;
            }

            default: {
                command = new ErrorCommand("Le terme \"" + arguments + "\" n'est pas reconnu");
                break;
            }
        }

        return command;
    }
}
