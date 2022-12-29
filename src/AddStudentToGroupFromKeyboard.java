import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddStudentToGroupFromKeyboard {

    public Student createNewStudent() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            return new Student(reader.readLine(), reader.readLine(), Gender.valueOf(reader.readLine()),Integer.parseInt(reader.readLine()), reader.readLine());
        }
    }

    public void addStudentToGroup (Group group)  {
        try {
            group.addStudent(createNewStudent());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
