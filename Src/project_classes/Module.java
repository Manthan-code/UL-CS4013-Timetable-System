package project_classes;

import java.util.*;

public class Module {
    /**
     * Class which represents a module in the timetable
     */

    private String name;
    private double lectureHours;
    private double labHours;
    private double tutorialHours;
    private int lecturerCount;
    private String lecturer;
    private String moduleCode;

    private ArrayList<String> programmes;
    private ArrayList<String> lecturers;

    public Module(String moduleCode,String name) {
        this.moduleCode = moduleCode;
        this.lecturerCount = 0;
        this.lectureHours = 0.0;
        this.labHours = 0.0;
        this.tutorialHours = 0.0;
    }

    public Module(double lectureHours,double labHours,double tutorialHours,
                  int lecturerCount,String lecturer,String moduleCode,ArrayList<String> programmes) {
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
        this.lecturerCount = lecturerCount;
        this.programmes = new ArrayList<>();

        // Relevant data field used to represent lecturer(s) of module based on lecturerCount
        if(lecturerCount == 1) {
            this.lecturer = lecturer;
        } else {
            this.lecturers = new ArrayList<>();
        }
    }

    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Gets the lecture hours of the module
     * @return double lectureHours
     */
    public double getLectureHours() { return lectureHours;}
    /**
     * Gets the lab hours of the module
     * @return double labHours
     */
    public double getLabHours() { return labHours;}
    /**
     * Gets the tutorial hours of the module
     * @return double tutorialHours
     */
    public double getTutorialHours() { return tutorialHours;}
    /**
     * Gets the number of lecturers assigned to the module
     * @return int lecturerCount
     */
    public int getLecturerCount() {return lecturerCount;}
    /**
     * Gets the module code of the module
     * @return String moduleCode
     */
    public String getModuleCode() { return moduleCode;}

    /**
     * Gets the module name
     * @return String name of the module
     */
    public String getModuleName(){return name;}

    /**
     * Gets the programme(s) the module is taken in
     * @return String of Array List programmes
     */
    public String getProgrammes() {
        return new ArrayList<>(programmes).toString();
    }
    /**
     * Gets the lecturer(s) of the module
     * @return String representing the lecturer(s) assigned to module
     */
    public String getLecturers() {
        String lectureString;
        if(lecturerCount == 1) {
            lectureString =  lecturer;
        } else {
            lectureString = lecturers.toString();
        }

        return lectureString;

    }

    /**
     * Adds programme to list of programmes the module is in
     * @param programme String name/code of the programme
     */
    public void addProgramme(String programme) {
        programmes.add(programme);
    }
    /**
     * Removes programme from the list of programmes the module is in
     * @param programme String name/code of the programme
     */
    public void removeProgramme(String programme) {
        if(programmes.contains(programme)) {
            programmes.remove(programme);
        } else {
            System.out.println("Programme " + programme + " not found.");
        }
    }

    @Override
    public String toString() {

        return "Module: " + moduleCode +
                "\n Lecture Hours: " + lectureHours +
                "\n Lab Hours: " + labHours +
                "\n Tutorial Hours: " + tutorialHours +
                "\n Lecturer Count: " + lecturerCount +
                "\n Lecturers: " + getLecturers() +
                "\n Programmes: " + getProgrammes();

    }
}
