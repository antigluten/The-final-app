package com.example.theapp;

public class User {
    public String name;
    public String last;
    public String password;
    public String email;

    public User() {

    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String last) {
        this.name = name;
        this.last = last;
    }




    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", last='" + last + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}