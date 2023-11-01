package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private File csvFile;

    // method to write data to the leaderboard
    public static void WriteToLeaderboard(String user, int MeanScore, int MeanTime) {
        File csvFile = new File("Leaderboard.csv"); // create or open a CSV file for the leaderboard

        try {
            List<LeaderboardEntry> entries = readLeaderboardData(csvFile); // read existing data from the CSV file

            entries.add(new LeaderboardEntry(user, MeanScore, MeanTime)); // add the new entry to the list

            Collections.sort(entries, new LeaderboardComparator()); // sort the list based on highest score and lowest time

            writeLeaderboardData(csvFile, entries); // write the sorted data back to the CSV file
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // method to read data from the CSV file and populate a list of LeaderboardEntry objects
    private static List<LeaderboardEntry> readLeaderboardData(File csvFile) throws IOException {
        List<LeaderboardEntry> entries = new ArrayList<>();
        if (csvFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String user = parts[0];
                    int meanScore = Integer.parseInt(parts[1]);
                    int meanTime = Integer.parseInt(parts[2]);
                    entries.add(new LeaderboardEntry(user, meanScore, meanTime));
                }
            }
            reader.close();
        }
        return entries;
    }

    // method to write data back to the CSV file
    private static void writeLeaderboardData(File csvFile, List<LeaderboardEntry> entries) throws IOException {
        FileWriter writer = new FileWriter(csvFile, false);
        PrintWriter out = new PrintWriter(new BufferedWriter(writer));
        for (LeaderboardEntry entry : entries) {
            out.println(entry.getUser() + "," + entry.getMeanScore() + "," + entry.getMeanTime());
        }
        out.close();
    }

    public File getCSVFile() {
        return csvFile;
    }

    public static void main(String[] args) {
        // WriteToLeaderboard("James", 15, 33); 
    }
}

class LeaderboardEntry {
    private String user;
    private int meanScore;
    private int meanTime;

    // constructor to create a LeaderboardEntry. when a new LeaderboardEntry object is created, the constructor sets these attributes
    // they ensure that the object starts with a valid and consistent state
    public LeaderboardEntry(String user, int meanScore, int meanTime) {
        this.user = user;
        this.meanScore = meanScore;
        this.meanTime = meanTime;
    }

    // getters to access the attributes
    // getters are methods that provide access to the private attributes (fields) of an object. they allow other parts of the program to retrieve the values of these attributes without directly accessing them
    public String getUser() {
        return user;
    }

    public int getMeanScore() {
        return meanScore;
    }

    public int getMeanTime() {
        return meanTime;
    }
}

class LeaderboardComparator implements Comparator<LeaderboardEntry> {
    @Override
    public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
        // Sort by highest score, and in case of a tie, by lowest time
        if (entry1.getMeanScore() == entry2.getMeanScore()) {
            return entry1.getMeanTime() - entry2.getMeanTime();
        }
        return entry2.getMeanScore() - entry1.getMeanScore();
    }
}
