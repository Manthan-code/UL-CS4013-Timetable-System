package project_main;

// Class imports
import project_classes.TimetableManager;
import project_classes.TimetableEntry;
import project_classes.TimeSlot;

// Other imports
import java.util.Scanner;
import java.util.List;
import java.time.LocalTime;

public class CLILauncher {
    /**
     * Main method class which launches + runs the CLI interface for timetable
     * system
     */

    private static TimetableManager manager = new TimetableManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("--- Loading Data ---");

        // Loading CSV file with time slots
        manager.loadCSV("timetable_data.csv");
        System.out.println("Data Loaded.");

        boolean running = true;

        // Loop to keep the program running continuously
        while (running) {
            printMenu();
            // User input
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllTimetables();
                    break;
                case "2":
                    viewLecturerTimetable();
                    break;
                case "3":
                    addNewEntry();
                    break;
                case "4":
                    manager.saveCSV("timetable_data.csv");
                    System.out.println("Data Saved.");
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays CLI menu for the user to select an option
     */

    private static void printMenu() {
        System.out.println("\n=== UL CLI Timetabling System ===");
        System.out.println("1. View All Timetables");
        System.out.println("2. View Specific Lecturer Timetable");
        System.out.println("3. Add New Entry");
        System.out.println("4. Save Changes");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    /**
     * Displays all the timetables made from the CSV file
     */

    private static void viewAllTimetables() {
        manager.printWeeklyGrid();
    }

    /**
     * Displays the timetable for a specific lecturer
     */

    private static void viewLecturerTimetable() {
        System.out.print("Enter Lecturer Name: ");
        String name = scanner.nextLine();
        List<TimetableEntry> results = manager.getLecturerTimetable(name);

        if (results.isEmpty()) {
            System.out.println("No entries found for " + name);
        } else {
            for (TimetableEntry entry : results) {
                System.out.println(entry);
            }
        }
    }

    private static void addNewEntry() {
        System.out.println("\n--- Add New Timetable Entry ---");

        // Get day of week from user
        System.out.print("Enter day of the week (e.g., Mon, Tue): ");
        String day = scanner.nextLine();

        // Get start and end time from user
        System.out.print("Enter start time (HH:mm): ");
        String startStr = scanner.nextLine();

        System.out.print("Enter end time (HH:mm): ");
        String endStr = scanner.nextLine();

        // Get module code from user
        System.out.print("Enter module code (e.g., CS4013): ");
        String module = scanner.nextLine();

        // Get roomCode from user
        System.out.print("Enter room code (e.g., KBG12): ");
        String room = scanner.nextLine();

        // Get class type from user
        System.out.print("Enter class type (Lecture/Lab/Tutorial): ");
        String type = scanner.nextLine();

        // Get lecturer name from user
        System.out.print("Enter lecturer name: ");
        String lecturer = scanner.nextLine();

        System.out.println("Enter the student Group: ");
        String studentGroup = scanner.nextLine();

        // Get start and end week from user
        System.out.print("Enter start week (e.g., 1): ");
        int startWeek = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter end week (e.g., 12): ");
        int endWeek = Integer.parseInt(scanner.nextLine());

        // Create TimetableEntry object
        try {
            LocalTime startTime = LocalTime.parse(startStr);
            LocalTime endTime = LocalTime.parse(endStr);

            TimetableEntry newEntry = new TimetableEntry(
                    day, new TimeSlot(startTime, endTime), module, room, type, lecturer,studentGroup, startWeek, endWeek);

            // Add to Manager (checks for conflicts automatically)
            boolean success = manager.addEntry(newEntry);

            if (success) {
                System.out.println("Success: Entry added to timetable.");
            } else {
                System.out.println("Failed: Could not add entry (Conflict detected).");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid time format or input. " + e.getMessage());
        }
    }
}