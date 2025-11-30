//CLILauncher

package project_main;

import project_classes.User;
import project_classes.LoginPrompt;
import project_classes.TimeSlot;
import project_classes.TimetableEntry;
import project_classes.TimetableManager;
import project_classes.Room;
import project_data.DataManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// main CLI
public class CLILauncher {
    /**
     * Class that handles the CLI of system
     */

    private static final String USERS_FILE = "users.csv";
    private static final String TIMETABLE_FILE = "timetable_data.csv";
    private static final String MODULES_FILE = "timetableModules.csv";
    private static final String ROOMS_FILE = "timetableRooms.csv";
    private static final String COURSES_FILE = "courses.csv";

    private static TimetableManager manager = new TimetableManager();
    private static LoginPrompt loginPrompt = new LoginPrompt(USERS_FILE);
    private static DataManager dataManager = new DataManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Load all CSV data
        dataManager.loadModules(MODULES_FILE);
        dataManager.loadRooms(ROOMS_FILE);
        dataManager.loadCourses(COURSES_FILE);
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

    /**
     * Verifies the role that the user has
     * @param user The user using the system
     */
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

    /**
     * Loads the admin menu for a successful admin login
     */
    private static void adminMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View Student Timetable");
            System.out.println("2. View Course Timetable (LM121/LM174)");
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

    /**
     * Loads the lecturer menu for a successful lecturer login
     * @param user The lecturer using the system
     */
    private static void lecturerMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== LECTURER MENU ===");
            System.out.println("1. View Your Timetable");
            System.out.println("2. View Module Timetable");
            System.out.println("3. View Course Timetable (LM121/LM174)");
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

    /**
     * Loads the student menu for a successful student login
     * @param user The student using the system
     */
    private static void studentMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View Your Timetable");
            System.out.println("2. View Course Timetable (LM121/LM174)");
            System.out.println("3. View Module Timetable");
            System.out.println("4. View Room Timetable");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // use group + course from logged in user
                    viewStudentTimetable(user);
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

    /**
     * Allows admin user to choose the group and course of a student
     */
    private static void viewStudentTimetablePrompt() {
        System.out.print("Enter student group (e.g. 3A or AI1A): ");
        String group = scanner.nextLine().trim();

        System.out.print("Enter course code (LM121/LM174): ");
        String course = scanner.nextLine().trim().toUpperCase();

        List<String> courseModules = dataManager.getModulesForCourse(course);
        if (courseModules == null || courseModules.isEmpty()) {
            System.out.println("No modules found for course: " + course);
            return;
        }

        List<TimetableEntry> all = manager.getEntries();
        List<TimetableEntry> result = new ArrayList<>();

        for (TimetableEntry e : all) {

            boolean belongsToCourse = courseModules.contains(e.getModuleCode());
            boolean isLecture = e.getClassType().equalsIgnoreCase("LEC");

            if (!belongsToCourse) continue;

            if (isLecture) {
                // show lectures for the full course
                result.add(e);
            } else {
                // show only labs/tuts that match group
                if (e.getStudentGroup() != null &&
                        e.getStudentGroup().equalsIgnoreCase(group)) {
                    result.add(e);
                }
            }
        }

        printWeeklyGrid(result, "Student Timetable (" + group + ", " + course + ")");
    }

    /**
     * Handles prompt for viewing student timetable
     */
    private static void viewStudentTimetable(User user) {

        String group = user.getExtra();     // 3A or AI1A etc.
        String course = user.getCourse();   // LM121 or LM174

        List<String> courseModules = dataManager.getModulesForCourse(course);
        if (courseModules == null || courseModules.isEmpty()) {
            System.out.println("No modules found for your course: " + course);
            return;
        }

        List<TimetableEntry> all = manager.getEntries();
        List<TimetableEntry> result = new ArrayList<>();

        for (TimetableEntry e : all) {

            boolean isLecture = e.getClassType().equalsIgnoreCase("LEC");
            boolean belongsToCourse = courseModules.contains(e.getModuleCode());

            if (!belongsToCourse) continue;

            if (isLecture) {
                // all lectures of that course
                result.add(e);
            } else {
                // labs/tuts for the student's group only
                if (e.getStudentGroup() != null &&
                        e.getStudentGroup().equalsIgnoreCase(group)) {
                    result.add(e);
                }
            }
        }

        printWeeklyGrid(result, "Your Timetable (" + group + " - " + course + ")");
    }

    /**
     * Handles prompt for viewing course timetable
     */
    private static void viewCourseTimetablePrompt() {

        System.out.print("Enter course code (LM121 - BSc. CS, LM174 - BSc. AI/ML): ");
        String courseCode = scanner.nextLine().trim().toUpperCase();

        List<String> courseModules = dataManager.getModulesForCourse(courseCode);

        if (courseModules == null || courseModules.isEmpty()) {
            System.out.println("No modules found for course: " + courseCode);
            System.out.println("Check courses.csv or the code you entered.");
            return;
        }

        List<TimetableEntry> all = manager.getEntries();
        List<TimetableEntry> result = new ArrayList<>();

        for (TimetableEntry e : all) {
            String mod = e.getModuleCode();
            for (String m : courseModules) {
                if (mod.equalsIgnoreCase(m)) {
                    result.add(e);
                    break;
                }
            }
        }

        printWeeklyGrid(result, "Course timetable for " + courseCode);
    }

    /**
     * Handles prompt for viewing module timetable
     */
    private static void viewModuleTimetablePrompt() {
        System.out.print("Enter module code (e.g. CS4013): ");
        String module = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getModuleTimetable(module);
        printWeeklyGrid(list, "Module timetable for " + module);

    }

    /**
     * Handles prompt for viewing lecturer timetable
     */
    private static void viewLecturerTimetablePrompt() {
        System.out.print("Enter lecturer name: ");
        String name = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getLecturerTimetable(name);
        printWeeklyGrid(list, "Lecturer timetable for " + name);
    }

    /**
     * Handles prompt for viewing room timetable
     */
    private static void viewRoomTimetablePrompt() {
        System.out.print("Enter room code (e.g. CS2044): ");
        String room = scanner.nextLine().trim();
        List<TimetableEntry> list = manager.getRoomTimetable(room);
        printWeeklyGrid(list, "Room timetable for " + room);

    }

    /**
     * Handles prompt for viewing all modules in a timetable
     */
    private static void viewAllModules() {
        List<project_classes.Module> modules = dataManager.getModules();
        System.out.println("\n--- All Modules ---");
        if (modules.isEmpty()) {
            System.out.println("No modules loaded.");
        } else {
            for (project_classes.Module m : modules) {
                System.out.println(m);
            }
        }
    }

    /**
     * Handles prompt for viewing all rooms in a timetable
     */
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


    /**
     * Allows admin user to add timetable entries
     */
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

        System.out.print("Enter student group (e.g. 3A, 3B, AI1A): ");
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

    /**
     * Allows admin user to modify existing timetable entries
     */
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

    /**
     * Allows admin user to delete timetable entries
     */
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

    /**
     * Formats/prints the structure of the timetable grid
     * @param list ArrayList of timetable entries
     * @param title String title of the timetable
     */

    private static void printWeeklyGrid(List<TimetableEntry> list, String title) {
        System.out.println("\n=== " + title + " ===");

        if (list == null || list.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        int startHour = 9;
        int endHour = 17;

        //4lines of spacing y-axis in each block
        String[][][] grid = new String[(endHour - startHour)][days.length][4];

        //fill empty space for 4 line gap in between cells
        for (int r = 0; r < grid.length; r++) {
            for (int d = 0; d < days.length; d++) {
                for (int line = 0; line < 4; line++) {
                    grid[r][d][line] = "";
                }
            }
        }

        //Insert timetable entries
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

            String groupText = "";
            //No GRP description for LEC
            if (!e.getClassType().equalsIgnoreCase("LEC") && e.getStudentGroup() != null) {
                groupText = " - " + e.getStudentGroup();
            }

            //cell structure
            String[] content = new String[]{
                    " "+e.getModuleCode() + " - " + e.getClassType() + groupText,
                    "     "+e.getLecturerName(),
                    "      "+e.getRoomCode(),
                    "     "+"Wks:" + e.getStartWeek() + "-" + e.getEndWeek()
            };


            for (int r = rowStart; r < rowEnd; r++) {
                if (r >= 0 && r < grid.length) {
                    grid[r][dayIndex] = content;
                }
            }
        }

        // Print table
        //An actual Grid/Matrix like TimeTable
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

    /**
     * Reads integer values from the user in the CLI.
     * @return Number read from the user on command line
     */

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

    /**
     * Reads integer values from user
     * Default value is outputted if the integer can't be read in.
     * @param text String user input
     * @param defaultValue int default value to be returned
     * @return The int read from user/default int value
     */

    private static int parseIntOrDefault(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, keeping " + defaultValue);
            return defaultValue;
        }
    }
}
