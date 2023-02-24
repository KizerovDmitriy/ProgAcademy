package classes;

import annotations.Save;


public class Client {
    @Save
    private String firstName;
    @Save
    private String lastName;
    @Save
    private Integer age;
    @Save
    private String phoneNumber;

    public Client(String firstName, String lastName, Integer age, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "SerializableClass{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}