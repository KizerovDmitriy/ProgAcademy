import java.io.*;

public class GroupFileStorage {

    private File pathToFile;

    void saveGroupToCSV(Group gr) throws IOException {
        pathToFile = new File("C:\\Users\\Win 7\\Desktop\\workFolder\\" + gr.getName());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Students s : gr.getGroupList()) {
                bufferedWriter.write(s.getName() + "," + s.getLastName() + "," + s.getAge());
                bufferedWriter.newLine();
            }
        }
    }

    Group loadGroupFromCSV(File file) throws Exception {
        Group group = new Group(file.getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String[] result = reader.readLine().split(",");
                group.addStudent(new Students(result[0], result[1], Integer.parseInt(result[2])));
            }
        }
        return group;
    }

    File findFileByGroupName(String groupName, File workFolder) {
        String result = "";
        File[] files = workFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getAbsolutePath().equals(workFolder.getAbsolutePath() + "\\" + groupName + ".txt") && file.isFile()) {
                    return new File(workFolder.getAbsolutePath() + "\\" + groupName + ".txt");
                }
            }
        }
        return null;
    }

    public File getPathToFile() {
        return pathToFile;
    }
}