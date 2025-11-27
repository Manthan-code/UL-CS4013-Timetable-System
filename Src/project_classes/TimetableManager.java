package project_classes;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimetableManager {

    private ArrayList<TimetableEntry> entries = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public List<TimetableEntry> getEntries() {
        return entries;
    }

    // ----------------------------
    // Add with basic conflict check
    // ----------------------------
    public boolean addEntry(TimetableEntry entry) {

        for (TimetableEntry e : entries) {
            if (entry.timeConflictsWith(e)) {

                // Room conflict
                if (entry.getRoomCode().equalsIgnoreCase(e.getRoomCode())) {
                    System.out.println("Room conflict with: " + e);
                    return false;
                }

                // Lecturer conflict
                if (entry.getLecturerName().equalsIgnoreCase(e.getLecturerName())) {
                    System.out.println("Lecturer conflict with: " + e);
                    return false;
                }

                // Module conflict
                if (entry.getModuleCode().equalsIgnoreCase(e.getModuleCode())) {
                    System.out.println("Module conflict with: " + e);
                    return false;
                }
            }
        }

        entries.add(entry);
        return true;
    }

    // ----------------------------
    // Remove by index
    // ----------------------------
    public boolean removeEntry(int index) {
        if (index < 0 || index >= entries.size()) return false;
        entries.remove(index);
        return true;
    }

    // ----------------------------
    // List all entries
    // ----------------------------
    public void printAll() {
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(i + ": " + entries.get(i));
        }
    }

    // ----------------------------
    // Search
    // ----------------------------
    public List<TimetableEntry> getLecturerTimetable(String name) {
        ArrayList<TimetableEntry> list = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getLecturerName().equalsIgnoreCase(name)) {
                list.add(e);
            }
        }
        return list;
    }

    public List<TimetableEntry> getModuleTimetable(String module) {
        ArrayList<TimetableEntry> list = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getModuleCode().equalsIgnoreCase(module)) {
                list.add(e);
            }
        }
        return list;
    }

    public List<TimetableEntry> getRoomTimetable(String room) {
        ArrayList<TimetableEntry> list = new ArrayList<>();
        for (TimetableEntry e : entries) {
            if (e.getRoomCode().equalsIgnoreCase(room)) {
                list.add(e);
            }
        }
        return list;
    }

    // ----------------------------
    // Load CSV
    // ----------------------------
    public void loadCSV(String path) {

        entries.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                String day = p[0];
                LocalTime start = LocalTime.parse(p[1], formatter);
                LocalTime end = LocalTime.parse(p[2], formatter);
                String module = p[3];
                String room = p[4];
                String type = p[5];
                String lecturer = p[6];
                String studentGroup = p[7];
                int startW = Integer.parseInt(p[8]);
                int endW = Integer.parseInt(p[9]);

                TimeSlot slot = new TimeSlot(start, end);
                TimetableEntry entry = new TimetableEntry(day, slot, module, room, type, lecturer,studentGroup, startW, endW);

                entries.add(entry);
            }

        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }

    // ----------------------------
    // Save CSV
    // ----------------------------
    public void saveCSV(String path) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {

            pw.println("Day,StartTime,EndTime,ModuleCode,RoomCode,ClassType,LecturerName,StartWeek,EndWeek");

            for (TimetableEntry e : entries) {
                pw.println(
                        e.getDay() + "," +
                                e.getTimeSlot().getStartTime() + "," +
                                e.getTimeSlot().getEndTime() + "," +
                                e.getModuleCode() + "," +
                                e.getRoomCode() + "," +
                                e.getClassType() + "," +
                                e.getLecturerName() + "," +
                                e.getStartWeek() + "," +
                                e.getEndWeek()
                );
            }

        } catch (Exception e) {
            System.out.println("Error saving CSV: " + e.getMessage());
        }
    }
}
