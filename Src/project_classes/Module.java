package project_classes;

import java.util.Objects;

/**
 * Represents a module in the university timetable system.
 * Stores the module code, name, and weekly teaching hours
 * for lectures, labs, and tutorials.
 */
public class Module {

    private String moduleCode;        // e.g., "CS4013"
    private String moduleName;        // e.g., "Software Development"
    private double lectureHours;      // weekly lecture hours
    private double labHours;          // weekly lab hours
    private double tutorialHours;
    private String lecturer;// weekly tutorial hours

    public Module(String moduleCode,String lecturer) {
        this.moduleCode = moduleCode;
        this.lecturer = lecturer;
    }

    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * The type of class session the module can deliver.
     * LEC = Lecture
     * LAB = Laboratory
     * TUT = Tutorial
     */
    public enum ClassType {
        LEC,
        LAB,
        TUT
    }

    /**
     * Creates a module with its key information.
     *
     * @param moduleCode     module code (e.g., CS4013)
     * @param moduleName     full module name
     * @param lectureHours   weekly lecture hours
     * @param labHours       weekly lab hours
     * @param tutorialHours  weekly tutorial hours
     */
    public Module(String moduleCode, String moduleName,
                  double lectureHours, double labHours, double tutorialHours) {

        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
    }

    // ===========================
    //         GETTERS
    // ===========================

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public double getLectureHours() {
        return lectureHours;
    }

    public double getLabHours() {
        return labHours;
    }

    public double getTutorialHours() {
        return tutorialHours;
    }

    // ===========================
    //         SETTERS
    // ===========================

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setLectureHours(double lectureHours) {
        this.lectureHours = lectureHours;
    }

    public void setLabHours(double labHours) {
        this.labHours = labHours;
    }

    public void setTutorialHours(double tutorialHours) {
        this.tutorialHours = tutorialHours;
    }

    /**
     * Checks if two modules are equal via module code
     * @param o   the reference module with which to compare.
     * @return Boolean to check whether the two modules are the same
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        // Two rooms are equal if their IDs are the same
        return Objects.equals(moduleCode, module.moduleCode);
    }

    /**
     * Gets the hashCode of Module object
     * @return int hashCode of a Module object
     */

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode);
    }

    /**
     * Summary of module details.
     */
    @Override
    public String toString() {
        return "Module Code: " + moduleCode +
                "\nModule Name: " + moduleName +
                "\nLecture Hours (LEC): " + lectureHours +
                "\nLab Hours (LAB): " + labHours +
                "\nTutorial Hours (TUT): " + tutorialHours;
    }
}
