package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.ChangeDirectoryCommand;
import com.esiea.pootd2.commands.Command;
import com.esiea.pootd2.commands.ErrorCommand;
import com.esiea.pootd2.commands.ListCommand;
import com.esiea.pootd2.commands.MakeDirectoryCommand;
import com.esiea.pootd2.commands.TouchCommand;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FileInode;
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

    private String doCommand(ChangeDirectoryCommand command) {
        String s = "";

        String[] args = command.getArgs();

        switch (args.length) {
            case 0 -> { this.currentFolder = goToRoot(this.currentFolder); }

            case 1 -> {
                String path = args[0];

                // Chemin virtuel pour s'assurer de la possiblité du déplacement
                FolderInode currentFolderCopy = currentFolder;

                // Cas d'un chemin absolu
                if (path.startsWith("/")) {
                    path = path.substring(1, path.length());
                    currentFolderCopy = goToRoot(currentFolderCopy);
                }

                // Liste des étapes du chemin
                String[] steps = path.split("/");

                // Parcours des étapes du chemin
                for (int i = 0; i < steps.length; i++) {
                    String step = steps[i];

                    // On souhaite aller dans le dossier parent
                    if (step.equals("..")) {
                        if (currentFolderCopy.getParent() != null) {
                            currentFolderCopy = currentFolderCopy.getParent();
                        }
                    }
                    
                    // On souhaite se déplacer dans dossier enfant
                    else if (!step.equals("") && !step.equals(".")) {
                        Inode foundChildren = currentFolderCopy.findChild(step);

                        // Si on trouve un enfant
                        if (foundChildren != null) {
                            // Si l'enfant est un dossier
                            if (foundChildren instanceof FolderInode) {
                                currentFolderCopy = (FolderInode) foundChildren;
                            }
                            
                            // Si l'enfant n'est pas un dossier
                            else {
                                s = "cd: " + step + ": N'est pas un dossier";
                                break;
                            }
                        } 
                        
                        // Si on ne trouve pas d'enfant
                        else {
                            s = "cd: " + step + ": Aucun fichier ou dossier de ce type";
                            break;
                        }
                    }
                }

                // Si aucune erreur n'a été affichée, on effectue le déplacement
                if (s.equals("")) this.currentFolder = currentFolderCopy;
            }

            default -> { s = "cd: trop d'arguments"; }
        }

        return s;
    }

    private String doCommand(ErrorCommand command) {
        return "Le terme \"" + command.getArgs()[0] + "\" n'est pas reconnu.";
    }

    private String doCommand(ListCommand command) {
        String s = "";

        // Parcours des enfants du dossier courant
        for (Inode inode : this.currentFolder.getChildren()) {
            // Affichage du type de fichier
            if (inode instanceof FolderInode) s += "d\t";
            else                              s += "f\t";

            // Affichage du nom du fichier
            s += inode.getName() + "\t";

            // Affichage de la taille du fichier
            s += inode.getSize() + "\n";
        }

        return s;
    }

    private String doCommand(MakeDirectoryCommand command) {
        String s = "";

        FolderInode currentFolderCopy;

        String[] args = command.getArgs();

        // Vérification du nombre d'arguments
        if (args.length >= 1) {
            // Parcours des arguments
            for (String arg : args) {
                // Chemin virtuel pour s'assurer de la possiblité du déplacement
                currentFolderCopy = currentFolder;

                // Cas d'un chemin absolu
                if (arg.startsWith("/")) {
                    arg = arg.substring(1, arg.length());
                    currentFolderCopy = goToRoot(currentFolderCopy);
                }

                // Récupération des étapes du chemin du dossier à créer
                String[] steps = arg.split("/");

                // Parcours des étapes du chemin du dossier à créer
                for (int i = 0; i < steps.length; i++) {
                    if (i != steps.length - 1) {
                        Inode child = currentFolderCopy.findChild(steps[0]);

                        // Si un enfant est trouvé
                        if (child != null) {
                            // Si l'enfant trouvé est un dossier
                            if (child instanceof FolderInode) {
                                currentFolderCopy = (FolderInode) child;
                            }

                            // Si l'enfant trouvé est un fichier
                            else {
                                s += "mkdir: impossible de créer le répertoire \"" + arg + "\": N'est pas un dossier";
                            }
                        }

                        // Si aucun enfant n'est trouvé
                        else {
                            s += "mkdir: impossible de créer le répertoire \"" + arg + "\": Aucun fichier ou dossier de ce type";
                        }
                    }

                    else {
                        if (currentFolderCopy.findChild(arg) == null) {
                            currentFolderCopy.addInode(new FolderInode(arg));
                        } else {
                            if (!s.equals("")) s += "\n";

                            s += "mkdir: impossible de créer le répertoire \"" + arg + "\": Le fichier existe";
                        }
                    }
                }
            }
        } else {
            s = "mkdir: opérande manquant";
        }

        /*
        if (args.length >= 1) {
            for (String arg : args) {
                if (this.currentFolder.findChild(arg) == null) {
                    this.currentFolder.addInode(new FolderInode(arg));
                } else {
                    if (!s.equals("")) s += "\n";

                    s += "mkdir: impossible de créer le répertoire \"" + arg + "\": Le fichier existe";
                }
            }
        } else {
            s = "mkdir: opérande manquant";
        }
        */

        /*
        if (args.length >= 1) {
            for (int i = 0; i < args.length; i++) {
                if (this.currentFolder.findChild(args[i]) == null) {
                    this.currentFolder.addInode(new FolderInode(args[i]));
                } else {
                    if (!s.equals("")) s += "\n";

                    s += "mkdir: impossible de créer le répertoire \"" + args[i] + "\": Le fichier existe";
                }
            }
        } else {
            s = "mkdir: opérande manquant";
        }
        */

        return s;
    }

    private String doCommand(TouchCommand command) {
        String s = "";

        String[] args = command.getArgs();

        if (args.length >= 1) {
            for (int i = 0; i < args.length; i++) {
                if (this.currentFolder.findChild(args[i]) == null) {
                    this.currentFolder.addInode(new FileInode(args[i]));
                }
            }
        } else {
            s = "touch: opérande de fichier manquant";
        }

        return s;
    }

    private FolderInode goToRoot(FolderInode folder) {
        while (folder.getParent() != null) {
            folder = (FolderInode) folder.getParent();
        }

        return folder;
    }
}
