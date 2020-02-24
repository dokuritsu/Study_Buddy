package com.example.studybuddy_prototype_one;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateProfile extends AppCompatActivity {

    private static final String TAG = "CreateProfile";
    private final AppCompatActivity appCompatActivity = CreateProfile.this;

    //Set up references
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button createUser;
    private TextView alreadyMember;

    //Create objects that we need
    private StudentUser newUser;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        //Initialize above references
        firstName = findViewById(R.id.create_first_name);
        lastName = findViewById(R.id.create_last_name);
        email = findViewById(R.id.create_email);
        password = findViewById(R.id.create_password);
        confirmPassword = findViewById(R.id.create_confirm_password);
        createUser = findViewById(R.id.create_profile);
        alreadyMember = findViewById(R.id.create_accident);

        //Since we are creating a new user, a new student object is created
        newUser = new StudentUser();
        databaseHandler = new DatabaseHandler(appCompatActivity);


    }

    //Function to check if all fields are filled
    public boolean isEmptyFields(){
        if(firstName.getText().toString().isEmpty()){
            firstName.setError("Please enter your first name");
            return false;
        } else if (lastName.getText().toString().isEmpty()){
            lastName.setError("Please enter your last name");
            return false;
        } else if (email.getText().toString().isEmpty()){
            email.setError("Please enter a email");
            return false;
        } else if(password.getText().toString().isEmpty()){
            password.setError("Please enter a password");
            return false;
        } else if (confirmPassword.getText().toString().isEmpty()){
            confirmPassword.setError("Please confirm password");
            return false;
        } else {
            Log.d(TAG, "isEmptyFields: All fields have been filled");
            return true;
        }
    }
}
