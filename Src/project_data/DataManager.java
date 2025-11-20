package project_data;

import java.io.*;
import java.util.*;

import project_classes.Lecturer;
import project_classes.Room;
import project_io.CSVReader;


public class DataManager {
    /**
     * Class that will manage data from CSV files
     */

    public DataManager() {

    }

    /**
     * Creates room objects from CSV file
     * @return List of rooms made from CSV file
     */

    public List<Room> loadRooms(String filepath){
        List<Room> rooms = new ArrayList<>();
        List<String[]> rows = CSVReader.readCSV(filepath);

        String roomType;
        int maxCapacity;
        String roomCode;
        double roomHours;

        for(int i = 0; i < rows.size(); i++){
            String[] row = rows.get(i);
            // Getting values for room
            roomType = row[0];
            maxCapacity = Integer.parseInt(row[1]);
            roomCode = row[2];
            roomHours = Double.parseDouble(row[3]);


            Room room = new Room (roomType,maxCapacity,roomCode,roomHours);
            rooms.add(room);
        }

        return rooms;
    }
    /**
     * Saves all the data loaded from the CSV files
     */

    public void saveAllData() {
        //will save updated timetables + modules + rooms
    }

}
