package project_classes;

import java.util.ArrayList;

public class Lecturer extends User{
	private ArrayList<Module> teachingModules;
	//private ArrayList<Group> classGroupList; //again I think I need some sort of Group class for this
	
	
	public Lecturer(String name, String id, String password, ArrayList<Module> modules) {
        super(name, id);
        teachingModules = modules;
        //add group later
    }

    /**
     * Gets the list of modules that a lecturer is teaching
     * @return ArrayList of modules a lecturer is teaching
     */
	public ArrayList<Module> getTeachingModules() {
		return teachingModules;
	}

    /**
     * Adds a module that a lecturer is currently teaching
     * @param module the module that the lecturer is teaching
     */

    public void addTeachingModule(Module module){
        teachingModules.add(module);
    }

    /**
     * Removes a module that a lecturer is currently teaching
     * @param module the module that the lecturer is teaching
     */

    public void removeTeachingModule(Module module){
        if(teachingModules.contains(module)){
            teachingModules.remove(module);
        } else {
            System.out.println("Module Not Found");
        }
    }

    /**
     * Assigns a list of modules for a lecturer to teach
     * @param teachingModules List of modules
     */
	public void setTeachingModules(ArrayList<Module> teachingModules) {
		this.teachingModules = teachingModules;
	}


    /**
     * Displays the timetable to the user on the Command-Line
     */

    //Lots to do
    //Assign/drop modules
    //Teaching session? work hours? availability
    //Overlapping/overbooking
    //I think we need session class which has start time, end time, week day etc.
    //Timetable system
    //Teaching load, its totalTeachingHours()
    @Override
	public void displayDashboard() {
        System.out.println("=== LECTURER DASHBOARD ===");
        System.out.println("Lecturer Name: " + getName());
        System.out.println("Lecturer ID: " + getId());
        System.out.println("No. of Teaching Modules: " + teachingModules.size());
        //System.out.println("Group: " + Group.getGroupId()); //using some group class
        System.out.println("\n");
    }

    /**
     * Allows user to view their timetable
     */

    @Override
	public void viewTimetable() {
		// TODO Auto-generated method stub
		
	}
}
