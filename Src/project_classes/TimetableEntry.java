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
                          String roomCode, String classType, String lecturerName,String studentGroup,
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
     * Checks if two timetable entries clash based on either day,startWeek,endWeek,timeslots ,
     * rooms ,lecturers and student groups
     * @param other The timetable entry to be checked
     * @return True if the entries clash in any way ,false otherwise.
     */
    // Basic time conflict
    public boolean timeConflictsWith(TimetableEntry other) {
        // Check if days overlap
        if (this.day.equalsIgnoreCase(other.day)) return true;

        // Check if the start and end weeks overlap
        boolean weeksOverlap = !(this.endWeek < other.startWeek ||
                other.endWeek < this.startWeek);
        if (weeksOverlap) return true;

        // Check if the timeslots overlap with each other
        if (this.timeSlot.overlaps(other.timeSlot)) return true;

        // Check if the rooms overlap with each other
        if(this.roomCode.equalsIgnoreCase(other.roomCode)) return true;

        // Check if the lecturers overlap with each other
        if (this.lecturerName.equalsIgnoreCase(other.lecturerName)) return true;

        // Check if student groups overlap
        return isGroupConflict(this.studentGroup, other.studentGroup);
    }

    /**
     * Checks if two student groups are equal
     * @param g1 String student group id
     * @param g2 String student group id
     * @return True if the two groups are the same + vice versa
     */

    private boolean isGroupConflict(String g1, String g2) {

        // "All" conflicts with everyone. "Group A" conflicts with "Group A" (but not B).
        if (g1.equalsIgnoreCase("All") || g2.equalsIgnoreCase("All")) return true;
        return g1.equalsIgnoreCase(g2);
    }

    /**
     * Outputs string version of a timeslot
     * @return String details of a timeslot
     */

    public String toString() {
        return day + " " + timeSlot + " " + moduleCode + " " + roomCode +
                " (" + classType + ") - " + lecturerName +
                " Weeks " + startWeek + "-" + endWeek;
    }
}
