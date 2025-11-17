package project_classes;

import java.util.ArrayList;

public class Programme {
    /**
     * Class which represents programmes modules are taken in
     */

    private String name;
    private ArrayList<Module> modules;
    private int numberOfYears;
    private int numberOfStudents;
    private String faculty;

    public Programme(String name) {
        this.name = name;
    }

    public Programme(String name, ArrayList<Module> modules,
                     int numberOfYears, int numberOfStudents, String faculty) {
        this.name = name;
        this.modules = modules;
        this.numberOfYears = numberOfYears;
        this.numberOfStudents = numberOfStudents;
        this.faculty = faculty;
    }

    /**
     * Gets the name of a programme
     * @return String name of the programme
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the modules that are in the programme
     * @return The list of modules taken in a programme
     */

    public ArrayList<Module> getModules() {
        return new ArrayList<>(modules);
    }

    /**
     * Gets the number of students enrolled in the programme
     * @return int number of students in the programme
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    /**
     * Gets the faculty the programme is in
     * @return String name of the faculty the programme is under.
     */
    public String getFaculty() {
        return faculty;
    }

}
