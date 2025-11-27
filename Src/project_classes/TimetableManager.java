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

    public void printWeeklyGrid() {

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};

        //this determines the range of the timetable so from 9:00 to 18:00
        LocalTime earliest = LocalTime.of(9, 0);
        LocalTime latest = LocalTime.of(18, 0);

        int cellWidth = 30;
        int cellHeight = 3;  // 3 lines per cell

        //prints header row
        System.out.print(pad("Time", cellWidth));
        for (String d : days)
            System.out.print("| " + pad(d, cellWidth - 2));
        System.out.println();
        System.out.println("-".repeat(cellWidth + days.length * cellWidth));

        //prints rest of the rows
        for (LocalTime time = earliest; time.isBefore(latest); time = time.plusHours(1)) {

            //build text for each cell first
            String[][] cellLines = new String[days.length][cellHeight];
            for (int i = 0; i < days.length; i++) {

                String fullText = getCellValue(days[i], time);
                String[] lines = fullText.split("\n");

                for (int l = 0; l < cellHeight; l++) {
                    if (l < lines.length) {
                        cellLines[i][l] = pad(lines[l], cellWidth - 2);
                    } else {
                        cellLines[i][l] = pad("", cellWidth - 2);
                    }
                }
            }

            for (int line = 0; line < cellHeight; line++) {

                if (line == 0)
                    System.out.print(pad(time.toString(), cellWidth));
                else
                    System.out.print(pad("", cellWidth));

                for (int i = 0; i < days.length; i++) {
                    System.out.print("| " + cellLines[i][line]);
                }

                System.out.println();
            }

            System.out.println("-".repeat(cellWidth + days.length * cellWidth));
        }
    }

    private String getCellValue(String day, LocalTime time) {
        for (TimetableEntry e : entries) {

            if (!e.getDay().equalsIgnoreCase(day))
                continue;

            LocalTime start = e.getTimeSlot().getStartTime();
            LocalTime end   = e.getTimeSlot().getEndTime();

            if (!time.isBefore(start) && time.isBefore(end)) {

                return e.getModuleCode() + " (" + e.getRoomCode() + ")\n" +
                        e.getClassType() + " - " + e.getLecturerName() + "\n" +
                        "W" + e.getStartWeek() + "–" + e.getEndWeek();
            }
        }

        return "";
    }

    private String pad(String text, int width) {
        if (text.length() >= width) return text.substring(0, width - 1);
        return String.format("%-" + width + "s", text);
    }


}
