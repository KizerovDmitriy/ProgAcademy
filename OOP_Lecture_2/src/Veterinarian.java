public class Veterinarian {

    private String name;

    public Veterinarian(String name) {
        this.name = name;
    }

    public void treatment(Animal animal) {
        if (animal instanceof Cat) {
            System.out.println("Heal " + (((Cat) animal).getName()));
        }
        else if (animal instanceof Dog){
            System.out.println("Heal " + ((Dog) animal).getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "name='" + name + '\'' +
                '}';
    }
}