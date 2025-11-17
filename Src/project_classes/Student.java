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

    public ArrayList<Module> getModules() {
        return modulesList;
    }

    public String getProgramme() {
        return programme;
    }

    public int getStudentGroup() {
        return studentGroup;
    }

    public void enrollInModule(Module newModule) {
        if (!modulesList.contains(newModule)) {
            modulesList.add(newModule);
        }
    }

    public void dropModule(Module chosenModule) {
        modulesList.remove(chosenModule);
    }

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

