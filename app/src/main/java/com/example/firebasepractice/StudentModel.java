package com.example.firebasepractice;

public class StudentModel {

    String name,id,roll,course;
    public StudentModel(){}
    public StudentModel(String name, String id, String roll, String course) {
        this.name = name;
        this.id = id;
        this.roll = roll;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
