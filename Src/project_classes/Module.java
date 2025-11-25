package project_classes;

/**
 * Represents a module in the university timetable system.
 * Stores the module code, name, and weekly teaching hours
 * for lectures, labs, and tutorials.
 */
public class Module {

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

    private String moduleCode;        // e.g., "CS4013"
    private String moduleName;        // e.g., "Software Development"
    private double lectureHours;      // weekly lecture hours
    private double labHours;          // weekly lab hours
    private double tutorialHours;     // weekly tutorial hours

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
