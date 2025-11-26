package project_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    /**
     * Reads CSV file and returns rows via String arrays
     * @param filepath The path for CSV file
     * @return List where each entry is a String[] representing a row.
     */
    public static List<String[]> readCSV(String filepath) {
        List<String[]> data = new ArrayList<>();

        // Trying to read each orw of CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            // Reading every line
            while ((line = br.readLine()) != null) {

                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Splitting by comma
                String[] values = line.split(",");

                // Trimming whitespace or every value
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                data.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file '" + filepath + "': " + e.getMessage());
        }

        return data;
    }
}



