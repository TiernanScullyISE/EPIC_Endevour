package main;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDataStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a username
        System.out.println("Enter username: ");
        String user = scanner.next();

        // Create an instance of PlayerDataWriter to write scores to a CSV file
        PlayerDataWriter userDataWriter = new PlayerDataWriter(user);

        // Ask the user to input a score
        System.out.println("Enter score: ");
        String scoreInput = scanner.next();

        try {
            int score = Integer.parseInt(scoreInput);
            int seconds = 5; // Fixed value for seconds
            userDataWriter.writeScore(score, seconds);

            // Retrieve the CSV file associated with the user
            File csvFile = userDataWriter.getCSVFile();

            // Calculate and print statistics
            calculateAndPrintStatistics(csvFile);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Score should be an integer.");
        }
    }

    private static void calculateAndPrintStatistics(File csvFile) {
        List<Integer> scores = new ArrayList<>();
        List<Integer> seconds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int score = Integer.parseInt(parts[0]);
                    int second = Integer.parseInt(parts[1]);
                    scores.add(score);
                    seconds.add(second);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        if (scores.isEmpty()) {
            System.out.println("No scores available to calculate statistics.");
        } else {
            double mean = calculateMean(scores);
            double standardDeviation = calculateStandardDeviation(scores, mean);

            System.out.println("Mean: " + mean);
            System.out.println("Standard Deviation: " + standardDeviation);
        }
    }

    private static double calculateMean(List<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    private static double calculateStandardDeviation(List<Integer> scores, double mean) {
        double squaredDifferencesSum = 0.0;
        for (int score : scores) {
            squaredDifferencesSum += Math.pow(score - mean, 2);
        }
        double variance = squaredDifferencesSum / scores.size();
        return Math.sqrt(variance);
    }
}
