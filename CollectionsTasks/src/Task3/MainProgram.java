package Task3;

public class MainProgram {
    public static void main(String[] args) {
        FileReaderAndCount fileReaderAndCount = new FileReaderAndCount();
        fileReaderAndCount.readAndCount(args[0]);
    }
}