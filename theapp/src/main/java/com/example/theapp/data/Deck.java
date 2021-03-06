package com.example.theapp.data;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Deck {
    public int id;
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
        this.dateCreated = getToday();
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

    public Deck(int id, String name, int totalNumberOfCard, int numberToRelearn, int numberNewCards, int numberToRevise, String dateCreated) {
        this.id = id;
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

    public String getToday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.UK);
        Log.d("ANTIGLUTEN", "getToday: " + dateFormat.format(calendar.getTime()));
        return dateFormat.format(calendar.getTime());
    }
}
