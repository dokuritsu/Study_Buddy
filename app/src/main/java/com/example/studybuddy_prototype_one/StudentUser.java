package com.example.studybuddy_prototype_one;

import java.util.ArrayList;

//The class StudentUser will contain information on the student. By having a login information stored, data/settings of the user can be saved for quicker access.
//Variables are prone to change
public class StudentUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Course> courses;
}
