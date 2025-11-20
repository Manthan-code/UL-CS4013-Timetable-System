package project_classes;

public class Admin extends User{


    public Admin(String name, String id) {
        super(name, id);
    }
    //adding these basic functions to admin, i will do a proper job later
    public void enrollInModule(Module newModule) {
        if (!modulesList.contains(newModule)) {
            modulesList.add(newModule);
        }
    }

    public void dropModule(Module chosenModule) {
        modulesList.remove(chosenModule);
    }
    
    /**
     * Displays the timetable dashboard to th user on the screen.
     */

    @Override
	public void displayDashboard() {
		// TODO Auto-generated method stub
		
	}

    /**
     * Allows user to view the timetable.
     */

	@Override
	public void viewTimetable() {
		// TODO Auto-generated method stub
		
	}
	
}
