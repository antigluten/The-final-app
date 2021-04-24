package com.example.theapp.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.theapp.R;
import com.example.theapp.data.Contact;
import com.example.theapp.util.Util;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    String TAG = "ANTIGLUTEN";

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME
                + "(" + Util.KEY_ID + "INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";
        //creating the table
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);
    }

    //add Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoheNumber());

        Log.d(TAG, "addContact: " + contact.getId() + " " + contact.getName() + " " + contact.getPhoheNumber());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact("antigluten", "01032003");
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoheNumber(cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);


        //loop through data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("antigluten", "01032003");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoheNumber(cursor.getString(2));

                contactList.add(contact);
            } while (cursor.moveToFirst());
        }

        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoheNumber());

        //update the row

        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }


    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }
}
