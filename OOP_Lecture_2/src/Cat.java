public class Cat extends Animal {

    private String name;

    public String getName() {
        return name;
    }

    public Cat(String ration, String color, int weight, String name) {
        super(ration, color, weight);
        this.name = name;
    }

    @Override
    public String getVoice() {
        return "Meow";
    }

    @Override
    public void eat() {
        System.out.println("Eat "+getRation());
    }

    @Override
    public void sleep() {
        System.out.println("sleep "+getName());
    }
}
