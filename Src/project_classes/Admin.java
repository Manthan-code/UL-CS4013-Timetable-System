package project_classes;

public class Admin extends User {

    public Admin(String userId, String name, String username, String password) {
        super(userId, name, username, password);
    }

    public void addTimetableEntry(TimetableManager manager, TimeSlot entry) {
        manager.addEntry(entry);
    }

    public void removeTimetableEntry(TimetableManager manager, TimeSlot entry) {
        manager.removeEntry(entry);
    }

    public void updateTimetableEntry(TimetableManager manager, TimeSlot oldEntry, TimeSlot newEntry) {
        manager.updateEntry(oldEntry, newEntry);
    }
}
