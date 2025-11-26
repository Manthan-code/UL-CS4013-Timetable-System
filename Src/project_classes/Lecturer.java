package project_classes;

import java.util.ArrayList;

public class Lecturer extends User {
    private String staffId;

    public Lecturer(String userId, String name, String username, String password, String staffId) {
        super(userId, name, username, password);
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }
}