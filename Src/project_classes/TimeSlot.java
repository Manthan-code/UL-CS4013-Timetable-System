package project_classes;

import java.time.LocalTime;

public class TimeSlot {
    /**
     * Java class which represents a time slot to be added into timetable
     */

    private String dayOfWeek;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private String moduleCode;
    private Module module;
    private String classType;

    public TimeSlot(String dayOfWeek, LocalTime startingTime, LocalTime endingTime,
            String moduleCode, String classType) {
        this.dayOfWeek = dayOfWeek;
        this.startingTime = startingTime;
        this.endingTime = endingTime;

        this.moduleCode = moduleCode;
        this.module = new Module(moduleCode);
        this.classType = classType;
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
    public LocalTime getStartTime() {return startingTime;}
    /**
     * Gets the end time of the slot
     * @return LocalTime End time of the time slot
     */
    public LocalTime getEndTime() {return endingTime;}

    /**
     * Gets the module that the time slot is part of
     * @return Module module that the time slot is concerned with
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
                "\n Duration: " + startingTime.toString() + "-" + endingTime.toString() +
                "\n Module Code: " + moduleCode +
                "\n Class Type: " + classType;
    }

}
