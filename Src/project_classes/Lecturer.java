package project_classes;

import project_classes.util.ArrayList;

public class Lecturer extends User{
	private ArrayList<Module> teachingModules;
	//private ArrayList<Group> classGroupList; //again i think i need some sort of Group class for this
	
	
	public Lecturer(String name, String id, String password, ArrayList<Module> modules) {
        super(name, id, Role.STUDENT, password);
        teachingModules = modules;
        //add group later
    }

	public ArrayList<Module> getTeachingModules() {
		return teachingModules;
	}


	public void setTeachingModules(ArrayList<Module> teachingModules) {
		this.teachingModules = teachingModules;
	}
	
	//lots to do
	//assign/drop modules
	//teaching session? work hours? availability
	//overlapping/overbooking
	//i think we need session class which has start time, end time, week day etc.
	//time table system
	//teaching load, its totalTeachingHours()
	
	@Override
	public void displayDashboard() {
        System.out.println("=== LECTURER DASHBOARD ===");
        System.out.println("Lecturer Name: " + getName());
        System.out.println("Lecturer ID: " + getId());
        System.out.println("No. of Teaching Modules: " + teachingModules.size());
        //System.out.println("Group: " + Group.getGroupId()); //using some group class
        System.out.println("\n");
    }

	@Override
	public void viewTimetable() {
		// TODO Auto-generated method stub
		
	}
}
