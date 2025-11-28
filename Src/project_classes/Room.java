package project_classes;

import java.util.Objects;

/**
 * Represents a room used in the timetable system.
 * Room types supported: LEC, LAB, TUT
 */
public class Room {

    private String roomType;     // LEC / LAB / TUT
    private int maxCapacity;
    private String roomCode;
    private double hours;

    public Room(String roomType, int maxCapacity, String roomCode, double hours) {

        // Accept only LEC / LAB / TUT
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

    public Room(String roomCode) {
        this.roomCode = roomCode;
    }

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

    // -------- Getters --------
    public String getRoomType() { return roomType; }
    public int getMaxCapacity() { return maxCapacity; }
    public String getRoomCode() { return roomCode; }
    public double getHours() { return hours; }

    // -------- Setters --------
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }
    public void setHours(double hours) { this.hours = hours; }

    // -------- Equality --------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(roomCode, room.roomCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomCode);
    }

    @Override
    public String toString() {
        return roomCode + " (" + roomType + ", cap=" + maxCapacity + ")";
    }
}
