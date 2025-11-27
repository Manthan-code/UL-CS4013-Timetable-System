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
     * Method to set and get the roomID for s room based on the room's maxCapacity
     * maxCapacity thresholds are based on average lab/lecture/tutorial room sizes in UL.
     * Could help when allocating lab/tutorial groups to rooms ?
     * @param maxCapacity The max capacity of a room
     * @return The String representing the room's max capacity that has either "LAB","LEC" or "TUT"
     * and a random digit from 0-100
     */

    public String getRoomID(int maxCapacity) {
        String roomID = "";
        // Generates random digit between 0-100
        String randomNum = new Random().nextInt(100)+"";
        if(roomType.equalsIgnoreCase("LAB") && maxCapacity <= 40) {
            roomID = "LAB" + randomNum;
        } else if(roomType.equalsIgnoreCase("TUT") && maxCapacity <= 60) {
            roomID = "TUT" + randomNum;
        } else if(roomType.equalsIgnoreCase("LEC") && maxCapacity > 60) {
            roomID = "LEC" + randomNum;
        } else if(roomType.equalsIgnoreCase("Unknown")) {
            roomID = randomNum;
        }

        return roomID;

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
     * Checks if two rooms are the aame based on the code
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
