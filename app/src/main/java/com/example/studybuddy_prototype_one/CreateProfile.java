package com.example.studybuddy_prototype_one;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateProfile extends AppCompatActivity {

    private static final String TAG = "CreateProfile";

    //Set up references
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button createUser;

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
    }
}
