package project_data;

import java.io.*;
import java.util.*;

import project_classes.Lecturer;
import project_classes.Room;
import project_classes.Module;
import project_classes.TimeSlot;
import project_io.CSVReader;


public class DataManager {


        // Method which safely handles errors reading int values
        private static int parseIntSafe(String value) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

    /**
     * Returns the list of rooms read from CSV file
     * @param filepath String file path of CSV file
     * @return List of rooms from CSV file (without duplicates)
     */

    public static Set<Room> loadRooms(String filepath) {
           // Set of rooms from CSV file
            Set<Room> rooms = new HashSet<>();

            // Getting rows from CSV file
            List<String[]> rows = CSVReader.readCSV(filepath);

            // Looping through rows
            for (String[] row : rows) {

                if (row.length > 4) {
                    String roomCode = row[4].trim();
                    rooms.add(new Room(roomCode));
                }
            }
            return rooms;
        }

    /**
     * Returns the list of modules read from CSV file
     * @param filepath String file path of CSV file
     * @return List of modules from CSV file (without duplicates)
     */

        public static Set<Module> loadModules(String filepath) {
            // Set of modules from CSV files
            Set<Module> modules = new HashSet<>();

            List<String[]> rows = CSVReader.readCSV(filepath);

            for (String[] row : rows) {
                if (row.length > 6) {
                    String moduleCode = row[4].trim();
                    String lecturer = row[6].trim();
                    modules.add(new Module(moduleCode, lecturer));
                }
            }
            return modules;
        }

    /**
     * Gets list of timeslots ferom CSV File
     * @param filepath String file path of CSV file
     * @return List of Timeslots from CSV file
     */

    public static List<TimeSlot> loadTimeSlots(String filepath) {

            List<TimeSlot> slots = new ArrayList<>();
            List<String[]> rows = CSVReader.readCSV(filepath);

            for (String[] row : rows) {
                if (row.length > 8) {
                    try {
                        String dayOfWeek = row[0].trim();
                        String startTime = row[1].trim();
                        String endTime   = row[2].trim();
                        String moduleCode= row[3].trim();
                        String roomCode  = row[4].trim();
                        String classType = row[5].trim();

                        // Parse integers safely
                        int startWeek = parseIntSafe(row[7]);
                        int endWeek   = parseIntSafe(row[8]);

                        slots.add(new TimeSlot(dayOfWeek, startTime, endTime,
                                moduleCode, roomCode, classType,
                                startWeek, endWeek));
                    } catch (Exception e) {
                        System.err.println("Skipping invalid row: " + Arrays.toString(row));
                    }
                }
            }
            return slots;
        }
    }