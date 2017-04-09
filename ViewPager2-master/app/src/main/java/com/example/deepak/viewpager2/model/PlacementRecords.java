package com.example.deepak.viewpager2.model;

/**
 * Created by deepak on 8/4/17.
 */

public class PlacementRecords {
    String name;
    int year;
    int numberOfStudents;

    public PlacementRecords(String name, int year, int numberOfStudents) {
        this.name = name;
        this.year = year;
        this.numberOfStudents = numberOfStudents;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
