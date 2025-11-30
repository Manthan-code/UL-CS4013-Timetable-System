//TimetableManager

package project_classes;

import project_io.CSVReader;
import project_io.CSVWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//handles saves, reads, modifying the timetable entries
public class  TimetableManager {
    /**
     * Class that saves, reads and handles the timetable entries
     */

    private List<TimetableEntry> entries = new ArrayList<>();

    /** Gets a list of the timetable entries*/
    public List<TimetableEntry> getEntries() {
        return entries;
    }

    /**
     * Loads the CSV file with the timetable data.
     * @param filePath String name of file path
     */
    public void loadCSV(String filePath) {
        entries.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: timetable file empty or missing.");
            return;
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] p = rows.get(i);
            if (p.length < 10) continue;

            try {
                String day = p[0].trim();
                LocalTime start = LocalTime.parse(p[1].trim());
                LocalTime end = LocalTime.parse(p[2].trim());
                String module = p[3].trim();
                String room = p[4].trim();
                String classType = p[5].trim();
                String lecturer = p[6].trim();
                String group = p[7].trim();
                int startWeek = Integer.parseInt(p[8].trim());
                int endWeek = Integer.parseInt(p[9].trim());

                TimetableEntry entry = new TimetableEntry(
                        day,
                        new TimeSlot(start, end),
                        module,
                        room,
                        classType,
                        lecturer,
                        group,
                        startWeek,
                        endWeek
                );
                entries.add(entry);
            } catch (Exception e) {
                System.out.println("Error loading row " + i + ": " + e.getMessage());
            }
        }
    }

    /**
     * Saves the data read in from the timetable data CSV file
     * @param filePath String name of the file path
     */
    public void saveCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{
                "Day","StartTime","EndTime","ModuleCode","RoomCode",
                "ClassType","LecturerName","StudentGroup","StartWeek","EndWeek"
        });

        for (TimetableEntry e : entries) {
            rows.add(new String[]{
                    e.getDay(),
                    e.getTimeSlot().getStartTime().toString(),
                    e.getTimeSlot().getEndTime().toString(),
                    e.getModuleCode(),
                    e.getRoomCode(),
                    e.getClassType(),
                    e.getLecturerName(),
                    e.getStudentGroup(),
                    String.valueOf(e.getStartWeek()),
                    String.valueOf(e.getEndWeek())
            });
        }
        CSVWriter.writeCSV(filePath, rows);
        System.out.println("Timetable saved.");
    }

    /**
     * Adds an entry to the list of timetable entries
     * @param newEntry The new timetable entry to be added
     * @return True if addition of entry was successful and vice versa
     */
    public boolean addEntry(TimetableEntry newEntry) {
        for (TimetableEntry existing : entries) {
            if (existing.timeConflictsWith(newEntry)) {
                System.out.println("Conflict with existing entry: " + existing);
                return false;
            }
        }
        entries.add(newEntry);
        return true;
    }

    /**
     * Removes an entry from the list of timetable entries
     * @param index The position of the entry to be removed in the list
     * @return True if the index is valid and the entry is removed and vice versa
     */
    public boolean removeEntry(int index) {
        if (index < 0 || index >= entries.size()) {
            return false;
        }
        entries.remove(index);
        return true;
    }

    /**
     * Prints each timetable entry in the list of entries
     */
    public void printAll() {
        System.out.println("\n--- Complete Timetable ---");

        for (int i = 0; i < entries.size(); i++) {
            System.out.println(i + ": " + entries.get(i));
        }
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        }
    }

    /**
     * Gets the timetable for a specified module
     * @param moduleCode The String module code for the module. E.g. "CS4178"
     * @return The list of entries that contains the module code.
     */
    public List<TimetableEntry> getModuleTimetable(String moduleCode) {
        List<TimetableEntry> result = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getModuleCode().equalsIgnoreCase(moduleCode)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Gets the timetable for a specified lecturer
     * @param lecturerName The String name of the lecturer
     * @return The list of entries that contains the lecturerName.
     */
    public List<TimetableEntry> getLecturerTimetable(String lecturerName) {
        List<TimetableEntry> result = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getLecturerName().equalsIgnoreCase(lecturerName)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Gets the timetable for a specified room
     * @param roomCode The String room code for the room. E.g. "CS2044"
     * @return The list of entries that contains the room code.
     */
    public List<TimetableEntry> getRoomTimetable(String roomCode) {
        List<TimetableEntry> result = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getRoomCode().equalsIgnoreCase(roomCode)) {
                result.add(e);
            }
        }
        return result;
    }
}
