package main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpListener implements ActionListener {
	//Sign up button action
    public void actionPerformed(ActionEvent e) {
        JTextField newUserTextField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        Object[] message = {
            "Enter New Email (Username):", newUserTextField,
            "Enter New Password:", newPasswordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "User Registration", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String newUsername = newUserTextField.getText().trim();
            String newEmailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
            Pattern pattern = Pattern.compile(newEmailRegex);
            Matcher matcher = pattern.matcher(newUsername);

            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Invalid Email Address, Please Retry.");
            } else {
                char[] newPasswordChars = newPasswordField.getPassword();
                String newPassword = new String(newPasswordChars);
                registerUser(newUsername, newPassword);
            }
        }
    }
    
    //Sign up - write username & password to csv
    private void registerUser(String username, String password) {
        try (FileWriter writer = new FileWriter("user_data.csv", true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + "," + password);
            JOptionPane.showMessageDialog(null, "User Registered Successfully. Please Login.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
