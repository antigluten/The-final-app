package com.example.theapp.deck;

import java.util.Date;

public class Deck {
    String name;
    Date dateCreated;
    int numberOfWords;
    int wordsToLearn;
    int wordsToReview;
    int newWords;
    int numberOfRepetitions;


    private int image;
    private String text;

    public Deck(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
