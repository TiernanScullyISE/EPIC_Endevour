package logon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class GUI implements ActionListener{
	
	private static JLabel UserLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	
	public static void main (String[] args ) {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(360,200);
		frame.setLocation(600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		UserLabel = new JLabel("Username");
		UserLabel.setBounds(10, 20, 80, 25);
		panel.add(UserLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		userText.setVisible(true);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);
		
		button = new JButton ("Login");
		button.setBounds(10,80,80,25);
		button.addActionListener(new GUI());
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10,110,300,25);
		panel.add(success);
		frame.setVisible(true);
			
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Button clicked");
		String user = userText.getText();
		String password = passwordText.getText();
		System.out.println(user + "," + password);
		if (user.equals("john") && password.equals("dylan")) {
			success.setText("Login sucessful");
			
		}
		
		
	}
}
