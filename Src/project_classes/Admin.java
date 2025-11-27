package project_classes;

public class Admin extends User {

    public Admin(String userId, String name, String username, String password) {
        super(userId, name, username, password);
    }

    public void addTimetableEntry(TimetableManager manager, TimetableEntry entry) {
        manager.addEntry(entry);
    }

    public void removeTimetableEntry(TimetableManager manager, TimetableEntry entry) {
        manager.removeEntry(entry);
    }

    public void updateTimetableEntry(TimetableManager manager, TimetableEntry oldEntry, TimetableEntry newEntry) {
        manager.updateEntry(oldEntry, newEntry);
    }
}
