package main_entities;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Laboratory.class, name = "Laboratory"),
        @JsonSubTypes.Type(value = Lecture.class, name = "Lecture")
})

public abstract class Lesson implements Serializable {
    protected String type;
    public String time;
    protected Subject subject;
    private String subject_name;
    protected Tutor tutor;
    private String tutor_name;
    protected String faculty;
    protected String course;
    public String getTutor_name() { return tutor_name; }
    public void setTutor_name(String tutor_name) { this.tutor_name = tutor_name; }
    public String getSubject_name() { return subject_name; }
    public void setSubject_name(String subject_name) { this.subject_name = subject_name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public void setCourse(String course) { this.course = course; }
    public String getCourse() { return course; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public Subject getSubject() { return subject; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }
    public Tutor getTutor() { return tutor; }

    public String writeData(){
        return "";
    }
    public void readData(String[] tokens) { }

}
