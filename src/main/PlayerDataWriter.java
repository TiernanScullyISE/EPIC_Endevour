package main;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

class PlayerDataWriter {
    private File csvFile;

    public PlayerDataWriter(String username) {
        csvFile = new File(username + ".csv");

        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void writeScore(int score, int seconds) {
        try {
            FileWriter writer = new FileWriter(csvFile, true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            out.println(score + "," + seconds);
            out.close();
            bw.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public File getCSVFile() {
        return csvFile;
    }
}

