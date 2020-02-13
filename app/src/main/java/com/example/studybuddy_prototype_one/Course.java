package com.example.studybuddy_prototype_one;


//The class Course will contain information regarding a specific course the user is taking
//Main variables are subject to change
public class Course {
    private String courseName;
    private int courseGrade;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }
}
