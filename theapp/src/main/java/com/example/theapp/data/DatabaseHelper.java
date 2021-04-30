package com.example.theapp.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.example.theapp.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "CARD_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FRONT_WORD = "FRONT_WORD";
    public static final String COLUMN_TRANSLATION_WORD = "TRANSLATION_WORD";
    public static final String COLUMN_CONTEXT = "CONTEXT";

    public static final String TABLE_TEST = "TEST";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "antigluten.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_FRONT_WORD + " TEXT, " +
//                COLUMN_TRANSLATION_WORD + " TEXT, " +
//                COLUMN_CONTEXT + " TEXT)";

//        db.execSQL(createTable);

        String onCreate = "create table " + "test" + "(" +
                "id integer not null primary key autoincrement,\n" +
                "    front text not null,\n" +
                "    back text not null,\n" +
                "    context text not null,\n" +
                "    type integer not null,\n" +
                "    dueDate date not null,\n" +
                "    dateCreated date not null" + ")";
        db.execSQL(onCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(TABLE_NAME);
        db.execSQL(TABLE_TEST);
    }

    public void testOne() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("front", "hello");
        values.put("back", "world");
        values.put("context", "hello world");
        values.put("type", 0);
        values.put("dueDAte", "date('now')");
        values.put("dateCreated", "date('now')");

        db.insert("test", null, values);
    }

    public boolean addOne(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
        values.put(COLUMN_CONTEXT, card.getContext());

        long insert = db.insert(TABLE_NAME, null, values);
        return insert != -1;
    }

    public boolean update(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, card.getId());
        contentValues.put(COLUMN_FRONT_WORD, card.getFrontWord());
        contentValues.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
        contentValues.put(COLUMN_CONTEXT, card.getContext());
        int success = db.update(TABLE_NAME, contentValues, COLUMN_ID + " =? ", new String[]{String.valueOf(card.getId())});
        return success != -1;
    }


    public boolean deleteOne(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        int success = db.delete(TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(card.getId())});
        return success != -1;
    }

    public void createNewTable(SQLiteDatabase db, String deckName) {
        String onCreate = "create table " + deckName + "(" +
                "id integer not null primary key autoincrement,\n" +
                "    front text not null,\n" +
                "    back text not null,\n" +
                "    context text not null,\n" +
                "    type integer not null,\n" +
                "    dueDate date not null,\n" +
                "    dateCreated date not null" + ")";
        db.execSQL(onCreate);
    }

    public boolean addToDeck(String table, Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_FRONT_WORD, card.getFrontWord());
        values.put(COLUMN_TRANSLATION_WORD, card.getTranslationWord());
        values.put(COLUMN_CONTEXT, card.getContext());

        long insert = db.insert(TABLE_NAME, null, values);
        return insert != -1;
    }


    public List<Card> getAll() {
        List<Card> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int customerID = cursor.getInt(0);
                String frontWord = cursor.getString(1);
                String translation = cursor.getString(2);
                String context = cursor.getString(3);

                Card card = new Card(customerID, frontWord, translation, context);
                returnList.add(card);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return returnList;
    }


}
