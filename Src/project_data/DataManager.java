package project_data;

import java.util.List;
import java.util.ArrayList;
import project_classes.*;
import project_classes.Module;
import project_io.CSVReader;


public class DataManager {

    private List<Room> rooms;
    private List<Module> modules;
    private List<User> users; // Polymorphism: Holds both Students and Lecturers
    private List<TimeSlot> timetableSlots;

    public DataManager() {
        this.rooms = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.users = new ArrayList<>();
        this.timetableSlots = new ArrayList<>();
    }

    public void loadAllData() {
        CSVReader reader = new CSVReader();

        System.out.println("Loading Rooms.........");
        reader.loadRooms();
    }


}
