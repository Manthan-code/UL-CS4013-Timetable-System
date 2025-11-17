package project_data;
import project_io.CSVReader;
import project_io.CSVWriter;
public class DataManager {
    /**
     * Class that will manage data from CSV files
     */

    private CSVReader reader;
    private CSVWriter writer;

    //constructor sets up the file readers/writers
    public DataManager() {
        this.reader = new CSVReader();
        this.writer = new CSVWriter();
    }

    /**
     * Loads all the data from CSV files in the system
     */
    public void loadAllData() {
        //will load modules.csv, lecturers.csv, rooms.csv, etc.
        //convert into objects
    }

    /**
     * Saves all the data loaded from the CSV files
     */

    public void saveAllData() {
        //will save updated timetables + modules + rooms
    }

}
