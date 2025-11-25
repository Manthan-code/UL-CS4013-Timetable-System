package project_io;


import java.util.*;
import java.io.*;


public class CSVReader {
    private Scanner input;
    /**
     * Class to read data from a CSV file
     */

    /**
     * Constructor to make CSVWriter object
     */

    public CSVReader() {
        super();
    }

    /**
     * Makes an input file from a file name
     * @param filename String name of the file
     * @throws FileNotFoundException Thrown when the file isn't found
     */
    public CSVReader(String filename) throws FileNotFoundException {
        try {
            input = new Scanner(new File(filename));
        }  catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found.");
        }
    }

    /**
     * Reads a line fron a CSV file and splits it at ','.
     * @return An array of Strings with the tokens.
     */
    public String[] getValues() {
        if (input.hasNextLine()) {
            String Line = input.nextLine();
            return Line.split(",");
        } else {
            return new String[0]; // Return an empty array if no more Lines

        }
    }

    /**
     * Reads the CSV file line by line and places the lines in a list
     * @param filePath The file path of the CSV file
     * @return ArrayList of lines in the CSV file.
     */

    public static List<String[]> readCSV(String filePath) {
        // List of rows in the CSV file
        List<String[]> rows = new ArrayList<>();

        try {

            // Setup for reading lines in the CSV file
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            // Reading lines in the CSV files with "," seperator for data in rows
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                rows.add(fields);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return rows;
    }
}