package project_data;

import java.io.*;
import java.util.*;

import project_classes.Lecturer;
import project_classes.Room;
import project_classes.Module;
import project_classes.TimeSlot;
import project_io.CSVReader;


public class DataManager {
    /**
     * Class that will manage data from CSV files
     */

    public DataManager() {

    }

    /**
     * Creates room objects from CSV file
     * @return List of rooms made from CSV file (without duplicates)
     */

    public static Set<Room> loadRooms(String filename) {
        // Try and read the CSV rooms file
        Set<Room> roomSet = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;

            // Set of rooms to remove duplicates
            roomSet = new HashSet<>();
            // To skip the first line
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                // If there is at least one row representing a room
                if (values.length >= 6) {
                    String roomCode = values[4].trim();
                    roomSet.add(new Room(roomCode));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading rooms: " + e.getMessage());
        }
        return roomSet;
    }

    /**
     * Creates module objects from CSV file
     * @return List of modules created from CSV file (without duplicates)
     */

    public static Set<Module> loadModules(String filename) {
        Set<Module> moduleSet = null;
        // Try to read the file from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;

            moduleSet = new HashSet<>();
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length >= 6) {
                    String moduleCode = values[4].trim();
                    String lecturer = values[6].trim();
                    moduleSet.add(new Module(moduleCode, lecturer));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading modules: " + e.getMessage());
        }
        return moduleSet;

    }

    /**
     * Create time slot objects from a CSV file
     * @param filename String file path of CSV file
     * @return List of Timeslots in the system
     */

    public static List<TimeSlot> loadTimeSlots(String filename) {
        List<TimeSlot> slots = null;
        // Try to read the timeslot info from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;

            slots = new ArrayList<>();

            // Skip the first line/header
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length >= 9) {
                    String dayOfWeek = values[0].trim();
                    String startTime = values[1].trim();
                    String endTime = values[2].trim();
                    String moduleCode = values[3].trim();
                    String roomCode = values[4].trim();
                    String classType = values[5].trim();
                    int startingWeek = Integer.parseInt(values[7].trim());
                    int endingWeek = Integer.parseInt(values[8].trim());


                    slots.add(new TimeSlot(dayOfWeek, startTime, endTime,
                              moduleCode, roomCode, classType, startingWeek, endingWeek));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading modules: " + e.getMessage());
        }
        return slots;

    }


}