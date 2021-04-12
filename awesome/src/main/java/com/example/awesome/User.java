package com.example.awesome;

public class User {
    String name;
    String last;
    int age;

    public User() {

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, String last, int age) {
        this.name = name;
        this.last = last;
        this.age = age;
    }


}
