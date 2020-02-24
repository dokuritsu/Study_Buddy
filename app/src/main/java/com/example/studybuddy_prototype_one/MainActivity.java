package com.example.studybuddy_prototype_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    
    //Set up references
    private EditText email;
    private EditText password;
    private Button login;
    private TextView newUser;

    //Declare database handler
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize references
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        newUser = findViewById(R.id.login_register);

        //Initialize listeners
        login.setOnClickListener(this);
        newUser.setOnClickListener(this);

        //Initialize objects to be used
        databaseHandler = new DatabaseHandler(this);
    }

    public void verifyUser(){
        //Check if email and password are filled
        if(isEmptyFields()){

        }
    }

    //Function to check if all fields are filled
    public boolean isEmptyFields(){
        if(email.getText().toString().isEmpty()){
            email.setError("Please enter a email");
            return false;
        } else if(password.getText().toString().isEmpty()){
            password.setError("Please enter a password");
            return false;
        } else {
            Log.d(TAG, "isEmptyFields: All fields have been filled");
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                verifyUser();
                break;
            case R.id.login_register:
                Log.d(TAG, "onClick: Moving to CreateProfile Activity");
                Toast.makeText(MainActivity.this, "Attempting to create new account", Toast.LENGTH_SHORT).show();
                Intent createProfile = new Intent(MainActivity.this, CreateProfile.class);
                startActivity(createProfile);
        }
    }
}
