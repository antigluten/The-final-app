package com.example.theapp.data;

import android.icu.util.LocaleData;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deck {
    public String name;
    public int totalNumberOfCard;
    public int numberToRelearn;
    public int numberNewCards;
    public int numberToRevise;
    public String dateCreated;
    public ArrayList<Card> cards;


    public Deck() {
    }

    public Deck(String name) {
        this.name = name;
        this.totalNumberOfCard = 0;
        this.numberToRelearn = 0;
        this.numberNewCards = 0;
        this.numberToRevise = 0;
        String dateCreated;
        this.cards = null;
    }

    public Deck(String name, int totalNumberOfCard, int numberToLearn, int numberNewCards, int numberToRevise, ArrayList<Card> cards) {
        this.name = name;
        this.totalNumberOfCard = totalNumberOfCard;
        this.numberToRelearn = numberToLearn;
        this.numberNewCards = numberNewCards;
        this.numberToRevise = numberToRevise;
        this.cards = cards;
    }

    public Deck(String name, int totalNumberOfCard, int numberToRelearn, int numberNewCards, int numberToRevise, String dateCreated) {
        this.name = name;
        this.totalNumberOfCard = totalNumberOfCard;
        this.numberToRelearn = numberToRelearn;
        this.numberNewCards = numberNewCards;
        this.numberToRevise = numberToRevise;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "name='" + name + '\'' +
                ", totalNumberOfCard=" + totalNumberOfCard +
                ", numberToLearn=" + numberToRelearn +
                ", numberNewCards=" + numberNewCards +
                ", numberToRevise=" + numberToRevise +
                ", cards=" + cards +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalNumberOfCard() {
        return totalNumberOfCard;
    }

    public void setTotalNumberOfCard(int totalNumberOfCard) {
        this.totalNumberOfCard = totalNumberOfCard;
    }

    public int getNumberToRelearn() {
        return numberToRelearn;
    }

    public void setNumberToRelearn(int numberToRelearn) {
        this.numberToRelearn = numberToRelearn;
    }

    public int getNumberNewCards() {
        return numberNewCards;
    }

    public void setNumberNewCards(int numberNewCards) {
        this.numberNewCards = numberNewCards;
    }

    public int getNumberToRevise() {
        return numberToRevise;
    }

    public void setNumberToRevise(int numberToRevise) {
        this.numberToRevise = numberToRevise;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
