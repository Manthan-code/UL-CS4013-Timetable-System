//Room

package project_classes;
import java.util.Objects;

public class Room {
    /**
     * Class to handle rooms in the timetable system
     */
    private String roomType;
    private int maxCapacity;
    private String roomCode;
    private double hours;

    /**
     * Constructs room object
     * @param roomType String type of room. Either LEC/TUT or LAB.
     * @param maxCapacity int maximum capacity of a room
     * @param roomCode String unique code of a room. E.g. "ERB001"
     * @param hours Double hours of availability of a room
     */
    public Room(String roomType, int maxCapacity, String roomCode, double hours) {
        if (roomType.equalsIgnoreCase("LEC") ||
                roomType.equalsIgnoreCase("LAB") ||
                roomType.equalsIgnoreCase("TUT")) {

            this.roomType = roomType.toUpperCase();
        } else {
            this.roomType = "UNKNOWN";
        }

        this.maxCapacity = maxCapacity;
        this.roomCode = roomCode;
        this.hours = hours;
    }

    /**
     * Constructs room object based on room code only
     * @param roomCode String unique code of a room. E.g. "CSG001"
     */
    public Room(String roomCode) {
        this.roomCode = roomCode;
    }

    /**
     * Constructs room that doesn't have specified hours of availability.
     * @param roomType String type of room. Either LEC/TUT or LAB.
     * @param maxCapacity int maximum capacity of a room
     * @param roomCode String unique code of a room. E.g. "C1062"
     */

    public Room(String roomType, int maxCapacity, String roomCode) {
        if (roomType.equalsIgnoreCase("LEC") ||
                roomType.equalsIgnoreCase("LAB") ||
                roomType.equalsIgnoreCase("TUT")) {

            this.roomType = roomType.toUpperCase();

        } else {
            this.roomType = "UNKNOWN";
        }

        this.maxCapacity = maxCapacity;
        this.roomCode = roomCode;
    }

    /** Gets the room's type. */
    public String getRoomType() { return roomType; }

    /** Gets the room's max capacity. */
    public int getMaxCapacity() { return maxCapacity; }

    /** Gets the room's unique code. */
    public String getRoomCode() { return roomCode; }

    /** Gets the room's hours of availability. */
    public double getHours() { return hours; }



    /** Sets the room's type */
    public void setRoomType(String roomType) { this.roomType = roomType; }

    /** Sets the room's max capacity */
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    /** Sets the room's unique code */
    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }

    /** Sets the room's hours of availability*/
    public void setHours(double hours) { this.hours = hours; }

    /**
     * Method to check if two rooms are equal
     * @param o   the reference object with which to compare.
     * @return True if the rooms are equal and vice versa.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(roomCode, room.roomCode);
    }

    /**
     * Checks if two rooms are equal based on hashcode
     * @return True if the hashcodes are equal and vice versa.
     */

    @Override
    public int hashCode() {
        return Objects.hash(roomCode);
    }

    /**
     * Returns the details of a room in string format
     * @return String details for a room
     */
    @Override
    public String toString() {
        return roomCode + " (" + roomType + ", cap=" + maxCapacity + ")";
    }
}
