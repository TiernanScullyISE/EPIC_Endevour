package main;

import java.io.*;
import java.util.Scanner;

public class PlayerDataWriter {
    // This class is responsible for writing user data (scores) to a CSV file.

    private File csvFile; // File object to represent the user's CSV file

    public PlayerDataWriter(String username) {
        // Constructor - Initializes the UserDataWriter with a username.

        // Create a File object for the user's CSV file
        csvFile = new File(username + ".csv");

        if (!csvFile.exists()) {
            try {
                // If the file doesn't exist, create a new CSV file
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFile.getName());
            } catch (IOException e) {
                // Handle any potential exceptions
                e.printStackTrace();
                System.exit(1); // Terminate the program if an error occurs
            }
        }
    }

    public void writeScore(int score) {
        // Method to write a user's score to the CSV file

        try {
            // Create a FileWriter to write data to the CSV file.
            FileWriter writer = new FileWriter(csvFile, true);

            // Create a BufferedWriter for efficient writing to the FileWriter.
            BufferedWriter bw = new BufferedWriter(writer);

            // Create a PrintWriter, which allows us to write data to the BufferedWriter.
            PrintWriter out = new PrintWriter(bw);

            out.println(score); // Write the 'score' value to the CSV file.

            out.close(); // Close the PrintWriter
            bw.close();  // Close the BufferedWriter
            writer.close(); // Close the FileWriter
        } catch (IOException ex) {
            // Handle any potential exceptions
            ex.printStackTrace();
        }
    }

    public File getCSVFile() {
        // Method to get the CSV file associated with the user
        return csvFile;
    }
}
