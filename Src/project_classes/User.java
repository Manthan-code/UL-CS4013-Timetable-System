package project_classes;

/**
 * Simple User model for login and role handling.
 * Roles: admin, lecturer, student
 *
 * Extra:
 *  - For admin: "-"
 *  - For lecturer: lecturer name (e.g. "Dr. Smith")
 *  - For student: student group / course code (e.g. "CS1A")
 */
public class User {

    private String email;
    private String password;
    private String role;   // "admin", "lecturer", "student"
    private String extra;  // lecturerName or studentGroup or "-"

    public User(String email, String password, String role, String extra) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.extra = extra;
    }

    public String getEmail() {
        return email;
    }

    // Usually not needed after login, but kept for completeness
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getExtra() {
        return extra;
    }

    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
