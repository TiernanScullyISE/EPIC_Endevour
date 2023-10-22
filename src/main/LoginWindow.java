package main;

// Import library swing
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// gui components
public class LoginWindow {
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel successLabel;
    private File csvFile;

    public void createLoginWindow() {
    	// create frame components
        frame = new JFrame("User Registration & Login");
        frame.setSize(360, 200);
        frame.setLocation(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create panel on the fra,e
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // create username label
        JLabel userLabel = new JLabel("Email (Username):");
        userLabel.setBounds(10, 20, 150, 25);
        panel.add(userLabel);
        
        // username text field
        userText = new JTextField(20);
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        // label for password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 150, 25);
        panel.add(passwordLabel);

        // text field for password
        passwordText = new JPasswordField();
        passwordText.setBounds(160, 50, 165, 25);
        panel.add(passwordText);
        
        // create a button for the login that does action when pressed 
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// when login button pressed get user details and store in variables
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                loginUser(user, password); // run login user function
            }
        });
        panel.add(loginButton); // add button

        JButton signUpButton = new JButton("Sign Up"); // sign up button
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(new SignUpListener());
        // action listener for sign up button - goes to other class
        panel.add(signUpButton);

        successLabel = new JLabel("");
        successLabel.setBounds(10, 110, 300, 25);
        panel.add(successLabel);
        
        // make verything visible
        frame.setVisible(true);
        
        csvFile = new File("user_data.csv");
        // create new csv file called user_data if not created already
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
            // write the username and password into the next line of a csv file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(password)) {
                	// if usernAme and password match, change success label to say login sucessful
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
            //catch any errors that may have occcured
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

