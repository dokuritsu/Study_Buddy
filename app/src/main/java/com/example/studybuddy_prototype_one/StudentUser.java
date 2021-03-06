package com.example.studybuddy_prototype_one;

import java.util.ArrayList;

//The class StudentUser will contain information on the student. By having a login information stored, data/settings of the user can be saved for quicker access.
//Variables are prone to change
public class StudentUser {
    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Course> courses;

    public StudentUser(){

    }
    public StudentUser(int user_id, String firstName, String lastName, String email, String password, ArrayList<Course> courses) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
