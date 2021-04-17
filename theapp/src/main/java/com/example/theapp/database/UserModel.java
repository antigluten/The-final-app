package com.example.theapp.database;

public class UserModel {
    private String name;
    private String lastName;
    private int age;
    private boolean isActive;

    public UserModel() {

    }

    public UserModel(String name, String lastName, int age, boolean isActive) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
