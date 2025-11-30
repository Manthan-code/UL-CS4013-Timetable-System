package project_classes;

import project_io.CSVReader;
import java.util.List;
import java.util.Scanner;

public class LoginPrompt {

    /**
     * Class to handle user logins
     */

    private String usersFilePath;

    public LoginPrompt(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    /**
     * Handles the login logic in the system
     * Notfies user of a valid/invalid login attempt
     * @param scanner The user's inputs (email and password)
     * @return The User who is logged into the system
     */
    public User login(Scanner scanner) {
        int attempts = 0;

        while (attempts < 3) {
            System.out.println("\n--- Login ---");
            System.out.print("Enter email (or 'q' to cancel): ");
            String email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("q")) {
                System.out.println("Login cancelled.");
                return null;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            User user = findUser(email, password);

            if (user != null) {
                System.out.println("Login successful. Logged in as: " + user.getRole());
                return user;
            }

            System.out.println("Invalid email or password.");
            attempts++;
        }

        System.out.println("Too many failed attempts.");
        return null;
    }

    /**
     * Finds the user inside the users.csv file
     * @param email The String email address of the user
     * @param password The string password for thr user's account
     * @return A user who exists in the users.csv file
     */
    private User findUser(String email, String password) {

        List<String[]> rows = CSVReader.readCSV(usersFilePath);

        if (rows.isEmpty()) {
            System.out.println("Error: users CSV not found or empty.");
            return null;
        }

        // Skip header
        for (int i = 1; i < rows.size(); i++) {
            String[] p = rows.get(i);
            if (p.length < 5) continue;

            String csvEmail = p[0].trim();
            String csvPass = p[1].trim();
            String csvRole = p[2].trim();
            String csvExtra = p[3].trim();
            String csvCourse = p[4].trim();

            if (email.equalsIgnoreCase(csvEmail) && password.equals(csvPass)) {
                return new User(csvEmail, csvPass, csvRole, csvExtra, csvCourse);
            }
        }

        return null;
    }
}
