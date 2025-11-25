package project_io;

import project_classes.Lecturer;
import project_classes.Room;
import project_classes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    /**
     * Rads in rooms from the CSV File
     * @param filePath Name of the CSV file
     * @return List of the rooms from CSV File
     */

    public List<Room> loadRooms(String filePath) {

        List<Room> rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skipping header
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }

                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length >= 3) {
                    String id = values[0].trim();
                    String type = values[1].trim();
                    int capacity = Integer.parseInt(values[2].trim());

                    rooms.add(new Room(id, type, capacity));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading rooms from " + filePath + ": " + e.getMessage());
        }
        return rooms;
    }

    // --- 2. Load Modules ---
    // Expected CSV Format: Code, LectureHours, TutorialHours, LabHours
    // Example: CS4013, 2, 1, 2
    public List<Module> loadModules(String filePath) {
        List<Module> modules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length >= 4) {
                    String code = values[0].trim();
                    int lec = Integer.parseInt(values[1].trim());
                    int tut = Integer.parseInt(values[2].trim());
                    int lab = Integer.parseInt(values[3].trim());

                    modules.add(new Module(code, lec, tut, lab));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading modules from " + filePath + ": " + e.getMessage());
        }
        return modules;
    }

    // --- 3. Load Students ---
    // Expected CSV Format: ID, Name, GroupID
    // Example: 123456, John Doe, LM121-Y1
    public List<Student> loadStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length >= 3) {
                    String id = values[0].trim();
                    String name = values[1].trim();
                    String groupId = values[2].trim();

                    students.add(new Student(id, name, groupId));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students from " + filePath + ": " + e.getMessage());
        }
        return students;
    }

    // --- 4. Load Lecturers ---
    // Expected CSV Format: ID, Name
    // Example: 998877, Michael English
    public List<Lecturer> loadLecturers(String filePath) {
        List<Lecturer> lecturers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length >= 2) {
                    String id = values[0].trim();
                    String name = values[1].trim();

                    lecturers.add(new Lecturer(id, name));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading lecturers from " + filePath + ": " + e.getMessage());
        }
        return lecturers;
    }
}
