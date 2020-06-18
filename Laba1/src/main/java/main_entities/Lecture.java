package main_entities;

public class Lecture extends Lesson {
    private String topic;
    public void setTopic(String topic) { this.topic = topic; }
    public String getTopic() { return topic; }

    @Override
    public String writeData() {
        String result = this.getType() + ';'
                + this.getTime() + ';'
                + this.getSubject_name() + ';'
                + this.getSubject().getDepartment() + ';'
                + this.getTutor_name() + ';'
                + this.getTutor().getDegree() + ';'
                + this.getTutor().getPost() + ';'
                + this.getFaculty() + ';'
                + this.getCourse() + ';'
                + this.getTopic() + ';';
        return result;
    }

    @Override
    public void readData(String[] tokens) {
        this.setType(tokens[0]);
        this.setTime(tokens[1]);
        Subject subject = new Subject();
        subject.setName(tokens[2]);
        subject.setDepartment(tokens[3]);
        this.setSubject_name(tokens[2]);
        this.setSubject(subject);
        Tutor tutor = new Tutor();
        tutor.setName(tokens[4]);
        tutor.setDegree(tokens[5]);
        tutor.setPost(tokens[6]);
        this.setTutor_name(tokens[4]);
        this.setTutor(tutor);
        this.setFaculty(tokens[7]);
        this.setCourse(tokens[8]);
        this.setTopic(tokens[9]);
    }

}
