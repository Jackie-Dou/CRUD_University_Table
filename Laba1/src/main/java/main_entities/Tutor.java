package main_entities;

import java.io.Serializable;

public class Tutor implements Serializable {
    private String name;
    private String degree;
    private String post;
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
