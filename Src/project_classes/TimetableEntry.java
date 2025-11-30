//TimetableEntry

package project_classes;

public class TimetableEntry {

    /**
     * Class that represents entries in the timetable
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

    public TimetableEntry(String day, TimeSlot timeSlot, String moduleCode, String roomCode, String classType,
                          String lecturerName, String studentGroup, int startWeek, int endWeek){

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

    /** Gets the day of week which the slot takes place on*/
    public String getDay() { return day; }

    /** Gets the timeSlot of the entry*/
    public TimeSlot getTimeSlot() { return timeSlot; }

    /** Gets the module code for entry*/
    public String getModuleCode() { return moduleCode; }

    /** Gets the room code for entry*/
    public String getRoomCode() { return roomCode; }

    /** Gets the class type for entry*/
    public String getClassType() { return classType; }

    /** Gets the lecturer assigned to entry*/
    public String getLecturerName() { return lecturerName; }

    /** Gets the student group assigned to entry*/
    public String getStudentGroup() { return studentGroup; }

    /** Gets the week no. the entry starts from*/
    public int getStartWeek() { return startWeek; }

    /** Gets the week no. the entry ends at*/
    public int getEndWeek() { return endWeek; }

    /**
     * Checks if two timetable entries conflicts with each other in any way
     * Prevents double booking
     * @param other The timetableEntry to be checked for conflicts
     * @return True if the entries overlap and vice versa
     */

    public boolean timeConflictsWith(TimetableEntry other) {

        //same day
        if (!this.day.equalsIgnoreCase(other.day)) {
            return false;
        }

        //week overlap
        boolean weeksOverlap = !(this.endWeek < other.startWeek ||
                other.endWeek < this.startWeek);
        if (!weeksOverlap) {
            return false;
        }

        //times overlap
        if (!this.timeSlot.overlaps(other.timeSlot)) {
            return false;
        }

        //room OR lecturer OR Student group
        boolean roomClash = this.roomCode.equalsIgnoreCase(other.roomCode);
        boolean lecturerClash = this.lecturerName.equalsIgnoreCase(other.lecturerName);
        boolean groupClash = isGroupConflict(this.studentGroup, other.studentGroup);

        return roomClash || lecturerClash || groupClash;
    }

    /**
     * Checks conflict for two entries based on student group
     * @param g1 String student group one
     * @param g2 String student Group two
     * @return true if the groups overlap and vice versa
     */
    private boolean isGroupConflict(String g1, String g2) {
        if (g1 == null || g2 == null) return false;

        if (g1.equalsIgnoreCase("All") || g2.equalsIgnoreCase("All")) return true;
        return g1.equalsIgnoreCase(g2);
    }

    /**
     * Returns the details of an timetable entry in String format
     * @return String details of timetable entry
     */
    public String toString() {
        return String.format("%s %s | %s | %s | %s | %s | %s | %d-%d",
                day, timeSlot, moduleCode, roomCode, classType, lecturerName,
                studentGroup, startWeek, endWeek);
    }
}
