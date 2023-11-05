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

    public String[] removeCommandName(String[] input) {
        String[] output = new String[input.length - 1];

        for (int i = 0; i < output.length; i++) {
            output[i] = input[i + 1];
        }

        return output;
    }

    public Command mapCommand(String[] arguments) {
        Command command;

        switch (arguments[0]) {
            case "cd": {
                command = new ChangeDirectoryCommand(removeCommandName(arguments));
                break;
            }

            case "ls": {
                command = new ListCommand(removeCommandName(arguments));
                break;
            }

            case "mkdir": {
                command = new MakeDirectoryCommand(removeCommandName(arguments));
                break;
            }

            case "touch": {
                command = new TouchCommand(removeCommandName(arguments));
                break;
            }

            default: {
                command = new ErrorCommand(arguments);
                break;
            }
        }

        return command;
    }
}
