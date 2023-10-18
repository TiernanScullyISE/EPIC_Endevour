package logon;
import java.io.*;
import java.util.Scanner;

public class testsql {
    public static void main(String[] args) {
        // Initialize the CSV file
        String csvFileName = "user_data.csv";
        File csvFile = new File(csvFileName);

        // Create the CSV file if it doesn't exist
        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFileName);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        // Initialize the scanner to take user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to register, 2 to login, or any other key to exit: ");
            int choice = scanner.nextInt();
            if (choice == 1) {

            } else if (choice == 2) {
                loginUser(csvFile, scanner);
            } else {
                break;
            }
        }
    }

    // Register a new user in the CSV file
    private static void registerUser(File csvFile, Scanner scanner) {
        System.out.print("Enter a new username: ");
        String username = scanner.next();
        System.out.print("Enter a password: ");
        String password = scanner.next();

        try (FileWriter writer = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + "," + password);
            System.out.println("User registered successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Log in an existing user from the CSV file
    private static void loginUser(File csvFile, Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean loggedIn = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("Login successful.");
                    loggedIn = true;
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("Login failed. Invalid username or password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}