import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyFiles {

    public static void main(String[] args) {

        /*

        // copy files to output directory

        String inputDirectory = "C:\\Users\\Win 7\\Desktop\\input";
        String outputDirectory = "C:\\Users\\Win 7\\Desktop\\output";

        copyDocFiles(inputDirectory, outputDirectory);

*/
        //save group to csv file

        Group group = new Group("Programming");
        group.addStudent(new Students("Alex", "Shelbi", 18));
        group.addStudent(new Students("James", "Alison", 22));

        GroupFileStorage groupFileStorage = new GroupFileStorage();
        try {
            groupFileStorage.saveGroupToCSV(group);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //recovery group from file

        File fileRecovery = new File("C:\\Users\\Win 7\\Desktop\\workFolder\\it.txt");
        try {
            System.out.println(groupFileStorage.loadGroupFromCSV(fileRecovery).toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //find file
        File findFile = new File("C:\\Users\\Win 7\\Desktop\\workFolder\\");
        System.out.println(groupFileStorage.findFileByGroupName("dmitriy", findFile).toString());
    }


    public static void copyDocFiles(String from, String to) {

        File fileFrom = new File(from);
        Path fileTo = Path.of(to);
        File[] list = fileFrom.listFiles();

        if (list != null) {
            for (File files : list) {
                if (files.isFile()) {
                    String name = files.getAbsolutePath();
                    if (name.endsWith(".doc") || name.endsWith(".docx")) {
                        File directory = new File(name);
                        try {
                            Files.copy(directory.toPath(), fileTo.resolve(files.getName()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}