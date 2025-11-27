package project_classes;

import java.util.*;

public class Room {
    private String roomType;
    private int maxCapacity;
    private String roomCode;
    private double hours;


    public Room(String roomType, int maxCapacity, String roomCode, double hours) {

        // Check to see if roomType is valid - E.g. Either "lec" or "LEC"
        if(roomType.equalsIgnoreCase("LEC") || roomType.equalsIgnoreCase("LAB")
                || roomType.equalsIgnoreCase("TUT")) {
            this.roomType = roomType;
            this.maxCapacity = maxCapacity;
            this.roomCode = roomCode;
            this.hours = hours;
        } else {
            // Set to "Unknown" if user enters anything other than lec/LEC,lab/LAB or tut/TUT
            this.roomType = "Unknown";
            this.maxCapacity = maxCapacity;
            this.roomCode = roomCode;
            this.hours = hours;
        }
    }

    public Room(String roomCode) {
            this.roomCode = roomCode;
    }

    public Room(String roomType, int maxCapacity, String roomCode) {
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        this.roomCode = roomCode;
    }

    /**
     * returns the type of room
     * @return String roomType
     */
    String getRoomType(){return roomType;}

    /**
     * returns the max capacity of a room
     * @return int capacity
     */
    int getMaxCapacity(){
        return maxCapacity;
    }

    /**
     * returns the roomCode of a room
     * @return String roomCode
     */
    String getRoomCode(){
        return roomCode;
    }

    /**
     * returns the hours used by a room
     * @return double hours
     */
    double getHours() {
        return hours;
    }


    /**
     * Sets the type of room for a room object
     * @param roomType String room type
     */

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    /**
     * Sets the max capacity of a room
     * @param maxCapacity Int max capacity of a room
     */
    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    /**
     * Sets the room code for a room
     * @param roomCode String code of a room
     */
    public void setRoomCode(String roomCode){
        this.roomCode = roomCode;
    }

    /**
     * Sets the hours for a room
     * @param hours Double hours for a room
     */
    public void setHours(double hours){
        this.hours = hours;
    }

    /**
     * Checks if two rooms are the same based on the code
     * @param o   the reference object with which to compare.
     * @return Boolean on whether the two rooms are the same
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        // Two rooms are equal if their IDs are the same
        return Objects.equals(roomCode, room.roomCode);
    }

    /**
     * Gets the hashCode of Module object
     * @return int hashCode of a Room object
     */

    @Override
    public int hashCode() {
        return Objects.hash(roomCode);
    }
}
