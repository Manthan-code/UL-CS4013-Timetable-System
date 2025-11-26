package project_classes;

import java.util.ArrayList;

public class Student extends User {
    private String course;

    public Student(String userId, String name, String username, String password, String course) {
        super(userId, name, username, password);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
