package main_entities;

public class Laboratory extends Lesson {
    private Group group;
    private String group_number;
    public String getGroup_number() { return group_number; }
    public void setGroup_number(String group_number) { this.group_number = group_number; }
    public void setGroup(Group group) { this.group = group; }
    public Group getGroup() { return group; }

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
                + this.getGroup_number() + ';'
                + this.getGroup().getFaculty() + ';'
                + this.getGroup().getCourse() + ';';
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
        Group group = new Group();
        group.setNumber(tokens[9]);
        group.setFaculty(tokens[10]);
        group.setCourse(tokens[11]);
        this.setGroup(group);
        this.setGroup_number(tokens[9]);
    }
}
