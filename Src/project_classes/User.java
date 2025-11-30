package project_classes;

//user model
public class User {

    /**
     * Class that stores user details and represents users of system
     */

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

    /** Gets the email address of user*/
    public String getEmail() {
        return email;
    }

    /** Gets the role of user*/
    public String getRole() {
        return role;
    }

    /** Gets the extra tag of user*/
    public String getExtra() {
        return extra;
    }

    /** Gets the course of the user*/
    public String getCourse() {
        return course;
    }

    /**
     * Returns the details of a user in String format
     * @return String details of user.
     */

    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", extra='" + extra + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
