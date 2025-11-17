package project_classes;

import java.util.ArrayList;

public class Student extends User {
    private String programme;
    private int yearOfStudy;
    private int studentGroup; //this shouldnt be part of the student class, i just need it here to test code
    private ArrayList<Module> modulesList;

    public Student(String name, String id, int yearOfStudy, String programme, String password, int studentGroup, ArrayList<Module> modules) {
        super(name, id, Role.STUDENT, password);
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
     * Gets the program the student is in
     * @return String value
     **/

    public String getProgramme() {
        return programme;
    }
    /***
     * Gets the ID of the group the student is in
     * @return int ID of the student's group
     **/

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
    
    public String toString(){
    	return "Name: " + getName() + "\n" + 
    			"Id: " + getId() + "\n" + 
    			"Role: " + getRole() + "\n" + 
    			"Year: " + yearOfStudy + "\n" + 
    			"Programme: " + programme + "\n" + 
    			"Password: " + "secure password omg" +  "\n" + 
    			"Student Group: " + studentGroup + "\n" + 
    			"Modules: " + modulesList.toString() + "\n";
    }
}

