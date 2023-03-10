package classes;

import annotations.Save;


public class SerializableClass{
    @Save
    private String firstName;
    @Save
    private String lastName;
    private String age;
    @Save
    private String phoneNumber;

    public SerializableClass(String firstName, String lastName, String age, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public SerializableClass() {
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