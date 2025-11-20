package project_classes;

public class Classroom extends Room {
    private int Hours;
    private int tutorialHours;
    private int labHours;


    public Classroom(String roomType, int capacity, String roomCode, double hours) {
        super(roomType, capacity, roomCode, hours);
    }

    /**
     * returns hours for a lecture or a tutorial
     * @return int lectureHours
     */
    int getLectureHours(){
        return Hours;
    }
}
