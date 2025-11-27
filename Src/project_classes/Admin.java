package project_classes;

import project_data.DataManager;

import java.util.List;
import java.util.Set;

public class Admin extends User{

    private Set <Module> modulesList;


    public Admin(String name, String id) {
        super(name, id);
        modulesList = DataManager.loadModules("modules.csv");

    }

    public void enrollInModule(Module newModule) {
        if (!(modulesList.contains(newModule))) {
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
