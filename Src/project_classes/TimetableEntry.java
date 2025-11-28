package project_classes;

public class TimetableEntry {
    /**
     * Class for creating timetable entry objects for a timetable
     */

    private String day;
    private TimeSlot timeSlot;
    private String moduleCode;
    private String roomCode;
    private String classType;
    private String lecturerName;
    private String studentGroup;
    private int startWeek;
    private int endWeek;

    public TimetableEntry(String day, TimeSlot timeSlot, String moduleCode,
                          String roomCode, String classType, String lecturerName, String studentGroup,
                          int startWeek, int endWeek) {

        this.day = day;
        this.timeSlot = timeSlot;
        this.moduleCode = moduleCode;
        this.roomCode = roomCode;
        this.classType = classType;
        this.lecturerName = lecturerName;
        this.studentGroup = studentGroup;
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
    public String getStudentGroup() { return studentGroup; }
    public int getStartWeek() { return startWeek; }
    public int getEndWeek() { return endWeek; }

    /**
     * Checks if two timetable entries clash.
     * Real conflict means:
     *  - Same day
     *  - Weeks overlap
     *  - Times overlap
     *  - AND at least one of:
     *      * same room
     *      * same lecturer
     *      * same student group (or "All")
     */
    public boolean timeConflictsWith(TimetableEntry other) {

        // 1. Same day
        if (!this.day.equalsIgnoreCase(other.day)) {
            return false;
        }

        // 2. Weeks overlap
        boolean weeksOverlap = !(this.endWeek < other.startWeek ||
                other.endWeek < this.startWeek);
        if (!weeksOverlap) {
            return false;
        }

        // 3. Times overlap
        if (!this.timeSlot.overlaps(other.timeSlot)) {
            return false;
        }

        // 4. Entity clash: room OR lecturer OR group
        boolean roomClash = this.roomCode.equalsIgnoreCase(other.roomCode);
        boolean lecturerClash = this.lecturerName.equalsIgnoreCase(other.lecturerName);
        boolean groupClash = isGroupConflict(this.studentGroup, other.studentGroup);

        return roomClash || lecturerClash || groupClash;
    }

    /**
     * Checks if two student groups are in conflict.
     * "All" conflicts with everyone, otherwise equal groups conflict.
     */
    private boolean isGroupConflict(String g1, String g2) {
        if (g1 == null || g2 == null) return false;

        if (g1.equalsIgnoreCase("All") || g2.equalsIgnoreCase("All")) return true;
        return g1.equalsIgnoreCase(g2);
    }

    /**
     * Outputs string version of a timetable entry
     */
    public String toString() {
        return String.format("%s %s | %s | %s | %s | %s | %s | %d-%d",
                day, timeSlot, moduleCode, roomCode, classType, lecturerName,
                studentGroup, startWeek, endWeek);
    }
}
