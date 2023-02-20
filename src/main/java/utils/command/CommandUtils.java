package utils.command;

import utils.directory.DirectoryUtils;

/**
 * CommandUtils class consists of methods that are related to handling user arguments input
 */
public class CommandUtils {
    DirectoryUtils directoryUtils = new DirectoryUtils();

    /**
     * @param args list of arguments are inputted by user
     */
    public void runCommand(String[] args) {
        if (!argsLengthCheck(args)) {
            invalidCommandMessage();
            return;
        }

        if (!syncCommandExistenceCheck(args)) {
            System.out.println("Prints Err: <Invalid command! '" + args[0] + "' is not recognized.>");
            return;
        }

        String source = "";
        if (args[1].equals("-s")) {
            source = args[2];
        } else if (args[3].equals("-s")) {
            source = args[4];
        }

        String target = "";
        if (args[1].equals("-d")) {
            target = args[2];
        } else if (args[3].equals("-d")) {
            target = args[4];
        }

        if (!commandInvalidation(args)) {
            invalidCommandMessage();
            return;
        }

        directoryUtils.operation(source, target);


    }

    /**
     * @param args list of arguments are inputted by user
     * @return true or false (boolean), true if arguments length is equal to 5, false if otherwise
     */
    public Boolean argsLengthCheck(String[] args) {
        return args.length == 5;
    }

    /**
     * @param args list of arguments are inputted by user
     * @return true or false (boolean), true if the first args is "sync", false if otherwise
     */
    public Boolean syncCommandExistenceCheck(String[] args) {
        return args[0].equals("sync");
    }

    /**
     * @param args list of arguments are inputted by user
     * @return true or false (boolean), true if user arguments input is valid, false if otherwise
     */
    public Boolean commandInvalidation(String[] args) {
        if (args[1].equals("-s") && args[3].equals("-s")) {
            return false;
        } else if (args[1].equals("-d") && args[3].equals("-d")) {
            return false;
        } else if (args[1].equals("-d") && !args[3].equals("-s")) {
            return false;
        } else if (!args[1].equals("-d") && args[3].equals("-s")) {
            return false;
        } else if (args[1].equals("-s") && !args[3].equals("-d")) {
            return false;
        } else if (!args[1].equals("-s") && args[3].equals("-d")) {
            return false;
        }
        return true;
    }

    /**
     * invalidCommandMessage method is called whenever user input an invalid command params
     */
    private void invalidCommandMessage() {
        System.out.println("Prints Err: <Insufficient command!>");
        System.out.println("Prints Err: <The Command should be :>");
        System.out.println(
                "Prints Err: <   ============================== JAR VERSION ========================================    >");
        System.out.println(
                "Prints Err: <   java -jar DirectorySynchronizer.jar sync -s [source_folder] -d [destination_folder]    >");
        System.out.println(
                "Prints Err: <           or                                                                             >");
        System.out.println(
                "Prints Err: <   java -jar DirectorySynchronizer.jar sync -d [destination_folder] -s [source_folder]    >");
        System.out.println(
                "Prints Err: <   ============================== EXE VERSION ========================================    >");
        System.out.println(
                "Prints Err: <   .\\DirectorySynchronizer.exe sync -s [source_folder] -d [destination_folder             >");
        System.out.println(
                "Prints Err: <           or                                                                             >");
        System.out.println(
                "Prints Err: <   .\\DirectorySynchronizer.exe sync -d [destination_folder] -s [source_folder]            >");
    }
}
