package com.example.theapp.data;

public class Card {
    int id;
    String frontWord;
    String translationWord;
    String context;

    public Card(){

    }

    public Card(String frontWord, String translationWord, String context) {
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
    }

    public Card(int id, String frontWord, String translationWord, String context) {
        this.id = id;
        this.frontWord = frontWord;
        this.translationWord = translationWord;
        this.context = context;
    }

    public String getFrontWord() {
        return frontWord;
    }

    public void setFrontWord(String frontWord) {
        this.frontWord = frontWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "Card{" +
//                "id='" + id + '\'' +
//                ", frontWord='" + frontWord + '\'' +
//                ", translationWord='" + translationWord + '\'' +
//                ", context='" + context + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return "Foreign word: " + frontWord + "\nTranslation: " + translationWord + "\nContext: " + context;
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
}
