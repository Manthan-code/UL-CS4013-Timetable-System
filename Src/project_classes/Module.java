//module

package project_classes;

import java.util.Objects;


public class Module {

    /**
     * Class that represents modules in the timetable system
     */

    private String moduleCode;
    private String moduleName;
    private double lectureHours;
    private double labHours;
    private double tutorialHours;

    /**
     * Constructs a module object based on moduleCode alone
     * @param moduleCode String unique code of module. E.g. CS4013
     */
    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Primary constructor which constructs module with full details
     * @param moduleCode String unique code of module. E.g. "CS4023"
     * @param moduleName String name of module. E.g. "Object-Oriented Development"
     * @param lectureHours Double lecture hours assigned to module
     * @param labHours Double lab hours assigned to module
     * @param tutorialHours Double tutorial hours assigned to module
     */
    public Module(String moduleCode, String moduleName,
                  double lectureHours, double labHours, double tutorialHours) {

        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
    }

    // Getter methods
    /** Gets the module code. */
    public String getModuleCode() { return moduleCode; }
    /** Gets the module title/name. */

    public String getModuleName() { return moduleName; }
    /** Gets the assigned lecture hours */

    public double getLectureHours() { return lectureHours; }
    /** Gets the assigned lab hours. */

    public double getLabHours() { return labHours; }

    /** Gets the assigned tutorial hours. */
    public double getTutorialHours() { return tutorialHours; }

    //Setter Methods
    /** Sets the module code. */
    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }

    /** Sets the name/title of module. */
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    /** Sets the assigned lecture hours. */
    public void setLectureHours(double lectureHours) {this.lectureHours = lectureHours; }

    /** Sets the assigned lab hours. */
    public void setLabHours(double labHours) { this.labHours = labHours; }

    /** Sets the assigned tutorial hours. */
    public void setTutorialHours(double tutorialHours) { this.tutorialHours = tutorialHours; }

    /**
     * Returns details about the module in a String format
     * @return String details of module
     */
    @Override
    public String toString() {
        return "Module Code: " + moduleCode +
                "\nModule Name: " + moduleName +
                "\nLecture Hours: " + lectureHours +
                "\nLab Hours: " + labHours +
                "\nTutorial Hours: " + tutorialHours;
    }
}
