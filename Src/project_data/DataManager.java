package project_data;
import project_io.CSVReader;
import project_io.CSVWriter;
public class DataManager {

    private CSVReader reader;
    private CSVWriter writer;

    //constructor sets up the file readers/writers
    public DataManager() {
        this.reader = new CSVReader();
        this.writer = new CSVWriter();
    }

    //placeholder methods
    public void loadAllData() {
        //will load modules.csv, lecturers.csv, rooms.csv, etc.
        //convert into objects
    }

    public void saveAllData() {
        //will save updated timetables + modules + rooms
    }
}
