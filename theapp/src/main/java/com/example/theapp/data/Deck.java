package com.example.theapp.data;

import java.util.ArrayList;

public class Deck {
    public String name;
    public int totalNumberOfCard;
    public int numberToRelearn;
    public int numberNewCards;
    public int numberToRevise;
    public ArrayList<Card> cards;

    public Deck() {
    }

    public Deck(String name, int totalNumberOfCard, int numberToLearn, int numberNewCards, int numberToRevise, ArrayList<Card> cards) {
        this.name = name;
        this.totalNumberOfCard = totalNumberOfCard;
        this.numberToRelearn = numberToLearn;
        this.numberNewCards = numberNewCards;
        this.numberToRevise = numberToRevise;
        this.cards = cards;
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
}
