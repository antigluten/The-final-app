package com.example.theapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME_CARDS = "CARDS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FRONT_WORD = "FRONT_WORD";
    public static final String COLUMN_TRANSLATION_WORD = "TRANSLATION_WORD";
    public static final String COLUMN_CONTEXT = "CONTEXT";
    public static final String COLUMN_CARD_TYPE = "TYPE";
    public static final String COLUMN_CARD_DATE_CREATED = "CREATED";
    public static final String COLUMN_CARD_DUE = "DUE";
    public static final String COLUMN_CARD_DECK = "DECK";

    public static final String TABLE_NAME_DECKS = "DECKS";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_TOTAL = "TOTAL";
    public static final String COLUMN_NEW = "NEW";
    public static final String COLUMN_RELEARN = "LEARN";
    public static final String COLUMN_REVISE = "REVISE";
    public static final String COLUMN_DECK_CREATED = "CREATED";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "anti.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCards = "CREATE TABLE " + TABLE_NAME_CARDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FRONT_WORD + " TEXT NOT NULL," +
                COLUMN_TRANSLATION_WORD + " TEXT NOT NULL," +
                COLUMN_CONTEXT + " TEXT NOT NULL," +
                COLUMN_CARD_TYPE + " INTEGER NOT NULL," +
                COLUMN_CARD_DATE_CREATED + " TEXT NOT NULL," +
                COLUMN_CARD_DUE + " TEXT NOT NULL," +
                COLUMN_CARD_DECK + " TEXT NOT NULL)";

        String createDecks = "CREATE TABLE " + TABLE_NAME_DECKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_TOTAL + " INTEGER NOT NULL," +
                COLUMN_NEW + " INTEGER NOT NULL," +
                COLUMN_RELEARN + " INTEGER NOT NULL," +
                COLUMN_REVISE + " INTEGER NOT NULL," +
                COLUMN_DECK_CREATED + " TEXT NOT NULL "+
                ")";


        db.execSQL(createCards);
        db.execSQL(createDecks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CARDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DECKS);

        db.execSQL(TABLE_NAME_CARDS);
        db.execSQL(TABLE_NAME_DECKS);
    }

    public boolean addCard(Card card){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
        values.put(COLUMN_CONTEXT, card.getContext());
        values.put(COLUMN_CARD_TYPE, card.getType());
        values.put(COLUMN_CARD_DATE_CREATED, card.getDateCreated());
        values.put(COLUMN_CARD_DUE, card.getDueDate());
        values.put(COLUMN_CARD_DECK, card.getDeck());

        long insert = db.insert(TABLE_NAME_CARDS, null, values);
        return insert != -1;
    }

    public boolean addDeck(Deck deck) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, deck.getName());
        values.put(COLUMN_TOTAL, deck.getTotalNumberOfCard());
        values.put(COLUMN_RELEARN, deck.getNumberToRelearn());
        values.put(COLUMN_NEW, deck.getNumberNewCards());
        values.put(COLUMN_REVISE, deck.getNumberToRevise());
        values.put(COLUMN_DECK_CREATED, deck.getDateCreated());

        long insert = db.insert(TABLE_NAME_DECKS, null, values);
        return insert != -1;
    }

    public boolean deleteDeck(Deck deck) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_DECKS + " WHERE " + COLUMN_ID + " = " + deck.getId(), null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

//
//    public boolean addOne(Card card) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
//        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
//        values.put(COLUMN_CONTEXT, card.getContext());
//
//        long insert = db.insert(TABLE_NAME, null, values);
//        return insert != -1;
//    }
//
//    public boolean update(Card card) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(COLUMN_ID, card.getId());
//        contentValues.put(COLUMN_FRONT_WORD, card.getFrontWord());
//        contentValues.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
//        contentValues.put(COLUMN_CONTEXT, card.getContext());
//        int success = db.update(TABLE_NAME, contentValues, COLUMN_ID + " =? ", new String[]{String.valueOf(card.getId())});
//        return success != -1;
//    }
//
//
//    public boolean deleteOne(Card card) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        int success = db.delete(TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(card.getId())});
//        return success != -1;
//    }
//
//    public void createNewTable(SQLiteDatabase db, String deckName) {
//        String onCreate = "create table " + deckName + "(" +
//                "id integer not null primary key autoincrement,\n" +
//                "    front text not null,\n" +
//                "    back text not null,\n" +
//                "    context text not null,\n" +
//                "    type integer not null,\n" +
//                "    dueDate date not null,\n" +
//                "    dateCreated date not null" + ")";
//        db.execSQL(onCreate);
//    }
//
//    public boolean addToDeck(String table, Card card) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
//        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
//        values.put(COLUMN_CONTEXT, card.getContext());
//
//        long insert = db.insert(TABLE_NAME, null, values);
//        return insert != -1;
//    }
    public List<Deck> getAllDecks() {
        List<Deck> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_NAME_DECKS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int total = cursor.getInt(2);
                int newCards = cursor.getInt(3);
                int learn = cursor.getInt(4);
                int revise = cursor.getInt(5);
                String dateCreated = cursor.getString(6);

                Deck deck = new Deck(id, name, total, learn, newCards, revise, dateCreated);
                returnList.add(deck);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return returnList;
    }



    public List<Card> getAllCardWithDeckName(String deckName) {
        List<Card> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_NAME_CARDS + " WHERE " + COLUMN_CARD_DECK + "=?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[]{deckName});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String frontWord = cursor.getString(1);
                String translation = cursor.getString(2);
                String using = cursor.getString(3);
                int cardType = cursor.getInt(4);
                String cardCreated = cursor.getString(5);
                String cardDue = cursor.getString(6);
                String deck = cursor.getString(7);


                returnList.add(new Card(id, frontWord, translation, using, cardType, cardCreated, cardDue, deck));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return returnList;
    }

    public void updateCardList() {

    }
//
//    public List<Card> getAll() {
//        List<Card> returnList = new ArrayList<>();
//
//        String queryString = "SELECT * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int customerID = cursor.getInt(0);
//                String frontWord = cursor.getString(1);
//                String translation = cursor.getString(2);
//                String context = cursor.getString(3);
//
//                Card card = new Card(customerID, frontWord, translation, context);
//                returnList.add(card);
//            } while (cursor.moveToNext());
//
//        }
//
//        cursor.close();
//        db.close();
//
//        return returnList;
//    }
//

    public boolean deleteAllDecksAndCards() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_DECKS, null);
        Cursor cursor1 = db.rawQuery("DELETE FROM " + TABLE_NAME_CARDS, null);
        if (cursor.moveToFirst() && cursor1.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAllCards() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_CARDS, null);
        if (cursor.moveToFirst() && cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
