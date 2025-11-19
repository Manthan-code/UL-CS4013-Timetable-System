package project_classes;

public abstract class User {

	
	private String name;
	private String id;
	
	//constructor
	public User(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	//get and set
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}

	
	
	//will make these functions in subclasses
	public abstract void displayDashboard();
    public abstract void viewTimetable();
    
    
}
