package project_classes;

import project_classes.util.ArrayList;
import project_classes.util.HashSet;
import project_classes.util.Set;

public class TimetableManager {
    private ArrayList<TimeSlot> timeTableEntries;

    public TimetableManager() {
        timeTableEntries = new ArrayList<>();
    }

    /**
     * Adds a timeslot to the entries for the timetable
     * @param timeslot The timeslot to be added to timetable
     */
    public void add(TimeSlot timeslot) {
        timeTableEntries.add(timeslot);
    }
    /**
     * Deletes a time slot from the timetable
     * @param timeslot Time slot in timetable to be removed
     */
    public void delete(TimeSlot timeslot) {
        if(timeTableEntries.contains(timeslot)) {
            timeTableEntries.remove(timeslot);
        } else {
            System.out.println("Timetable does not contain timeslot " + timeslot);
        }
    }
    /**
     * Clears the timetable completely
     */
    public void clear() {
        timeTableEntries.clear();
    }

    /**
     * Gets the modules the user is taking in timetable based on module code
     * @return String set of module codes
     */

    public String getModules() {

        // Set to remove duplicate module codes
        Set <String> modules = new HashSet<>();
        for(TimeSlot timeslot : timeTableEntries) {
            modules.add(timeslot.getModule().getModuleCode());
        }
        return modules.toString();
    }

    /**
     * Gets the lecturers the user has in timetable
     */

    public String getLecturers() {

        ArrayList<String> lecturers = new ArrayList<>();
        for(TimeSlot timeslot : timeTableEntries) {
            lecturers.add(timeslot.getModule().getLecturers());
        }

        return lecturers.toString();
    }

    /**
     * Builds the timetable graphically for the command line
     */

    public void build() {

    }

    /**
     * Displays the timetable to the user on command line
     */

    public void display() {

    }
}
