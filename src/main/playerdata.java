package main;

import java.io.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
public class playerdata {
    private File csvFile;

    public playerdata() {
    	System.out.println("Enter username: ");
    	Scanner scanner = new Scanner(System.in);
    	String user = scanner.next();
    	int score = 60;
    	
        csvFile = new File(user);
        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }}}
        
    

    private void writescore(int score) {
        try (FileWriter writer = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(score);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean loginUser(String user, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) {
                    return true; // User logged in successfully
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false; // User login failed
    }

    public static void main(String[] args) {
        playerdata playerData = new playerdata();

        // Example of user registration
        playerData.registerUser("username", "password");

        // Example of user login
        boolean loggedIn = playerData.loginUser("username", "password");
        if (loggedIn) {
            System.out.println("User logged in successfully.");
        } else {
            System.out.println("Login failed.");
        }
    }
}
