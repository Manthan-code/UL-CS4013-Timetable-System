package project_data;

import project_classes.Module;
import project_classes.Room;
import project_io.CSVReader;
import java.util.*;


public class DataManager {
    /**
     * Loads Modules, Rooms and Courses from CSV files.
     */

    private List<Module> modules = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    // courseName -> list of module codes
    private Map<String, List<String>> courseMap = new HashMap<>();

    /** Gets the list of modules from CSV file*/
    public List<Module> getModules() {
        return modules;
    }

    /** Gets the list of rooms from CSV file*/
    public List<Room> getRooms() {
        return rooms;
    }


    /** Gets the course names from CSV file*/
    public Set<String> getCourseNames() {
        return courseMap.keySet();
    }

    /** Gets the modules for a course listed on the CSV file */
    public List<String> getModulesForCourse(String courseName) {
        return courseMap.getOrDefault(courseName, new ArrayList<>());
    }

    /**
     * Loads the CSV file with details about modules
     * @param filePath String name of file path
     */
    public void loadModules(String filePath) {

        modules.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: Module file empty or missing: " + filePath);
            return;
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] p = rows.get(i);
            if (p.length < 5) continue;

            try {
                String code = p[0].trim();
                String name = p[1].trim();
                int lec = Integer.parseInt(p[2].trim());
                int lab = Integer.parseInt(p[3].trim());
                int tut = Integer.parseInt(p[4].trim());

                modules.add(new Module(code, name, lec, lab, tut));

            } catch (Exception e) {
                System.out.println("Bad module entry at row " + i);
            }
        }
        System.out.println("Loaded " + modules.size() + " modules.");
    }

    /**
     * Loads the CSV file with details about rooms
     * @param filePath String name of file path
     */
    public void loadRooms(String filePath) {

        rooms.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: Room file empty or missing: " + filePath);
            return;
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] p = rows.get(i);
            if (p.length < 4) continue;

            try {
                String type = p[0].trim();
                int capacity = Integer.parseInt(p[1].trim());
                String code = p[2].trim();
                double hours = Double.parseDouble(p[3].trim());

                rooms.add(new Room(type, capacity, code, hours));

            } catch (Exception e) {
                System.out.println("Bad room entry at row " + i);
            }
        }

        System.out.println("Loaded " + rooms.size() + " rooms.");
    }

    /**
     * Loads the CSV file with details about courses/programmes
     * @param filePath String name of file path
     */
    public void loadCourses(String filePath) {

        courseMap.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: Course file empty or missing: " + filePath);
            return;
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] p = rows.get(i);
            if (p.length < 2) continue;

            String course = p[0].trim();
            String module = p[1].trim();

            // Add module to the course list
            courseMap.putIfAbsent(course, new ArrayList<>());
            courseMap.get(course).add(module);
        }

        System.out.println("Loaded " + courseMap.size() + " courses.");
    }
}
