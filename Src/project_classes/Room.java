package project_classes;

public class Room {
    String roomType;
    int capacity;
    String roomCode;
    double hours;

    public Room(String s, int i, String s1, double v) {
        this.roomType = s;
        this.capacity = i;
        this.roomCode = s1;
        this.hours = v;
    }


    /**
     * returns the type of room
     * @return String roomType
     */
    String getRoomType(){
        return roomType;
    }

    /**
     * returns the max capacity of a room
     * @return int capacity
     */
    int getCapacity(){
        return capacity;
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
    double getHours(){
        return hours;
    }


}
