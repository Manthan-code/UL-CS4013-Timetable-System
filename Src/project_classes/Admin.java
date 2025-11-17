package project_classes;

public class Admin extends User{


    public Admin(String name, String id, Role role, String password) {
        super(name, id, role, password);
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
    
    @Override
	public void displayDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewTimetable() {
		// TODO Auto-generated method stub
		
	}
	
}
