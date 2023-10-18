package logon;

//import libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class mergeGUIandCSV implements ActionListener {
	
    // GUI components that are needed to run
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton signUpButton;
    private static JLabel success;

    // start of csv file 
    private static String csvFileName = "user_data.csv";
    private static File csvFile = new File(csvFileName);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mergeGUIandCSV mainInstance = new mergeGUIandCSV();

        // Create a frame
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(360, 200);
        frame.setLocation(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // Username label
        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Username input field
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        // Password input field
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(mainInstance);
        panel.add(loginButton);

        // Sign Up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(mainInstance.new SignUpListener());
        panel.add(signUpButton);

        // button to show status of success in logging in 
        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);

        // Create the CSV file if it doesn't exist already
        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                //creates in current directory
                System.out.println("New CSV file created: " + csvFileName);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        }
    }
    
    //Action listener performs when login button is pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            //System.out.println("Login button clicked");
            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            loginUser(user, password);
        }
    }
    
    //checked if entered username and password are in CSV
    private void loginUser(String user, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean loggedIn = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) {
                    //System.out.println("Login successful.");
                    success.setText("Login successful");
                    loggedIn = true;
                    break;
                }
            }

            if (!loggedIn) {
                success.setText("Login Failed");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 // Action listener for registering new user
    class SignUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField newUserTextField = new JTextField();
            JPasswordField newPasswordField = new JPasswordField();
            Object[] message = {
                "Enter New Username:", newUserTextField,
                "Enter New Password:", newPasswordField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "User Registration", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String newUsername = newUserTextField.getText().trim();
                char[] newPasswordChars = newPasswordField.getPassword();
                String newPassword = new String(newPasswordChars);
                registerUser(newUsername, newPassword);
            }
        }
        //write username and password into csv so its remembered in future
        private void registerUser(String username, String password) {
            try (FileWriter writer = new FileWriter(csvFile, true);
                 BufferedWriter bw = new BufferedWriter(writer);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(username + "," + password);
                System.out.println("User registered successfully.");
                success.setText("User Registered Successfully, Please Login");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}