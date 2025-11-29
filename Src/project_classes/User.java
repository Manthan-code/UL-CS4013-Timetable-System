package project_classes;

//user model
public class User {

    private String email;
    private String password;
    private String role;
    private String extra;   // lecturerName OR studentGroup OR "-" for admin
    private String course;

    public User(String email, String password, String role, String extra, String course) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.extra = extra;
        this.course = course;
    }

    //getters
    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getExtra() {
        return extra;
    }

    public String getCourse() {
        return course;
    }

    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", extra='" + extra + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
