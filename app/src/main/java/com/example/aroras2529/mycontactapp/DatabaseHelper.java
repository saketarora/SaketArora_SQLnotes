package com.example.aroras2529.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public final static String COLUMN_NAME_CONTACT = "contact";
    public final static String COLUMN_NUMBER_CONTACT = "Number";
    public final static String COLUMN_EMAIL_CONTACT = "Email";
    public final static String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_CONTACT + " TEXT , " + COLUMN_NUMBER_CONTACT + " TEXT , " + COLUMN_EMAIL_CONTACT + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();// for initial test of db creation
        Log.d("MyContactApp", "Databasehelper: constructed Databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: created Databasehelper");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading Databasehelper");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public boolean insertData(String name, String number, String email) {
        Log.d("MyContactApp", "Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValue = new android.content.ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT, name);
        contentValue.put(COLUMN_NUMBER_CONTACT, number);
        contentValue.put(COLUMN_EMAIL_CONTACT, email);
        long result = db.insert(TABLE_NAME, null, contentValue);
        if (result == -1){
            Log.d("MyContactApp", "Databasehelper: Content insert - FAIL");
            return false;
        }
        else{
            Log.d("MyContactApp", "Databasehelper: Content insert - SUCCEED");
            return true;
        }

    }
    public Cursor getAllData(){
        Log.d("MyContactApp", "Databasehelper: calling GetAllData method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

}
