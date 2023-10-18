package main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginWindow {
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel successLabel;
    private File csvFile;

    public void createLoginWindow() {
        frame = new JFrame("User Registration & Login");
        frame.setSize(360, 200);
        frame.setLocation(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email (Username):");
        userLabel.setBounds(10, 20, 150, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 150, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(160, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                loginUser(user, password);
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(new SignUpListener());
        panel.add(signUpButton);

        successLabel = new JLabel("");
        successLabel.setBounds(10, 110, 300, 25);
        panel.add(successLabel);

        frame.setVisible(true);

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

    private void loginUser(String user, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean loggedIn = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) {
                    successLabel.setText("Login successful");
                    loggedIn = true;
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Sucesfully logged in, Continue to Console ");
                    questions.runQuestions();
                    
                    //closes the window after correct login
                    // Open the question window or perform any required action
                }
            }

            if (!loggedIn) {
                successLabel.setText("Login Failed");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

