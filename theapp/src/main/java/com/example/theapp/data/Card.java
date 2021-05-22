package com.example.theapp.data;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Card {
    int id;
    String frontWord;
    String translationWord;
    String context;
    //initial 0 - new 1 - learn 2 - revise 3 - mature
    int type = 0;
    String dateCreated;
    String dueDate;
    String deck;
    long interval;

    public Card() {

    }

    public Card(String frontWord, String translationWord, String context, String dateCreated, String dueDate, String deck) {
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.deck = deck;
    }

    public Card(String frontWord, String translationWord, String context, int type, String dateCreated, String dueDate, String deck) {
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
        this.type = type;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.deck = deck;
    }

    public Card(int id, String frontWord, String translationWord, String context, int type, String dateCreated, String dueDate, String deck) {
        this.id = id;
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
        this.type = type;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.deck = deck;
    }

    public Card(int id, String frontWord, String translationWord, String context, int type, String dateCreated, String dueDate, String deck, long interval) {
        this.id = id;
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
        this.type = type;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.deck = deck;
        this.interval = interval;
    }

    public Card(String frontWord, String translationWord, String context, int type, String dateCreated, String dueDate, String deck, long interval) {
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
        this.type = type;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.deck = deck;
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrontWord() {
        return frontWord;
    }

    public void setFrontWord(String frontWord) {
        this.frontWord = frontWord;
    }

    public String getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(String translationWord) {
        this.translationWord = translationWord;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDateAndTime() {
        long mill = Long.parseLong(dateCreated);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mill);
        return df.format(calendar.getTime());
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }
}
