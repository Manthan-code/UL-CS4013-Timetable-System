//TimeSlot

package project_classes;
import java.time.LocalTime;

public class TimeSlot {
    /**
     * Class that handles slots of time for each timetable entry
     */
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**Gets the start time of a slot*/
    public LocalTime getStartTime() {
        return startTime;
    }

    /** Sets the end time of a slot*/
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Checks if two timeSlots overlap each other
     * @param other The timeSlot to be checked
     * @return True if the timeSlots overlap and vice versa.
     */
    public boolean overlaps(TimeSlot other) {
        return this.startTime.isBefore(other.endTime)
                && other.startTime.isBefore(this.endTime);
    }

    /**
     * Returns the String details of a time slot in the form "startTime(hh:mm)-endTime(hh:mm)"
     * @return String details of a timeSlot.
     */

    public String toString() {
        return startTime + " - " + endTime;
    }
}
