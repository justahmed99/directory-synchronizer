import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.command.CommandUtils;

public class CommandUtilsTest {
    CommandUtils commandUtils = new CommandUtils();
    String[] userCommandInput;

    @BeforeEach
    void setup() {
        userCommandInput = new String[]{"sync", "-d", "/path/destination_dir", "-s", "/path/source_dir"};
    }

    @Test
    @DisplayName("Argument length check (positive test)")
    void argumentLengthCheckPositiveTest() {
        Assertions.assertEquals(true, commandUtils.argsLengthCheck(userCommandInput));
    }

    @Test
    @DisplayName("Argument length check (negative test)")
    void argumentLengthCheckNegativeTest() {
        userCommandInput = new String[]{"sync", "-d", "/path/destination_dir", "-s"};
        Assertions.assertEquals(false, commandUtils.argsLengthCheck(userCommandInput));
    }

    @Test
    @DisplayName("Sync command existence check (positive test)")
    void syncCommandExistenceCheckPositiveTest() {
        Assertions.assertEquals(true, commandUtils.syncCommandExistenceCheck(userCommandInput));
    }

    @Test
    @DisplayName("Sync command existence check (negative test)")
    void syncCommandExistenceCheckNegativeTest() {
        userCommandInput[0] = "async";
        Assertions.assertEquals(false, commandUtils.syncCommandExistenceCheck(userCommandInput));
    }

    @Test
    @DisplayName("User command input invalidation (positive test)")
    void userCommandInputInvalidationPositiveTest() {
        Assertions.assertEquals(true, commandUtils.commandInvalidation(userCommandInput));
    }

    @Test
    @DisplayName("User command input invalidation (negative test)")
    void userCommandInputInvalidationNegativeTest() {
        userCommandInput[3] = "-d";
        Assertions.assertEquals(false, commandUtils.commandInvalidation(userCommandInput));
    }
}
