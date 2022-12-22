public class Main {
    public static void main(String[] args) {

        Dog dog = new Dog("Meat","Black",20,"Spike");
        Cat cat = new Cat("Fish","White",5,"Tom");
        Veterinarian doctor = new Veterinarian("Aibolit");

        dog.eat();
        dog.sleep();
        System.out.println(dog.getVoice());

        cat.eat();
        cat.sleep();
        System.out.println(cat.getVoice());

        doctor.treatment(dog);
        doctor.treatment(cat);
    }
}