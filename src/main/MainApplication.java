package main;

import javax.swing.SwingUtilities;

public class MainApplication {
    public static void main(String[] args) {
        // Start the GUI application on the event dispatch thread (EDT)

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and show the login window.
                new LoginWindow().createLoginWindow();
            }
        });
    }
}

