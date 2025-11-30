//CSVReader

package project_io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    /**
     * Class that handles CSV file reading via parsing
     */


    /**
     * Reads the rwos fo data from a CSV file
     * @param filePath String name of file path
     * @return List of rows from the CSV file
     */
    public static List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                rows.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error occurred Could not read CSV file: " + filePath);
            System.out.println(e.getMessage());
        }
        return rows;
    }
}
