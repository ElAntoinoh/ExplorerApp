package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.ChangeDirectoryCommand;
import com.esiea.pootd2.commands.Command;
import com.esiea.pootd2.commands.ErrorCommand;
import com.esiea.pootd2.commands.ListCommand;
import com.esiea.pootd2.commands.MakeDirectoryCommand;
import com.esiea.pootd2.commands.TouchCommand;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FolderInode;
import com.esiea.pootd2.models.Inode;

public class ExplorerController implements IExplorerController {
    private UnixLikeCommandParser parser = new UnixLikeCommandParser();

    private FolderInode currentFolder = new FolderInode("/");
    
    @Override
    public String executeCommand(String commandStr) {
        Command command = parser.parse(commandStr);

        String s = "";

        if (command instanceof ChangeDirectoryCommand) s = doCommand((ChangeDirectoryCommand) command);
        if (command instanceof ErrorCommand          ) s = doCommand((ErrorCommand          ) command);
        if (command instanceof ListCommand           ) s = doCommand((ListCommand           ) command);
        if (command instanceof MakeDirectoryCommand  ) s = doCommand((MakeDirectoryCommand  ) command);
        if (command instanceof TouchCommand          ) s = doCommand((TouchCommand          ) command);
        
        return s;
    }

    public String doCommand(ChangeDirectoryCommand command) {
        String s = "";

        // Absolute path
        if (command.getArgs()[0].startsWith("/")) {
            
        }

        // Relative path
        else {

        }

        return s;
    }

    public String doCommand(ErrorCommand command) {
        return "Le terme \"" + command.getArgs()[0] + "\" n'est pas reconnu.";
    }

    public String doCommand(ListCommand command) {
        return "Commande \"ListCommand\" lancée";
    }

    public String doCommand(MakeDirectoryCommand command) {
        String s = "";

        String[] args = command.getArgs();

        if (args.length >= 1) {
            for (int i = 0; i < args.length; i++) {
                if (currentFolder.findChildFolder(args[i]) == null) {
                    currentFolder.addInode(new FolderInode(command.getArgs()[i]));
                } else {
                    if (!s.equals("")) s += "\n";

                    s += "mkdir: impossible de créer le répertoire \"" + args[i] + "\": Le fichier existe";
                }
            }
        } else {
            s = "mkdir: opérande manquant";
        }

        return s;
    }

    public String doCommand(TouchCommand command) {
        return "Commande \"TouchCommand\" lancée";
    }
}
