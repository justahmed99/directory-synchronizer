import utils.command.CommandUtils;

@Credit(
        title = "DirectorySynchronizer",
        subTitle = "This project is created for AccelByte Technical Test - Backend Engineer",
        creator = "Ahmad Mujahid Abdurrahman",
        javaVersion = "Java 11",
        jdkVersion = "11.0.12",
        ideInDev = "IntelliJ",
        jarOutputTested = {"Windows 10, Ubuntu 20.04"}
)

public class DirectorySynchronizer {
    public static void main(String[] args) {
        CommandUtils commandUtils = new CommandUtils();
        commandUtils.runCommand(args);
    }
}
