package project_data;

import java.io.*;
import java.util.*;

import project_classes.Lecturer;
import project_classes.Room;
import project_classes.Module;
import project_io.CSVReader;


public class DataManager {
    /**
     * Class that will manage data from CSV files
     */

    public DataManager() {

    }

    /**
     * Creates room objects from CSV file
     * @return List of rooms made from CSV file
     */

    public static List<Room> loadRooms(String filename){
        // List of rooms
        List<Room> rooms = new ArrayList<>();
        // Try and read the CSV rooms file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;
            // To skip the first line
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }

                String[] values = line.split(",");
                // If there is at least one row representing a room
                if (values.length >= 4) {
                    String roomType = values[0].trim();
                    int maxCapacity = Integer.parseInt(values[1].trim());
                    String roomCode = values[2].trim();
                    double hours = Double.parseDouble(values[3].trim());
                    rooms.add(new Room(roomType, maxCapacity, roomCode, hours));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading rooms: " + e.getMessage());
        }
        return rooms;
    }

    /**
     * Creates module objects from CSV file
     * @return List of modules created from CSV file
     */

    public static List<Module> loadModules(String filename){
        List<Module> modules = new ArrayList<>();
        // Try to read the file from the
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }

                String[] values = line.split(",");
                if (values.length >= 5) {
                    String moduleCode = values[0].trim();
                    String name = values[1].trim();
                    double lectureHours = Double.parseDouble(values[2].trim());
                    double tutHours = Double.parseDouble(values[3].trim());
                    double labHours = Double.parseDouble(values[4].trim());
                    modules.add(new Module(moduleCode, name,lectureHours, tutHours, labHours));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading modules: " + e.getMessage());
        }
        return modules;

    }
    /**
     * Saves all the data loaded from the CSV files
     */

    public void saveAllData() {
        //will save updated timetables + modules + rooms
    }

}
