import dto.FileObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.directory.DirectoryUtils;

import java.util.List;

public class DirectoryUtilsTest {

    DirectoryUtils directoryUtils = new DirectoryUtils();

    @Test
    @DisplayName("Test if whether or not the path exists (positive test)")
    void pathCheckerPositiveTest() {
        Boolean directoryExisted = directoryUtils.pathChecker("src/main/resources/example_folders/source_folder_a", false);
        Assertions.assertEquals(true, directoryExisted);
    }

    @Test
    @DisplayName("Test if whether or not the path exists (negative test)")
    void pathCheckerNegativeTest() {
        Boolean directoryExisted = directoryUtils.pathChecker("src/main/resources/example_folders/source_folder_z", false);
        Assertions.assertEquals(false, directoryExisted);
    }

    @Test
    @DisplayName("Test method that traverse files inside folder")
    void getListFilesTest() {
        List<FileObject> files = directoryUtils.getListFiles("src/main/resources/example_folders/source_folder_a");
        Assertions.assertEquals(3, files.size());
    }

    @Test
    @DisplayName("Test the method that count files from destination folder that don't exist in source folder")
    void unExistedFilesTest() {
        List<FileObject> unExistedFiles = directoryUtils.findUnExistedFiles("src/main/resources/example_folders/source_folder_a", "src/main/resources/example_folders/source_folder_b");
        Assertions.assertEquals(1, unExistedFiles.size());
    }

    @Test
    @DisplayName("Test the method that check whether or not the file is exist inside folder (positive test)")
    void fileExistPositiveTest() {
        List<FileObject> files = directoryUtils.getListFiles("src/main/resources/example_folders/source_folder_a");
        FileObject file = new FileObject("file_a", "src/main/resources/example_folders/source_folder_b/file_a", "src/main/resources/example_folders/source_folder_b");
        Assertions.assertEquals(true, directoryUtils.fileExist(file, files));
    }

    @Test
    @DisplayName("Test the method that check whether or not the file is exist inside folder (negative test)")
    void fileExistNegativeTest() {
        List<FileObject> files = directoryUtils.getListFiles("src/main/resources/example_folders/source_folder_a");
        FileObject file = new FileObject("file_d", "src/main/resources/example_folders/source_folder_b/file_d", "src/main/resources/example_folders/source_folder_d");
        Assertions.assertEquals(false, directoryUtils.fileExist(file, files));
    }
}
