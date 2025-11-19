package project_data;

import java.io.*;
import java.util.*;

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

    public List<Room> loadModules(String filepath){
        List<Room> rooms = new ArrayList<>();
        List<String[]> rows = CSVReader.readCSV("data/modules.csv");

        for(int i = 0; i < rows.size(); i++){
            String[] row = rows.get(i);

            Room room = new Room (
                    row[1], // roomType
                    Integer.parseInt(row[2]),// room capacity
                    row[3] ,// room code
                    Double.parseDouble(row[4]) // room hours
            );
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
