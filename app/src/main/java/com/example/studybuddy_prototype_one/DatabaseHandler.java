package com.example.studybuddy_prototype_one;

import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHandler";

    //If database schema is modified, increment the database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "StudyBuddyDB";

    //Database Table Name
    private static final String TABLE_NAME = "UserTable";

    //Database Columns (For now)
    private static final String STUDENT_ID = "user_id";
    private static final String STUDENT_FNAME = "user_firstName";
    private static final String STUDENT_LNAME = "user_lastName";
    private static final String STUDENT_EMAIL = "user_email";
    private static final String STUDENT_PASSWORD = "user_password";

    //Creating sql table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    STUDENT_FNAME + " TEXT not null," +
                    STUDENT_LNAME + " TEXT not null," +
                    STUDENT_EMAIL + " TEXT," +
                    STUDENT_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //private SQLiteDatabase database;


    DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //database = getWritableDatabase();
        Log.d(TAG, "DatabaseHandler: Constructor finished");
    }

//    When the application turns on, it will call upon this function to generate the saved database
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
        SQLiteDatabase database = this.getWritableDatabase();

        //This grabs the current values within the activity that have
        ContentValues values = new ContentValues();

        //Adds this specifications
        values.put(STUDENT_FNAME, user.getFirstName());
        values.put(STUDENT_LNAME, user.getLastName());
        values.put(STUDENT_EMAIL, user.getEmail());
        values.put(STUDENT_PASSWORD, user.getPassword());

        //Insert new user row in table
        database.insert(TABLE_NAME, null, values);
        database.close();
        Log.d(TAG, "addUser: Added new user");
    }

    //Updating user
    public void updateUser(StudentUser user){
        Log.d(TAG, "updateUser: Updating old user");
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_FNAME, user.getFirstName());
        values.put(STUDENT_LNAME, user.getLastName());
        values.put(STUDENT_EMAIL, user.getEmail());
        values.put(STUDENT_PASSWORD, user.getPassword());

        //Update row by checking if the userID given matches the userID within database
        database.update(TABLE_NAME, values, STUDENT_ID + " = ?", new String[]{String.valueOf(user.getUser_id())});
        database.close();
    }

    //Verifying user's existence
    public boolean checkUser(String email){
        Log.d(TAG, "checkUser: Verifying user exists");
        String[] cols = {STUDENT_ID};

        //Notice this is readable; We don't want to edit the current database by verifying user
        SQLiteDatabase database = this.getReadableDatabase();

        //Create selection criteria
        String selection = STUDENT_EMAIL + " = ?";

        //Create argument
        String[] argument = {email};

        //Query with condition
        Cursor cursor = database.query(TABLE_NAME,
                cols,
                selection,
                argument,
                null,
                null,
                null);

        //Check the count of cursor
        //If greater than 0, then user does exist
        int cursorCount = cursor.getCount();
        cursor.close();
        database.close();
        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    //Deleting a user (for testing or other reasons)
    public void deleteUser(StudentUser user){
        Log.d(TAG, "deleteUser: Deleting a user");
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_NAME, STUDENT_ID + " = ?", new String[]{String.valueOf(user.getUser_id())});
        database.close();
    }

    //Fetch all users within database (For testing and other reasons)
    public ArrayList<StudentUser> getAllUsers(){
        Log.d(TAG, "getAllUsers: Begin getting all users");
        String[] cols = {
                STUDENT_FNAME,
                STUDENT_LNAME,
                STUDENT_ID,
                STUDENT_EMAIL,
                STUDENT_PASSWORD
        };

        //Sort in ascending order
        String student_fullName = STUDENT_FNAME + " " + STUDENT_LNAME;
        String sortOrder = student_fullName + "ASC";
        ArrayList<StudentUser> userList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        //Query the user table
        Cursor cursor = database.query(TABLE_NAME,
                cols,
                null,
                null,
                null,
                null,
                sortOrder);

        //Add to userList
        if(cursor.moveToFirst()){
            do{
                //Grab the information stored within the database by col
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(STUDENT_ID)));
                String fName = cursor.getString(cursor.getColumnIndex(STUDENT_FNAME));
                String lName = cursor.getString(cursor.getColumnIndex(STUDENT_LNAME));
                String email = cursor.getString(cursor.getColumnIndex(STUDENT_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(STUDENT_PASSWORD));

                //Empty course list
                ArrayList<Course> courses = new ArrayList<>();

                //Now create a student object out of the above information and add to the userList
                StudentUser studentUser = new StudentUser(id, fName, lName, email, password, courses);
                userList.add(studentUser);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return userList;
    }

}
