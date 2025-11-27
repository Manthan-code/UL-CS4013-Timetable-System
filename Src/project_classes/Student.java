package project_classes;

import java.util.ArrayList;

public class Student extends User {
    private String programme;
    private int yearOfStudy;
    private int studentGroup; //this shouldn't be part of the student class, i just need it here to test code
    private ArrayList<Module> modulesList;

    public Student(String name, String id, int yearOfStudy, String programme,
                                 int studentGroup, ArrayList<Module> modules) {
        super(name, id);
        this.yearOfStudy = yearOfStudy;
        this.programme = programme;
        this.studentGroup = studentGroup;
        this.modulesList = modules;
    }
    /***
     * Gets the modules the student is taking
     * @return List of modules the student is taking
     */

    public ArrayList<Module> getModules() {
        return modulesList;
    }
/*** 
 * returns program String value
 * */
    public String getProgramme() {
        return programme;
    }
    /*** 
     * returns studentGroup int value
     * */
    public int getStudentGroup() {
        return studentGroup;
    }
    

    /***
     * Displays CLI dashboard to student
     **/

    @Override
    public void displayDashboard() {
        System.out.println("=== STUDENT DASHBOARD ===");
        System.out.println("Student Name: " + getName());
        System.out.println("Student ID: " + getId());
        System.out.println("Programme: " + programme);
        System.out.println("Year: " + yearOfStudy);
        System.out.println("No. of Enrolled Modules: " + modulesList.size());
        //System.out.println("Group: " + Group.getGroupId()); //using some group class
        System.out.println("\n");
    }
    /*** 
     * prints Timetable for student
     * */
    
    //NOT DONE YET!!!

    /***
     * Prints timetable for student
     */

    @Override
    public void viewTimetable() {
        System.out.println("=== STUDENT TIMETABLE ===");
        System.out.println("Student: " + getName() + " (" + getId() + ")");
        for (int i = 0; i < modulesList.size(); i++) {
            Module module = modulesList.get(i);
            System.out.println("Module: " + module.getModuleCode() + " - " + module.getModuleName());
        }
        System.out.println("\n");
    }
    
    /*** 
     * basic toString method which returns student info
     * */
    public String toString(){
    	return "Name: " + getName() + "\n" + 
    			"Id: " + getId() + "\n" +
    			"Year: " + yearOfStudy + "\n" + 
    			"Programme: " + programme + "\n" +
    			"Student Group: " + studentGroup + "\n" + 
    			"Modules: " + modulesList.toString() + "\n";
    }
}

