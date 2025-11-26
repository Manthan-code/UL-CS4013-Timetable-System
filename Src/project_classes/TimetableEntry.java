package project_classes;

public class TimetableEntry {

    private String day;
    private TimeSlot timeSlot;
    private String moduleCode;
    private String roomCode;
    private String classType;
    private String lecturerName;
    private int startWeek;
    private int endWeek;

    public TimetableEntry(String day, TimeSlot timeSlot, String moduleCode,
                          String roomCode, String classType, String lecturerName,
                          int startWeek, int endWeek) {

        this.day = day;
        this.timeSlot = timeSlot;
        this.moduleCode = moduleCode;
        this.roomCode = roomCode;
        this.classType = classType;
        this.lecturerName = lecturerName;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }

    // Getters
    public String getDay() { return day; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public String getModuleCode() { return moduleCode; }
    public String getRoomCode() { return roomCode; }
    public String getClassType() { return classType; }
    public String getLecturerName() { return lecturerName; }
    public int getStartWeek() { return startWeek; }
    public int getEndWeek() { return endWeek; }

    // Basic time conflict
    public boolean timeConflictsWith(TimetableEntry other) {
        if (!this.day.equalsIgnoreCase(other.day)) return false;

        boolean weeksOverlap = !(this.endWeek < other.startWeek ||
                other.endWeek < this.startWeek);

        if (!weeksOverlap) return false;

        return this.timeSlot.overlaps(other.timeSlot);
    }

    public String toString() {
        return day + " " + timeSlot + " " + moduleCode + " " + roomCode +
                " (" + classType + ") - " + lecturerName +
                " Weeks " + startWeek + "-" + endWeek;
    }
}
