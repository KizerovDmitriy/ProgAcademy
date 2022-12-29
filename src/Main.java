public class Main {
    public static void main(String[] args) {

        Group group = new Group("ProgAcademy");
        Student studentArtem = new Student("Artem", "Kovtun", Gender.MALE, 11, "Unknown");

        try {
            group.addStudent(new Student("Ivanov", "Shevchenko", Gender.MALE, 1, "ProgAcademy"));
            group.addStudent(new Student("Dmitriy", "Kizerov", Gender.MALE, 2, "ProgAcademy"));
            group.addStudent(new Student("Oleksand", "Ichenko", Gender.MALE, 3, "ProgAcademy"));
            group.addStudent(new Student("Vladimir", "Kolomiec", Gender.MALE, 4, "ProgAcademy"));
            group.addStudent(new Student("Dmitriy", "Forsh", Gender.MALE, 5, "ProgAcademy"));
            group.addStudent(new Student("Tamila", "Kizerova", Gender.FEMALE, 6, "ProgAcademy"));
            group.addStudent(new Student("Yanina", "Forsh", Gender.FEMALE, 7, "ProgAcademy"));
            group.addStudent(new Student("Irina", "Andreeva", Gender.FEMALE, 8, "ProgAcademy"));
            group.addStudent(new Student("Vika", "Tyz", Gender.FEMALE, 9, "ProgAcademy"));
            //group.addStudent(new Student("Yuliya", "Prichodko", Gender.FEMALE, 10, "ProgAcademy"));

            //group.addStudent(studentArtem);   //add new student

            //System.out.println(group.searchStudentByLastName("tyz"));     //search by LastName
            //System.out.println(group.searchStudentByLastName("error"));   //search by LastName

            //System.out.println(group.removeStudentByID(1));     //remove Student
            //System.out.println(group.removeStudentByID(23));    //remove Student

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        AddStudentToGroupFromKeyboard add = new AddStudentToGroupFromKeyboard();
        add.addStudentToGroup(group);
        group.sortStudentsByLastName();
        */

        CSVStringConverter csv = new CSVStringConverter();
        String student = "Alex,Birman,MALE,12,Programing";
        System.out.println((csv.fromStringRepresentation(student)).toString());
        System.out.println(csv.toStringRepresentation(new Student("Alex","Birman", Gender.MALE,12,"Programing")));
    }
}