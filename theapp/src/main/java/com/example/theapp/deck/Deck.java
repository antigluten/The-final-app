package com.example.theapp.deck;

import java.util.Date;

public class Deck {
//    public String name;
//    public Date dateCreated;
//    public int numberOfWords;
//    public int wordsToLearn;
//    public int wordsToReview;
//    public int newWords;
//    public int numberOfRepetitions;


    public Deck() {

    }

    public int image;
    public String text;

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
