package project_classes;

import java.util.*;

public class Module {
    /**
     * Class which represents a module in the timetable
     */

    private String moduleCode;
    private String name;
    private double lectureHours;
    private double labHours;
    private double tutorialHours;


    public Module(String moduleCode,String name,double lectureHours,double labHours,double tutorialHours) {
        this.moduleCode = moduleCode;
        this.name = name;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
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
     * Gets the module code of the module
     * @return String moduleCode
     */
    public String getModuleCode() { return moduleCode;}

    /**
     * Gets the module name
     * @return String name of the module
     */
    public String getModuleName(){return name;}


    @Override
    public String toString() {

        return "Module: " + moduleCode +
                "\n Lecture Hours: " + lectureHours +
                "\n Lab Hours: " + labHours +
                "\n Tutorial Hours: " + tutorialHours;
    }
}
