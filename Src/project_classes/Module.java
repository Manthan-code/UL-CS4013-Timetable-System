//module

package project_classes;

import java.util.Objects;


public class Module {

    private String moduleCode;        // e.g., "CS4013"
    private String moduleName;        // e.g., "Object Oriented Development"
    private double lectureHours;
    private double labHours;
    private double tutorialHours;

    //moduleCode Constructor
    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    //main Constructor
    public Module(String moduleCode, String moduleName,
                  double lectureHours, double labHours, double tutorialHours) {

        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
    }

    //Get Methods

    public String getModuleCode() { return moduleCode; }
    public String getModuleName() { return moduleName; }
    public double getLectureHours() { return lectureHours; }
    public double getLabHours() { return labHours; }
    public double getTutorialHours() { return tutorialHours; }

    //Set Methods

    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }
    public void setLectureHours(double lectureHours) { this.lectureHours = lectureHours; }
    public void setLabHours(double labHours) { this.labHours = labHours; }
    public void setTutorialHours(double tutorialHours) { this.tutorialHours = tutorialHours; }

    //toString
    @Override
    public String toString() {
        return "Module Code: " + moduleCode +
                "\nModule Name: " + moduleName +
                "\nLecture Hours: " + lectureHours +
                "\nLab Hours: " + labHours +
                "\nTutorial Hours: " + tutorialHours;
    }
}
