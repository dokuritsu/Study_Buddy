package com.example.studybuddy_prototype_one;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHandler";

    //If database schema is modified, increment the database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "StudyBuddyDB";

    //Database Table Name
    private static final String TABLE_NAME = "UserTable";

    //Database Columns (For now)
    private static final String USER_ID = "User_id";
    private static final String USER_FNAME = "User_firstName";
    private static final String USER_LNAME = "User_lastName";
    private static final String USER_EMAIL = "User_email";
    private static final String USER_PASSWORD = "User_password";

    //Creating table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_FNAME + " TEXT not null," +
                    USER_LNAME + " TEXT not null," +
                    USER_EMAIL + " TEXT," +
                    USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private SQLiteDatabase database;


    DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
        Log.d(TAG, "DatabaseHandler: Constructor finished");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Note: onCreate only called if database does not exist
        Log.d(TAG, "onCreate: Making a new StudentUser Database");
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: Dropping user table");
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    //Adding user
    public void addUser(StudentUser user){
        Log.d(TAG, "addUser: Adding a new user");
        ContentValues values = new ContentValues();
        values.put(USER_FNAME, user.getFirstName());
        values.put(USER_LNAME, user.getLastName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());

        database.insert(TABLE_NAME, null, values);
        Log.d(TAG, "addUser: Added new user");
    }
}
