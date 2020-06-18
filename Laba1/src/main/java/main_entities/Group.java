package main_entities;

import java.io.Serializable;

public class Group implements Serializable {
    private String number;
    private String faculty;
    private String course;

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}
