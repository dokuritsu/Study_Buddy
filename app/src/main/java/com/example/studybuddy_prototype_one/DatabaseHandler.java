package com.example.studybuddy_prototype_one;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHandler";

    //If database schema is modified, increment the database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "StudyBuddyDB";

    //Database Table Name
    private static final String TABLE_NAME = "StudyBuddyTable";

    //Database Columns (For now)
    private static final String USER_ID = "user_id";
    private static final String USER_FNAME = "user_firstName";
    private static final String USER_LNAME = "user_lastName";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";

    //Creating table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_FNAME + " TEXT not null," +
                    USER_LNAME + " TEXT not null," +
                    USER_EMAIL + " TEXT," +
                    USER_PASSWORD + " TEXT" + ")";



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
