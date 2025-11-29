//TimeSlot

package project_classes;
import java.time.LocalTime;

public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getters for time
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    // overLapping checker
    public boolean overlaps(TimeSlot other) {
        return this.startTime.isBefore(other.endTime)
                && other.startTime.isBefore(this.endTime);
    }

    //toString
    public String toString() {
        return startTime + " - " + endTime;
    }
}
