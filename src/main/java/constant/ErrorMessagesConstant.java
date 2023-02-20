package constant;

public enum ErrorMessagesConstant {
    SOURCE_FOLDER_NOT_EXIST("Source folder is not existed!"),
    DESTINATION_FOLDER_NOT_EXIST("Destination folder is not existed!");
    private final String value;

    ErrorMessagesConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return "Prints Err: <" + this.value + ">";
    }
}
