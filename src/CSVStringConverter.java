public class CSVStringConverter implements StringConverter {

    @Override
    public String toStringRepresentation(Student student) {
        return student.getName() + "," + student.getLastName() + "," + student.getGender() + "," + student.getId() + "," + student.getGroupName();
    }

    @Override
    public Student fromStringRepresentation(String str) {
        String[] result = str.split(",");
        return new Student(result[0], result[1], Gender.valueOf(result[2]), Integer.parseInt(result[3]), result[4]);
    }
}
