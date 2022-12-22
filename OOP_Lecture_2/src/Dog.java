public class Dog extends Animal {

    private String name;

    public Dog(String ration, String color, int weight, String name) {
        super(ration, color, weight);
        this.name = name;
    }

    @Override
    public String getVoice() {
        return "Gaw-gaw";
    }

    @Override
    public void eat() {
        System.out.println("Eat "+getRation());
    }

    public String getName() {
        return name;
    }

    @Override
    public void sleep() {
        System.out.println("Sleep "+getName());
    }
}
