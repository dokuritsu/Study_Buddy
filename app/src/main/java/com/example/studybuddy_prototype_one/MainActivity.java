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

public class MainActivity extends AppCompatActivity {

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

        //Create new user
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Moving to CreateProfile Activity");
                Toast.makeText(MainActivity.this, "Attempting to create new account", Toast.LENGTH_SHORT).show();
                Intent createProfile = new Intent(MainActivity.this, CreateProfile.class);
                startActivity(createProfile);
//
            }
        });

        //Login to dashboard
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Moving to Dashboard Activity");
                Intent dashboard = new Intent(MainActivity.this, Dashboard.class);
                startActivity(dashboard);
            }
        });

        databaseHandler = new DatabaseHandler(this);
    }
}
