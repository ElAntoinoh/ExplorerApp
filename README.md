# ExplorerApp

## Utilisation du projet
### Compilation
Linux : javac @compile.list<br/>
Windows : javac "@compile.list"

### Execution
java com.esiea.pootd2.ExplorerApp

## Documentation

### Liste des commandes

#### cd (ChangeDirectory)
__Particularités__:<br/>
"cd" équivaut à "cd /"

__Capacités__:<br/>
Possibilité de suivre des chemins relatifs ainsi que des chemins absolus<br/>
Possibilité de suivre un chemin nécessitant de traverser plusieurs dossiers<br/>
Possibilité d'utiliser les mots clés ".." et "."<br/>
Aucun déplacement résiduel en cas d'échec de la commande

__Erreurs possibles à l'utilisation__:<br/>
Trop d'arguments passés en paramètres : "cd: trop d'arguments"<br/>
Tentative de déplacement dans un inode innexistant : "cd: [inode]: Aucun fichier ou dossier de ce type"<br/>
Tentative de déplacement dans un fichier : "cd: [inode]: N'est pas un dossier"

#### ls (List)
__Particularités__:<br/>
Une ligne par fichier fils

Structure de l'affichage d'un fichier : type_fichier, nom_fichier, poids_fichier

type_fichier :
    - d : répertoire
    - f : autre

__Pistes d'amélioration__:<br/>
Possibilité d'exécuter la commande sur des dossiers passés en paramètre

#### mkdir (MakeDirectory)
__Capacités__:<br/>
Possibilité de créer plusieurs dossiers à la fois

__Erreurs possibles à l'utilisation__:<br/>
Pas assez d'arguments passés en paramètres : "mkdir: opérande manquant"<br/>
Tentative de création d'un répertoire dont le nom est déjà pris : "mkdir: impossible de créer le répertoire [répertoire]: Le fichier existe"

__Pistes d'amélioration__:<br/>
Possibilité de suivre des chemins relatifs ainsi que des chemins absolus<br/>
Possibilité de suivre un chemin nécessitant de traverser plusieurs dossiers<br/>
Possibilité d'utiliser les mots clés ".." et "."

#### touch (Touch)
__Capacités__:<br/>
Possibilité de touch plusieurs fichiers à la fois

__Erreurs possibles à l'utilisation__:<br/>
Pas assez d'arguments passés en paramètres : "touch: opérande de fichier manquant"

__Pistes d'amélioration__:<br/>
Possibilité de suivre des chemins relatifs ainsi que des chemins absolus<br/>
Possibilité de suivre un chemin nécessitant de traverser plusieurs dossiers<br/>
Possibilité d'utiliser les mots clés ".." et "."
