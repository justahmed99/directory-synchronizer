package dto;

public class FileObject {
    private String fileName;
    private String filePath;
    private String folderPath;

    public FileObject(String fileName, String filePath, String folderPath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.folderPath = folderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

}
