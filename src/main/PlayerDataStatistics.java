package main;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDataStatistics {
    // This class calculates statistics (mean and standard deviation) of user scores.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a username
        System.out.println("Enter username: ");
        String user = scanner.next();

        // Create an instance of UserDataWriter to write scores to a CSV file
        PlayerDataWriter userDataWriter = new PlayerDataWriter(user);

        // Ask the user to input scores
        System.out.println("Enter score: ");
        String scoreInput = scanner.next();
        String[] scoreStrings = scoreInput.split(" ");

        for (String scoreString : scoreStrings) {
            try {
                // Convert the scoreString to an integer
                int score = Integer.parseInt(scoreString);

                // Write the score to the CSV file
                userDataWriter.writeScore(score);
            } catch (NumberFormatException e) {
                // Handle the case when the input is not a valid integer
                System.err.println("Invalid score: " + scoreString);
            }
        }

        // Retrieve the CSV file associated with the user
        File csvFile = userDataWriter.getCSVFile();

        // Calculate and print statistics
        calculateAndPrintStatistics(csvFile);
    }

    private static void calculateAndPrintStatistics(File csvFile) {
        // Method to calculate and print statistics from a CSV file

        List<Integer> scores = new ArrayList<>(); // List to store user scores

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Convert the line to an integer (score) and add it to the list
                int score = Integer.parseInt(line);
                scores.add(score);
            }
        } catch (IOException | NumberFormatException ex) {
            // Handle potential exceptions
            ex.printStackTrace();
            System.exit(1); // Terminate the program if an error occurs
        }

        if (scores.isEmpty()) {
            System.out.println("No scores available to calculate statistics.");
        } else {
            // Calculate the mean and standard deviation
            double mean = calculateMean(scores);
            double standardDeviation = calculateStandardDeviation(scores, mean);

            // Print the calculated statistics
            System.out.println("Mean: " + mean);
            System.out.println("Standard Deviation: " + standardDeviation);
        }
    }

    private static double calculateMean(List<Integer> scores) {
        // Method to calculate the mean (average) of scores

        int sum = 0;

        for (int score : scores) {
            sum += score; // Calculate the sum of scores
        }

        return (double) sum / scores.size(); // Calculate and return the mean
    }

    private static double calculateStandardDeviation(List<Integer> scores, double mean) {
        // Method to calculate the standard deviation of scores

        double squaredDifferencesSum = 0.0;

        for (int score : scores) {
            // Calculate the sum of squared differences from the mean
            squaredDifferencesSum += Math.pow(score - mean, 2);
        }

        double variance = squaredDifferencesSum / scores.size(); // Calculate variance

        return Math.sqrt(variance); // Calculate and return the standard deviation
    }
}

