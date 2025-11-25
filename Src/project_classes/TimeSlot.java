package project_classes;


public class TimeSlot {
    /**
     * Java class which represents a time slot to be added into timetable
     */

    private String dayOfWeek;
    private String startingTime;
    private String endingTime;
    private String moduleCode;
    private String roomCode;
    private Module module;
    private String classType;
    private int startingWeek;
    private int endingWeek;

    public TimeSlot(String dayOfWeek, String startingTime, String endingTime,
            String moduleCode,String roomCode, String classType,int startingWeek,int endingWeek) {
        this.dayOfWeek = dayOfWeek;
        this.startingTime = startingTime;
        this.endingTime = endingTime;

        this.moduleCode = moduleCode;
        this.module = new Module(moduleCode);
        this.roomCode = roomCode;
        this.classType = classType;

        this.startingWeek = startingWeek;
        this.endingWeek = endingWeek;
    }

    /**
     * Gets the day of week the time slot is on
     * @return String day of the week
     */
    public String getDayOfWeek() {return dayOfWeek;}
    /**
     * Gets the start time of the slot
     * @return LocalTime Start time of the time slot
     */
    public String getStartTime() {return startingTime;}
    /**
     * Gets the end time of the slot
     * @return LocalTime End time of the time slot
     */
    public String getEndTime() {return endingTime;}

    /**
     * Gets the module that the time slot is part of
     * @return module that the time slot is concerned with
     */
    public Module getModule() {return module;}

    /**
     * Gets the type of class that the timeslot is
     * TUT - Tutorial
     * LAB - Lab
     * LEC - Lecture
     * @return String representing either a lecture , lab or tutorial.
     */
    public String getClassType() {return classType;}



    @Override
    public String toString() {
        return "Day of week: " + dayOfWeek +
                "\n Duration: " + startingTime + "-" + endingTime +
                "\n Module Code: " + moduleCode +
                "\n Class Type: " + classType;
    }

}
