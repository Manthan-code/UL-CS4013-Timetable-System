package project_main;

import project_classes.User;
import project_classes.LoginPrompt;
import project_classes.TimeSlot;
import project_classes.TimetableEntry;
import project_classes.TimetableManager;
import project_classes.Module;
import project_classes.Room;

import project_data.DataManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Main command-line interface for the timetable system.
 */
public class CLILauncher {

    private static final String USERS_FILE = "users.csv";
    private static final String TIMETABLE_FILE = "timetable_data.csv";
    private static final String MODULES_FILE = "timetableModules.csv";
    private static final String ROOMS_FILE = "timetableRooms.csv";

    private static TimetableManager manager = new TimetableManager();
    private static LoginPrompt loginPrompt = new LoginPrompt(USERS_FILE);
    private static DataManager dataManager = new DataManager();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Load all CSV data
        dataManager.loadModules(MODULES_FILE);
        dataManager.loadRooms(ROOMS_FILE);
        manager.loadCSV(TIMETABLE_FILE);

        boolean running = true;

        while (running) {
            System.out.println("\n=== TIMETABLE SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    User user = loginPrompt.login(scanner);
                    if (user != null) {
                        handleUserSession(user);
                    }
                    break;

                case "2":
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // ------------------------------------------------------------
    // ROLE HANDLING
    // ------------------------------------------------------------

    private static void handleUserSession(User user) {
        String role = user.getRole().toLowerCase();

        if (role.equals("admin")) {
            adminMenu();
        } else if (role.equals("lecturer")) {
            lecturerMenu(user);
        } else if (role.equals("student")) {
            studentMenu(user);
        } else {
            System.out.println("Unknown role: " + role);
        }
    }

    // ------------------------------------------------------------
    // ADMIN MENU
    // ------------------------------------------------------------

    private static void adminMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View Student Timetable");
            System.out.println("2. View Course Timetable (by group)");
            System.out.println("3. View Module Timetable");
            System.out.println("4. View Lecturer Timetable");
            System.out.println("5. View Room Timetable");
            System.out.println("6. View All Modules");
            System.out.println("7. View All Rooms");
            System.out.println("8. Add Timetable Entry");
            System.out.println("9. Edit Timetable Entry");
            System.out.println("10. Delete Timetable Entry");
            System.out.println("11. Save Changes");
            System.out.println("12. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewStudentTimetablePrompt();
                    break;
                case "2":
                    viewCourseTimetablePrompt();
                    break;
                case "3":
                    viewModuleTimetablePrompt();
                    break;
                case "4":
                    viewLecturerTimetablePrompt();
                    break;
                case "5":
                    viewRoomTimetablePrompt();
                    break;
                case "6":
                    viewAllModules();
                    break;
                case "7":
                    viewAllRooms();
                    break;
                case "8":
                    addNewEntry();
                    break;
                case "9":
                    editEntry();
                    break;
                case "10":
                    deleteEntry();
                    break;
                case "11":
                    manager.saveCSV(TIMETABLE_FILE);
                    break;
                case "12":
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------------------------------------------------
    // LECTURER MENU
    // ------------------------------------------------------------

    private static void lecturerMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== LECTURER MENU ===");
            System.out.println("1. View Your Timetable");
            System.out.println("2. View Module Timetable");
            System.out.println("3. View Course Timetable (by group)");
            System.out.println("4. View Room Timetable");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    List<TimetableEntry> lecList = manager.getLecturerTimetable(user.getExtra());
                    printWeeklyGrid(lecList, "Your Timetable (" + user.getExtra() + ")");

                    break;
                case "2":
                    viewModuleTimetablePrompt();
                    break;
                case "3":
                    viewCourseTimetablePrompt();
                    break;
                case "4":
                    viewRoomTimetablePrompt();
                    break;
                case "5":
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------------------------------------------------
    // STUDENT MENU
    // ------------------------------------------------------------

    private static void studentMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View Your Timetable");
            System.out.println("2. View Course Timetable (by group)");
            System.out.println("3. View Module Timetable");
            System.out.println("4. View Room Timetable");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewStudentTimetable(user.getExtra());
                    break;
                case "2":
                    viewCourseTimetablePrompt();
                    break;
                case "3":
                    viewModuleTimetablePrompt();
                    break;
                case "4":
                    viewRoomTimetablePrompt();
                    break;
                case "5":
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------------------------------------------------
    // VIEW HELPERS
    // ------------------------------------------------------------

    private static void viewStudentTimetablePrompt() {
        System.out.print("Enter student group (e.g. CS1A): ");
        String group = scanner.nextLine().trim();
        viewStudentTimetable(group);
    }

    private static void viewStudentTimetable(String group) {
        List<TimetableEntry> all = manager.getEntries();
        List<TimetableEntry> result = new ArrayList<>();

        for (TimetableEntry e : all) {
            if (e.getStudentGroup() != null &&
                    e.getStudentGroup().equalsIgnoreCase(group)) {
                result.add(e);
            }
        }

        printWeeklyGrid(result, "Student timetable for group " + group);

    }

    private static void viewCourseTimetablePrompt() {
        System.out.print("Enter course / group code (e.g. CS1A): ");
        String group = scanner.nextLine().trim();
        viewStudentTimetable(group);
    }

    private static void viewModuleTimetablePrompt() {
        System.out.print("Enter module code (e.g. CS4013): ");
        String module = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getModuleTimetable(module);
        printWeeklyGrid(list, "Module timetable for " + module);

    }

    private static void viewLecturerTimetablePrompt() {
        System.out.print("Enter lecturer name: ");
        String name = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getLecturerTimetable(name);
        printWeeklyGrid(list, "Lecturer timetable for " + name);
    }

    private static void viewRoomTimetablePrompt() {
        System.out.print("Enter room code (e.g. CS2044): ");
        String room = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getRoomTimetable(room);
        printWeeklyGrid(list, "Room timetable for " + room);

    }

    private static void viewAllModules() {
        List<Module> modules = dataManager.getModules();
        System.out.println("\n--- All Modules ---");
        if (modules.isEmpty()) {
            System.out.println("No modules loaded.");
        } else {
            for (Module m : modules) {
                System.out.println(m);
            }
        }
    }

    private static void viewAllRooms() {
        List<Room> rooms = dataManager.getRooms();
        System.out.println("\n--- All Rooms ---");
        if (rooms.isEmpty()) {
            System.out.println("No rooms loaded.");
        } else {
            for (Room r : rooms) {
                System.out.println(r);
            }
        }
    }

    private static void printEntries(List<TimetableEntry> list, String title) {
        System.out.println("\n--- " + title + " ---");
        if (list == null || list.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (TimetableEntry e : list) {
                System.out.println(e);
            }
        }
    }

    // ------------------------------------------------------------
    // ADMIN: ADD / EDIT / DELETE
    // ------------------------------------------------------------

    private static void addNewEntry() {
        System.out.println("\n--- Add New Timetable Entry ---");

        System.out.print("Enter day of week (e.g. Monday): ");
        String day = scanner.nextLine().trim();

        System.out.print("Enter start time (HH:mm): ");
        String startStr = scanner.nextLine().trim();

        System.out.print("Enter end time (HH:mm): ");
        String endStr = scanner.nextLine().trim();

        System.out.print("Enter module code: ");
        String module = scanner.nextLine().trim();

        System.out.print("Enter room code: ");
        String room = scanner.nextLine().trim();

        System.out.print("Enter class type (LEC/LAB/TUT): ");
        String classType = scanner.nextLine().trim();

        System.out.print("Enter lecturer name: ");
        String lecturer = scanner.nextLine().trim();

        System.out.print("Enter student group (e.g. CS1A): ");
        String group = scanner.nextLine().trim();

        System.out.print("Enter start week: ");
        int startWeek = readIntFromUser();

        System.out.print("Enter end week: ");
        int endWeek = readIntFromUser();

        try {
            LocalTime startTime = LocalTime.parse(startStr);
            LocalTime endTime = LocalTime.parse(endStr);

            TimetableEntry newEntry = new TimetableEntry(
                    day,
                    new TimeSlot(startTime, endTime),
                    module,
                    room,
                    classType,
                    lecturer,
                    group,
                    startWeek,
                    endWeek
            );

            boolean ok = manager.addEntry(newEntry);
            if (ok) {
                System.out.println("Entry added successfully.");
            } else {
                System.out.println("Could not add entry (conflict detected).");
            }

        } catch (Exception e) {
            System.out.println("Error: invalid time format.");
        }
    }

    private static void editEntry() {
        System.out.println("\n--- Edit Timetable Entry ---");
        manager.printAll();

        System.out.print("Enter index of entry to edit: ");
        int index = readIntFromUser();

        List<TimetableEntry> all = manager.getEntries();
        if (index < 0 || index >= all.size()) {
            System.out.println("Invalid index.");
            return;
        }

        TimetableEntry oldEntry = all.get(index);
        System.out.println("Editing: " + oldEntry);

        System.out.print("New day (blank to keep " + oldEntry.getDay() + "): ");
        String day = scanner.nextLine().trim();
        if (day.isEmpty()) day = oldEntry.getDay();

        System.out.print("New start time (blank to keep "
                + oldEntry.getTimeSlot().getStartTime() + "): ");
        String startStr = scanner.nextLine().trim();
        if (startStr.isEmpty()) startStr = oldEntry.getTimeSlot().getStartTime().toString();

        System.out.print("New end time (blank to keep "
                + oldEntry.getTimeSlot().getEndTime() + "): ");
        String endStr = scanner.nextLine().trim();
        if (endStr.isEmpty()) endStr = oldEntry.getTimeSlot().getEndTime().toString();

        System.out.print("New module code (blank to keep "
                + oldEntry.getModuleCode() + "): ");
        String module = scanner.nextLine().trim();
        if (module.isEmpty()) module = oldEntry.getModuleCode();

        System.out.print("New room code (blank to keep "
                + oldEntry.getRoomCode() + "): ");
        String room = scanner.nextLine().trim();
        if (room.isEmpty()) room = oldEntry.getRoomCode();

        System.out.print("New class type (blank to keep "
                + oldEntry.getClassType() + "): ");
        String classType = scanner.nextLine().trim();
        if (classType.isEmpty()) classType = oldEntry.getClassType();

        System.out.print("New lecturer name (blank to keep "
                + oldEntry.getLecturerName() + "): ");
        String lecturer = scanner.nextLine().trim();
        if (lecturer.isEmpty()) lecturer = oldEntry.getLecturerName();

        System.out.print("New student group (blank to keep "
                + oldEntry.getStudentGroup() + "): ");
        String group = scanner.nextLine().trim();
        if (group.isEmpty()) group = oldEntry.getStudentGroup();

        System.out.print("New start week (blank to keep "
                + oldEntry.getStartWeek() + "): ");
        String startWeekStr = scanner.nextLine().trim();
        int startWeek = startWeekStr.isEmpty()
                ? oldEntry.getStartWeek()
                : parseIntOrDefault(startWeekStr, oldEntry.getStartWeek());

        System.out.print("New end week (blank to keep "
                + oldEntry.getEndWeek() + "): ");
        String endWeekStr = scanner.nextLine().trim();
        int endWeek = endWeekStr.isEmpty()
                ? oldEntry.getEndWeek()
                : parseIntOrDefault(endWeekStr, oldEntry.getEndWeek());

        try {
            LocalTime startTime = LocalTime.parse(startStr);
            LocalTime endTime = LocalTime.parse(endStr);

            TimetableEntry newEntry = new TimetableEntry(
                    day,
                    new TimeSlot(startTime, endTime),
                    module,
                    room,
                    classType,
                    lecturer,
                    group,
                    startWeek,
                    endWeek
            );

            // Try replacing
            manager.removeEntry(index);
            boolean ok = manager.addEntry(newEntry);

            if (ok) {
                System.out.println("Entry updated successfully.");
            } else {
                System.out.println("Update failed (conflict). Reverting.");
                manager.addEntry(oldEntry);
            }

        } catch (Exception e) {
            System.out.println("Error: invalid time format.");
            manager.addEntry(oldEntry); // ensure not lost
        }
    }

    private static void deleteEntry() {
        System.out.println("\n--- Delete Timetable Entry ---");
        manager.printAll();

        System.out.print("Enter index of entry to delete: ");
        int index = readIntFromUser();

        boolean removed = manager.removeEntry(index);
        if (removed) {
            System.out.println("Entry deleted.");
        } else {
            System.out.println("Invalid index. Nothing deleted.");
        }
    }

    private static void printWeeklyGrid(List<TimetableEntry> list, String title) {

        System.out.println("\n=== " + title + " ===");

        if (list == null || list.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        int startHour = 9;
        int endHour = 17;

        // grid[row][day] = array of 4 lines
        String[][][] grid = new String[(endHour - startHour)][days.length][4];

        // Fill all cells with empty 4-line blocks
        for (int r = 0; r < grid.length; r++) {
            for (int d = 0; d < days.length; d++) {
                for (int line = 0; line < 4; line++) {
                    grid[r][d][line] = "";
                }
            }
        }

        // Insert timetable entries
        for (TimetableEntry e : list) {

            int sh = e.getTimeSlot().getStartTime().getHour();
            int eh = e.getTimeSlot().getEndTime().getHour();
            int rowStart = sh - startHour;
            int rowEnd = eh - startHour;

            int dayIndex = -1;
            for (int d = 0; d < days.length; d++) {
                if (e.getDay().equalsIgnoreCase(days[d])) {
                    dayIndex = d;
                    break;
                }
            }
            if (dayIndex == -1) continue;

            String[] content = new String[]{
                    e.getModuleCode() + " (" + e.getRoomCode() + ")",
                    e.getClassType() + " - " + e.getLecturerName(),
                    "W" + e.getStartWeek() + "-" + e.getEndWeek(),
                    ""
            };

            for (int r = rowStart; r < rowEnd; r++) {
                if (r >= 0 && r < grid.length) {
                    grid[r][dayIndex] = content;
                }
            }
        }

        // Print table
        String line =
                "+---------+--------------------+--------------------+--------------------+--------------------+--------------------+";

        System.out.println(line);
        System.out.printf("| %-7s | %-18s | %-18s | %-18s | %-18s | %-18s |\n",
                "Time", "Mon", "Tue", "Wed", "Thu", "Fri");
        System.out.println(line);

        for (int r = 0; r < grid.length; r++) {
            int hour = startHour + r;

            // print each of the 4 lines for the row
            for (int sub = 0; sub < 4; sub++) {

                if (sub == 0) {
                    System.out.printf("| %02d:00   |", hour);
                } else {
                    System.out.print("|         |");
                }

                for (int d = 0; d < days.length; d++) {
                    String cell = grid[r][d][sub];
                    if (cell == null) cell = "";
                    System.out.printf(" %-18s |", cell);
                }
                System.out.println();
            }
            System.out.println(line);
        }
    }


    // ------------------------------------------------------------
    // SMALL INPUT HELPERS
    // ------------------------------------------------------------

    private static int readIntFromUser() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static int parseIntOrDefault(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, keeping " + defaultValue);
            return defaultValue;
        }
    }
}
