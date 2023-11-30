package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow { // This class is used to display the login window
    private JFrame frame; // Create a window frame
    private JTextField userText; // Input field for the username
    private JPasswordField passwordText; // Input field for the password
    private JLabel successLabel;
    private File csvFile; // File to store user data.
    private BufferedImage backgroundImage; // image to use as the background.

    public void createLoginWindow() { // This method creates the login window
        frame = new JFrame("User Registration & Login"); // Create the window
        frame.setSize(500, 300); // Define the window's size
        frame.setLocation(370, 200); // Set the window's initial position
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // how to close the application

        try {
            // Load the background image from file
            backgroundImage = ImageIO.read(new File("Login-Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom panel for drawing the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { // Draw the background image to cover the panel
                super.paintComponent(g); // Make the panel transparent
                if (backgroundImage != null) {
                    // Draw the background image to cover the panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setOpaque(false); // Make the panel transparent
        frame.setContentPane(panel); // Set the panel as the content of the window

        panel.setLayout(null);

        // Create a label for the username input
        JLabel userLabel = new JLabel("Email (Username):");
        userLabel.setBounds(10, 20, 150, 25); // Set its position and size
        panel.add(userLabel); // add the label to the panel

        // Create a text field for the username input.
        userText = new JTextField(20);
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        // Create a label for the password input.
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 55, 150, 25);
        panel.add(passwordLabel);

        // Create a password input field for secure password entry.
        passwordText = new JPasswordField();
        passwordText.setBounds(160, 55, 165, 25);
        panel.add(passwordText);

        // Create a button for login.
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 90, 80, 25);
        // action when the button is pressed.
        loginButton.addActionListener(new ActionListener() { // This method is triggered when the "Login" button is
                                                             // clicked.
            public void actionPerformed(ActionEvent e) { // Retrieve the username and password.
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                loginUser(user, password);
            }
        });
        panel.add(loginButton); // add the button to the panel

        // button for signing up.
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 90, 80, 25);
        signUpButton.addActionListener(new SignUpListener());
        panel.add(signUpButton);

        // label for displaying success or failure messages.
        successLabel = new JLabel("");
        successLabel.setBounds(10, 120, 300, 25);
        panel.add(successLabel);

        // Make the window visible to the user
        frame.setVisible(true);

        // Create a file to store user data (if not already exists).
        csvFile = new File("user_data.csv");
        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1); // Exit the program with an error code if an exception occurs.
            }
        }
    }

    // A method for handling user login.
    private void loginUser(String user, String password) { // This method is triggered when the "Login" button is
                                                           // clicked.
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { // Read the user data from the CSV
                                                                                // file.
            String line;
            boolean loggedIn = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) { // If the username and
                                                                                               // password match
                    successLabel.setText("Login successful");
                    loggedIn = true;
                    frame.dispose(); // Close the login window on successful login.
                    JOptionPane.showMessageDialog(null, "Successfully logged in, Continue to Console");
                    int gamelevel = chooseGameLevel();
                    QuizManager runquestions = new QuizManager();
                    runquestions.runQuiz(user, gamelevel); // Run the quiz
                    break;
                }
            }

            if (!loggedIn) { // If the username and password do not match
                successLabel.setText("Login Failed");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int chooseGameLevel() { // This method is used to choose the game level
        String[] options = { "Beginner", "Intermediate", "Hard" };
        int choice = JOptionPane.showOptionDialog(null,
                "Choose the quiz difficulty level:",
                "Select Quiz Level",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options, // The array of strings to display as the buttons
                options[0]); // The default option

        // JOptionPane returns the index of the selected option
        // We add 1 to make it 1 for beginner, 2 for intermediate, 3 for hard
        return choice + 1;
    }

}