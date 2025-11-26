package project_classes;

public abstract class User {
    protected String userId;
    protected String name;
    protected String username;
    protected String password;

    public User(String userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getUsername() { return username; }

    // All users can view a timetable
    public void viewTimetable(TimetableManager manager) {
        for (TimeSlot entry : manager.getEntries()) {
            System.out.println(entry);
        }
    }
}
