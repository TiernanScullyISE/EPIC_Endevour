package logon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addingRegex {
    private static JFrame frame;                // JFrame for the main window
    private static JTextField userText;         // Input field for the email (username)
    private static JPasswordField passwordText; // Input field for the password
    private static JLabel successLabel;        // Label to display success or error messages
    private static File csvFile;                // CSV file for user data

    public addingRegex() {
        frame = new JFrame("User Registration & Login");  // Create the main window
        frame.setSize(360, 200);
        frame.setLocation(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();  // Create a panel to hold the components
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email (Username):");  // Label for the email input
        userLabel.setBounds(10, 20, 150, 25);
        panel.add(userLabel);

        userText = new JTextField(20);  // Input field for the email
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");  // Label for the password input
        passwordLabel.setBounds(10, 50, 150, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();  // Input field for the password
        passwordText.setBounds(160, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");  // Login button
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                loginUser(user, password);
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");  // Sign Up button
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(new SignUpListener());
        panel.add(signUpButton);

        successLabel = new JLabel("");  // Label to display success or error messages
        successLabel.setBounds(10, 110, 300, 25);
        panel.add(successLabel);

        frame.setVisible(true);

        // Create the CSV file if it doesn't exist already
        csvFile = new File("user_data.csv");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new addingRegex();
            }
        });
    }

    private void loginUser(String user, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean loggedIn = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) {
                    successLabel.setText("Login successful");
                    loggedIn = true;
                    break;
                }
            }

            if (!loggedIn) {
                successLabel.setText("Login Failed");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class SignUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField newUserTextField = new JTextField();
            JPasswordField newPasswordField = new JPasswordField();
            Object[] message = {
                "Enter New Email (Username):", newUserTextField,
                "Enter New Password:", newPasswordField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "User Registration", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String newUsername = newUserTextField.getText().trim();
                String newEmailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
                Pattern pattern = Pattern.compile(newEmailRegex);
                Matcher matcher = pattern.matcher(newUsername);

                if (!matcher.matches()) {
                    successLabel.setText("Invalid Email Address, Please Retry.");
                    return; // Exit the registration process if email is invalid
                }

                char[] newPasswordChars = newPasswordField.getPassword();
                String newPassword = new String(newPasswordChars);
                registerUser(newUsername, newPassword);
            }
        }

        private void registerUser(String username, String password) {
            try (FileWriter writer = new FileWriter(csvFile, true);
                 BufferedWriter bw = new BufferedWriter(writer);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(username + "," + password);
                successLabel.setText("User Registered Successfully. Please Login.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

