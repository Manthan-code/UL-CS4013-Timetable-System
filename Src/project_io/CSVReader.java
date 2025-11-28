package project_io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1); // keep empty columns
                rows.add(parts);
            }

        } catch (IOException e) {
            System.out.println("Error: Could not read CSV file: " + filePath);
            System.out.println(e.getMessage());
        }

        return rows;
    }
}
