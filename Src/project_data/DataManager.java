package project_data;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import project_classes.*;
import project_classes.Module;
import project_io.CSVReader;
import project_classes.TimetableEntry;
import project_classes.TimeSlot;


public class DataManager {

    // Cache these in memory so we don't read files constantly
    private static Set<Room> validRooms = new HashSet<>();
    private static Set<Module> validModules = new HashSet<>();


        // Method which safely handles errors reading int values
        private static int parseIntSafe(String value) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }


    /**
     * Loads the reference data (Rooms and Modules)
     */
    public static void loadReferenceData() {
        validRooms = loadRooms("rooms.csv");
        validModules = loadModules("modules.csv");
        System.out.println("Reference data loaded: " + validRooms.size() + " rooms, " + validModules.size() + " modules.");
    }

    /**
     * Returns the list of rooms read from CSV file
     * @param filepath String file path of CSV file
     * @return List of rooms from CSV file (without duplicates)
     */

    public static Set<Room> loadRooms(String filepath) {
            // Getting rows from CSV file
            List<String[]> rows = CSVReader.readCSV(filepath);

            // Looping through rows
            for (String[] row : rows) {

                if (row.length >= 4) {
                    String roomType = row[0].trim();
                    int maxCapacity = Integer.parseInt(row[1].trim());
                    String roomCode = row[2].trim();
                    validRooms.add(new Room(roomType,maxCapacity,roomCode));
                }
            }
            return validRooms;
        }

    /**
     * Returns the list of modules read from CSV file
     * @param filepath String file path of CSV file
     * @return List of modules from CSV file (without duplicates)
     */

        public static Set<Module> loadModules(String filepath) {
            List<String[]> rows = CSVReader.readCSV(filepath);

            for (String[] row : rows) {
                if (row.length >= 2) {
                    String moduleCode = row[0].trim();
                    String lecturer = row[1].trim();
                    validModules.add(new Module(moduleCode, lecturer));
                }
            }
            return validModules;
        }

    /**
     * Gets list of timeslots from CSV File
     * @param filepath String file path of CSV file
     * @return List of Timeslots from CSV file
     */

    public static List<TimetableEntry> loadTimeSlots(String filepath) {

            List<TimetableEntry> entries = new ArrayList<>();
            List<String[]> rows = CSVReader.readCSV(filepath);

            for (String[] row : rows) {
                if (row.length > 8) {
                    try {
                        String dayOfWeek = row[0].trim();
                        LocalTime startTime = LocalTime.parse(row[1]);
                        LocalTime endTime = LocalTime.parse(row[2]);
                        String moduleCode= row[3].trim();
                        String roomCode  = row[4].trim();
                        String classType = row[5].trim();
                        String lecturer = row[6].trim();
                        String studentGroup = row[7].trim();

                        // Parse integers safely
                        int startWeek = parseIntSafe(row[8]);
                        int endWeek   = parseIntSafe(row[9]);

                        entries.add(new TimetableEntry(dayOfWeek, new TimeSlot(startTime,endTime),
                                moduleCode, roomCode, classType, lecturer,studentGroup,startWeek, endWeek));
                    } catch (Exception e) {
                        System.err.println("Skipping invalid row: " + Arrays.toString(row));
                    }
                }
            }
            return entries;
        }
    }