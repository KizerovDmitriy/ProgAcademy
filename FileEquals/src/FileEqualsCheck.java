import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileEqualsCheck {

    public boolean equalsFile(File file1, File file2) throws IOException {
        byte[] array1 = new byte[(int) file1.length()];
        byte[] array2 = new byte[(int) file2.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file1);
             FileInputStream fileInputStream1 = new FileInputStream(file2)) {
            if (array1.length == array2.length) {
                array1 = fileInputStream.readAllBytes();
                array2 = fileInputStream1.readAllBytes();
                return Arrays.equals(array1, array2);
            } else {
                return false;
            }
        }
    }

    public boolean equalsFile(String[] args) throws IOException {
        File[] fileArray;
        if (args != null && args.length != 0) {
            fileArray = new File[args.length];
            for (int i = 0; i < args.length; i++) {
                fileArray[i] = new File(args[i]);
            }
            for (int i = 0; i < fileArray.length - 1; i++) {
                return equalsFile(fileArray[i], fileArray[i + 1]);
            }
        }
        return false;
    }
}