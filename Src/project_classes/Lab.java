package project_classes;

public class Lab extends Room {
    int labHours;

    public Lab(String roomType, int capacity, String roomCode) {
        super(roomType, capacity, roomCode);

    }

    /**
     * returns lab hours for a lab
     * @return int labHours
     */
    int getLabHours(){
        return labHours;
    }
}
