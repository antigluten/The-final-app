package com.example.theapp.data;

public class Contact {
    private int id;
    private String name;
    private String phoheNumber;

    public Contact() {

    }


    public Contact(int id, String name, String phoheNumber) {
        this.id = id;
        this.name = name;
        this.phoheNumber = phoheNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoheNumber() {
        return phoheNumber;
    }

    public void setPhoheNumber(String phoheNumber) {
        this.phoheNumber = phoheNumber;
    }
}
