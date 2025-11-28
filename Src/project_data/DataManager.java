package project_data;

import project_classes.Module;
import project_classes.Room;
import project_io.CSVReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads Room and Module information from CSV files.
 * Uses CSVReader so no repeated file I/O code.
 */
public class DataManager {

    private List<Module> modules = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    public List<Module> getModules() {
        return modules;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    // ------------------------------------------------------------
    // LOAD MODULES CSV
    // ------------------------------------------------------------
    public void loadModules(String filePath) {

        modules.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: Module file empty or missing: " + filePath);
            return;
        }

        for (int i = 1; i < rows.size(); i++) { // skip header
            String[] p = rows.get(i);
            if (p.length < 5) continue;

            try {
                String code = p[0].trim();
                String name = p[1].trim();
                int lec = Integer.parseInt(p[2].trim());
                int lab = Integer.parseInt(p[3].trim());
                int tut = Integer.parseInt(p[4].trim());

                modules.add(new Module(code, name, lec, lab, tut));

            } catch (Exception e) {
                System.out.println("Bad module entry at row " + i);
            }
        }

        System.out.println("Loaded " + modules.size() + " modules.");
    }

    // ------------------------------------------------------------
    // LOAD ROOMS CSV
    // ------------------------------------------------------------
    public void loadRooms(String filePath) {

        rooms.clear();
        List<String[]> rows = CSVReader.readCSV(filePath);

        if (rows.isEmpty()) {
            System.out.println("Warning: Room file empty or missing: " + filePath);
            return;
        }

        for (int i = 1; i < rows.size(); i++) { // skip header
            String[] p = rows.get(i);
            if (p.length < 4) continue;

            try {
                String type = p[0].trim();
                int capacity = Integer.parseInt(p[1].trim());
                String code = p[2].trim();
                double hours = Double.parseDouble(p[3].trim());

                rooms.add(new Room(type, capacity, code, hours));

            } catch (Exception e) {
                System.out.println("Bad room entry at row " + i);
            }
        }

        System.out.println("Loaded " + rooms.size() + " rooms.");
    }
}
