package utils.directory;

import constant.ErrorMessagesConstant;
import dto.FileObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectoryUtils class contains all methods related to file / folder operations that are used
 * by this program
 */
public class DirectoryUtils {
    /**
     * @param sourceDir absolute or relative filepath of source directory
     * @param destinationDir absolute or relative filepath of destination directory
     */
    public void operation(String sourceDir, String destinationDir) {
        Boolean sourcePathChecking = pathChecker(sourceDir, false);
        Boolean destinationPathChecking = pathChecker(destinationDir, true);
        if (sourcePathChecking && destinationPathChecking) {
            deleteUnExistedFiles(sourceDir, destinationDir);
            copyFiles(sourceDir, destinationDir);
        }
    }

    /**
     * @param sourceDir absolute or relative filepath of source directory
     * @param destinationDir absolute or relative filepath of destination directory
     */
    public void deleteUnExistedFiles(String sourceDir, String destinationDir) {
        List<FileObject> unExistedFiles = findUnExistedFiles(sourceDir, destinationDir);
        unExistedFiles.forEach(it -> {
            try {
                Files.delete(Path.of(it.getFilePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param sourceDir absolute or relative filepath of source directory
     * @param destinationDir absolute or relative filepath of destination directory
     */
    public void copyFiles(String sourceDir, String destinationDir) {
        Path sourceDirPath = Paths.get(sourceDir);
        Path destinationDirPath = Paths.get(destinationDir);

        try {
            // Files.walk is used to traverse all files inside directory (including subdirectories)
            Files.walk(sourceDirPath)
                    .forEach(sourcePath -> {
                        try {
                            Path destinationPath = destinationDirPath.resolve(sourceDirPath.relativize(sourcePath));
                            if (!Files.isDirectory(destinationPath)) {
                                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param directory absolute or relative filepath of certain directory
     * @param isDestinationDir flagging whether the directory is destination directory
     * @return true or false (boolean)
     */
    public Boolean pathChecker(String directory, Boolean isDestinationDir) {
        Path destinationPath = Paths.get(directory);
        if (!Files.exists(destinationPath)) {
            System.out.println(isDestinationDir ? ErrorMessagesConstant.DESTINATION_FOLDER_NOT_EXIST.getValue()
                    : ErrorMessagesConstant.SOURCE_FOLDER_NOT_EXIST.getValue());
            return false;
        }
        return true;
    }

    /**
     * @param directory absolute or relative filepath of certain directory
     * @return list of FileObject contained by the directory
     */
    public List<FileObject> getListFiles(String directory) {
        Path destinationPath = Paths.get(directory);
        List<FileObject> files = new ArrayList<>();
        try {
            // Files.walk is used to traverse all files inside directory (including subdirectories)
            Files.walk(destinationPath)
                    .forEach(filePath -> {
                        if (!Files.isDirectory(filePath))
                            // add the files to list
                            files.add(new FileObject(filePath.getFileName().toString(), filePath.toString(),
                                    filePath.getParent().toString()));
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return files;
    }

    /**
     * @param sourceDir absolute or relative filepath of source directory
     * @param destinationDir absolute or relative filepath of destination directory
     * @return list of files from destination directory that do not exist inside source directory
     */
    public List<FileObject> findUnExistedFiles(String sourceDir, String destinationDir) {
        List<FileObject> sourceDirFilesList = getListFiles(sourceDir);
        List<FileObject> destinationDirFilesList = getListFiles(destinationDir);
        List<FileObject> unExistedFiles = new ArrayList<>();
        destinationDirFilesList.forEach(file -> {
            if (!fileExist(file, sourceDirFilesList))
                unExistedFiles.add(file);
        });

        return unExistedFiles;
    }

    /**
     * @param file FileObject of certain file that will be checked
     * @param fileObjectList list of FileObject that become the reference for checking
     * @return true or false (boolean), true means the file exists in fileObjectList, false means otherwise
     */
    public Boolean fileExist(FileObject file, List<FileObject> fileObjectList) {
        boolean status = false;
        for (FileObject fileObject : fileObjectList) {
            if (fileObject.getFileName().equals(file.getFileName())) {
                status = true;
                break;
            }
        }
        return status;
    }
}
