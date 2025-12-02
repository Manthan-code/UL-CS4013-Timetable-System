//CSVWriter

package project_io;

import java.io.*;
import java.util.List;

public class CSVWriter {
    /**
     * Class that handles the writing data to a CSV file
     * @param filePath String name of file path
     * @param rows Array of rows from a CSV file
     */
    public static void writeCSV(String filePath, List<String[]> rows) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            for (String[] row : rows) {
                pw.println(String.join(",", row));
            }
        } catch (IOException e) {
            System.out.println("Error Occurred Could not write CSV file: " + filePath);
            System.out.println(e.getMessage());
        }
    }
}
