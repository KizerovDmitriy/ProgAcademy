package com.example.customerstable;

public class Customer {
    private String firstName;
    private String lastName;
    private int age;
    private String id;
    private String cashValue;

    public Customer(String firstName, String lastName, int age, String id, String cashValue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.cashValue = cashValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getCashValue() {
        return cashValue;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCashValue(String cashValue) {
        this.cashValue = cashValue;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", id='" + id + '\'' +
                ", cashValue='" + cashValue + '\'' +
                '}';
    }
}
