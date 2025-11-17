package project_classes;

public abstract class User {
	//general user attributes that i think we need	
	
	enum Role {
		ADMIN,
		STUDENT,
		LECTURER
	}
	
	private String name;
	private String id;
	private Role role; //using enum
	private String password;
	
	/*** 
     * User constructor, takes in basic info about a person
     * */
	//constructor
	public User(String name, String id, Role role, String password) {
		this.name = name;
		this.id = id;
		this.role = role;
		this.password = password;
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
	 * returns role
	 */
	public Role getRole() {
		return role;
	}
	/***
	 * sets password
	 */
	public void setPassword(String newPassword) {
        this.password = newPassword;
    }
	
	/***
	 * empty abstract CLI methods
	 */
	//will make these functions in subclasses
	public abstract void displayDashboard();
    public abstract void viewTimetable();
    
    
}
