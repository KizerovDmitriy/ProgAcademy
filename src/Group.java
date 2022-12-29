import java.util.Arrays;


public class Group {

    private String groupName;
    private Student[] students = new Student[10];

    public Group(String groupName, Student[] students) {
        this.groupName = groupName;
        this.students = students;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void sortStudentsByLastName() {
        Student[] sort = this.students.clone();
        Arrays.sort(sort, new LastNameComparator());
        for (Student student : sort) {
            System.out.println(student.toString());
        }
    }

    public void addStudent(Student student) throws GroupOverflowException {

        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            } else if (students[students.length - 1] != null) {
                throw new GroupOverflowException();
            }
        }
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (Student student : students) {
            if ((student.getLastName()).equalsIgnoreCase(lastName)) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    public boolean removeStudentByID(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                students[i] = null;
                return true;
            }
        }
        return false;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public String toString() {

        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }

}