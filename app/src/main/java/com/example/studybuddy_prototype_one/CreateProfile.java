package com.example.studybuddy_prototype_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener {

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

        //Initialize the listeners
        createUser.setOnClickListener(this);
        alreadyMember.setOnClickListener(this);

        //Since we are creating a new user, a new student object is created
        databaseHandler = new DatabaseHandler(appCompatActivity);
        newUser = new StudentUser();


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

    //Function to post new user data to database
    public void postData(){
        Log.d(TAG, "postData: Checking if all fields are filled");
        //Check if all fields are filled
        if(isEmptyFields()){
            Log.d(TAG, "postData: Checking if email is valid");
            //Check if the email is valid by comparing to email pattern
            final String checkEmail = email.getText().toString().trim();
            final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(checkEmail.matches(emailPattern) && s.length() > 0){
                        Toast.makeText(appCompatActivity, "Valid email address", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(appCompatActivity, "Invalid email address", Toast.LENGTH_SHORT);
                    }
                }
            });

            Log.d(TAG, "postData: Checking if password matches");
            //Check if password matches
            final String checkPassword = password.getText().toString().trim();
            final String checkConfirmPassword = confirmPassword.getText().toString().trim();
            if(checkPassword != checkConfirmPassword){
                Toast.makeText(appCompatActivity, "Password do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(appCompatActivity, "Passwords match", Toast.LENGTH_SHORT).show();
            }

            //If the email hasn't been used before...
            if(!databaseHandler.checkUser(email.getText().toString().trim())){
                //Set the user's info
                newUser.setFirstName(firstName.getText().toString().trim());
                newUser.setLastName(lastName.getText().toString().trim());
                newUser.setEmail(email.getText().toString().trim());
                newUser.setPassword(password.getText().toString().trim());

                //Add user to database
                databaseHandler.addUser(newUser);

                Toast.makeText(appCompatActivity, "Successfully created account", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(appCompatActivity, "Error: Email already exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(appCompatActivity, "Please fill in all fields to proceed", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: Want to either create account or login into account");
        switch (v.getId()){
            case R.id.create_profile:
                //Call database to register the new user
                postData();
                break;
            case R.id.create_accident:
                //Go back to the login page
                Intent login = new Intent(CreateProfile.this, MainActivity.class);
                startActivity(login);
                break;
        }
    }


}
