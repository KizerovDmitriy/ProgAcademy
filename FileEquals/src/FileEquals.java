import java.io.File;
import java.io.IOException;

public class FileEquals {
    public static void main(String[] args) {
        File file1 = new File("C:\\Users\\Win 7\\Desktop\\input\\file1.txt");
        File file2 = new File("C:\\Users\\Win 7\\Desktop\\input\\file2.txt");

        FileEqualsCheck fileEqualsCheck = new FileEqualsCheck();
        try {
            System.out.println(fileEqualsCheck.equalsFile(file1, file2));
            System.out.println("**************************************");
            System.out.println(fileEqualsCheck.equalsFile(args));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}