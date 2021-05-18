package com.example.theapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "ANTIGLUTEN";

    public static final String TABLE_NAME_CARDS = "CARDS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FRONT_WORD = "FRONT_WORD";
    public static final String COLUMN_TRANSLATION_WORD = "TRANSLATION_WORD";
    public static final String COLUMN_CONTEXT = "CONTEXT";
    public static final String COLUMN_CARD_TYPE = "TYPE";
    public static final String COLUMN_CARD_DATE_CREATED = "CREATED";
    public static final String COLUMN_CARD_DUE = "DUE";
    public static final String COLUMN_CARD_DECK = "DECK";
    public static final String COLUMN_INTERVAL = "INTERVAL";

    public static final String TABLE_NAME_DECKS = "DECKS";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_TOTAL = "TOTAL";
    public static final String COLUMN_NEW = "NEW";
    public static final String COLUMN_RELEARN = "LEARN";
    public static final String COLUMN_REVISE = "REVISE";
    public static final String COLUMN_DECK_CREATED = "CREATED";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "flashIt.sql3", null, 1);
    }

    long dayInMilliseconds = 86400000;

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
                COLUMN_CARD_DECK + " TEXT NOT NULL," +
                COLUMN_INTERVAL + " INTEGER NOT NULL)";

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

    public boolean addCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
        values.put(COLUMN_CONTEXT, card.getContext());
        values.put(COLUMN_CARD_TYPE, card.getType());
        values.put(COLUMN_CARD_DATE_CREATED, card.getDateCreated());
        values.put(COLUMN_CARD_DUE, card.getDueDate());
        values.put(COLUMN_CARD_DECK, card.getDeck());
        values.put(COLUMN_INTERVAL, card.getInterval());

        long insert = db.insert(TABLE_NAME_CARDS, null, values);
        db.close();
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

    public int getTotalFromDeck(Card card) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select " + COLUMN_TOTAL + " from " +  TABLE_NAME_DECKS + " WHERE " + COLUMN_NAME + " =?";

        Cursor cursor = db.rawQuery(query, new String[]{card.getDeck()});
        int total = 0;

        if (cursor.moveToFirst()) {
            do {
                total = cursor.getInt(0);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return total;
    }

    public int getTotalFromDeck(String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select " + COLUMN_TOTAL + " from " +  TABLE_NAME_DECKS + " WHERE " + COLUMN_NAME + " =?";

        Cursor cursor = db.rawQuery(query, new String[]{deckName});
        int total = 0;

        if (cursor.moveToFirst()) {
            do {
                total = cursor.getInt(0);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return total;
    }

    public boolean deleteCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_CARDS + " WHERE " + COLUMN_ID + " = " +  card.getId(), null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteDeck(Deck deck) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_DECKS + " WHERE " + COLUMN_ID + " = " + deck.getId(), null);
        cursor.moveToFirst();
        deleteCardsWithDeckName(deck.getName());
        Log.d(TAG, "deleteDeck: deleted deck");
    }

    public void deleteCardsWithDeckName(String deckName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME_CARDS +
                " WHERE " + COLUMN_CARD_DECK + " =?", new String[]{deckName});
        cursor.moveToFirst();
        Log.d(TAG, "deleteCardsWithDeckName: deleted cards");
    }

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

        String queryString = "SELECT * FROM " + TABLE_NAME_CARDS +
                " WHERE " + COLUMN_CARD_DECK + "=?";
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
                long interval = cursor.getLong(8);


                returnList.add(new Card(id, frontWord, translation, using, cardType, cardCreated, cardDue, deck, interval));
            } while (cursor.moveToNext());

        }

        cursor.close();

        return returnList;
    }

    public void updateCardList() {

    }

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

    public void refreshTotal(String deckName, long number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTAL, number);

        String whereClaus = "NAME=?";
        String[] whereArgs = {deckName};

        long insert = db.update(TABLE_NAME_DECKS, values, whereClaus, whereArgs);
        Log.d(TAG, "addToTotal: " + insert);
    }

    public void changeTypeOfCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        int type = getType(card) + 1;
        values.put(COLUMN_CARD_TYPE, type);

        String whereClaus = "ID=?";
        String[] whereArgs = {String.valueOf(card.getId())};

        long insert = db.update(TABLE_NAME_CARDS, values, whereClaus, whereArgs);
        Log.d(TAG, "changeTypeOfCard: " + insert);
    }

    public int getType(Card card) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select " + COLUMN_CARD_TYPE + " from " +  TABLE_NAME_CARDS + " WHERE " + COLUMN_ID + " =?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(card.getId())});

        int type = 0;

        if (cursor.moveToFirst()) {
            do {
                type = cursor.getInt(0);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return type;
    }

    /*
    0 - new     the card is just added, if you answer it right you will have to confirm second one to see it tomorrow, and if was wrong one it stays 0.
    1 - learn   it was 2, but you forgot it
    2 - revise  it has a review and you have to get one more review to see it later
    3 - mature  you made two right answers in a row and you can delay it
     */
    public long refreshDecks(String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTAL, getNumberOfTotalCards(deckName));
        values.put(COLUMN_NEW, getNumberOfNewCards(deckName));
        values.put(COLUMN_RELEARN, getNumberOfLearnCards(deckName));
        values.put(COLUMN_REVISE, getNumberOfReviseCards(deckName));

        String whereClaus = "NAME=?";
        String[] whereArgs = {deckName};

        return db.update(TABLE_NAME_DECKS, values, whereClaus, whereArgs);
    }

    public long getNumberOfNewCards (String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db,
                "SELECT COUNT(*) FROM CARDS WHERE DECK =? and TYPE =? and DUE <?",
                new String[]{deckName, String.valueOf(0), String.valueOf(getTodayEndTime())});
    }

    public long getNumberOfLearnCards (String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db,
                "SELECT COUNT(*) FROM CARDS WHERE DECK =? and TYPE = 1  and DUE <?",
                new String[]{deckName, String.valueOf(getTodayEndTime())});
    }

    public long getNumberOfReviseCards (String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db,
                "SELECT COUNT(*) FROM CARDS WHERE DECK =? and TYPE = 2 and DUE <?",
                new String[]{deckName, String.valueOf(getTodayEndTime())});
    }

    public long getNumberOfTotalCards (String deckName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db,
                "SELECT COUNT(*) FROM CARDS WHERE DECK =?", new String[]{deckName});
    }

    public long getTodayEndTime() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 23); //anything 0 - 23
        c.set(Calendar.MINUTE, 60);
        c.set(Calendar.SECOND, 1);

        Date d1 = c.getTime();
        return d1.getTime();
    }
}
