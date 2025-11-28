package project_classes;

import java.util.Objects;

/**
 * Represents a module in the timetable system.
 */
public class Module {

    private String moduleCode;        // e.g., "CS4013"
    private String moduleName;        // e.g., "Object Oriented Development"
    private double lectureHours;
    private double labHours;
    private double tutorialHours;

    /**
     * Basic constructor using only module code.
     */
    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Main constructor used when loading full module info.
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
    // GETTERS
    // ===========================

    public String getModuleCode() { return moduleCode; }
    public String getModuleName() { return moduleName; }
    public double getLectureHours() { return lectureHours; }
    public double getLabHours() { return labHours; }
    public double getTutorialHours() { return tutorialHours; }

    // ===========================
    // SETTERS
    // ===========================

    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }
    public void setLectureHours(double lectureHours) { this.lectureHours = lectureHours; }
    public void setLabHours(double labHours) { this.labHours = labHours; }
    public void setTutorialHours(double tutorialHours) { this.tutorialHours = tutorialHours; }

    // ===========================
    // EQUALS + HASHCODE
    // ===========================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return Objects.equals(moduleCode, module.moduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode);
    }

    @Override
    public String toString() {
        return "Module Code: " + moduleCode +
                "\nModule Name: " + moduleName +
                "\nLecture Hours: " + lectureHours +
                "\nLab Hours: " + labHours +
                "\nTutorial Hours: " + tutorialHours;
    }
}
