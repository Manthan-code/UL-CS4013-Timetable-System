package project_classes;

public abstract class User {
	private String name;
	private String id;
	
	/*** 
     * User constructor, takes in basic info about a person
     * */
	//constructor
	public User(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	//get and set
/***
 * returns name
 */
	public String getName() {
		return name;
	}
	/***
	 * returns ID
	 */
	public String getId() {
		return id;
	}

	
	/***
	 * empty abstract CLI methods
	 */
	//will make these functions in subclasses
	public abstract void displayDashboard();
    public abstract void viewTimetable();
    
    
}
