import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Students> groupList = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Students students) {
        this.groupList.add(students);
    }

    public List<Students> getGroupList() {
        return groupList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupList=" + groupList +
                '}';
    }
}