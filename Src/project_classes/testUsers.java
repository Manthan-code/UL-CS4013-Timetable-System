package project_classes;

import project_classes.util.ArrayList;
import project_classes.util.Arrays;

public class testUsers {
	public static void main(String[] args) {
		Student newStudent = new Student(
				"Mickey Mouse", 
				"12345678", 
				2, 
				"Bsc Computers", 
				"SecurePass25!", 
				1, 
				new ArrayList<Module>(
				Arrays.asList(
						new Module("CS101", "Computers"),
						new Module("CS102", "Operating Systems"),
						new Module("CS103", "Java Programming")
						)));
		
		System.out.println(newStudent.toString());
		newStudent.displayDashboard();
		newStudent.viewTimetable();
	}
}
