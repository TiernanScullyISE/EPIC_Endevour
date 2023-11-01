package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LeaderboardGUI {
	private static JFrame frame;
	private static JPanel panel;
	private static BufferedImage backgroundImage;

	public static void createLeaderboardWindow() {
		frame = new JFrame("Leaderboard");
		frame.setSize(500, 500);
		frame.setLocation(750, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			// load the background image from a file.
			backgroundImage = ImageIO.read(new File("ldrboard.png"));

			panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					if (backgroundImage != null) {
						g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
					}
				}
			};

			panel.setLayout(null); // set the layout to null for absolute positioning, this is so i can add text to a photo shopped image

			Font customFont1 = new Font("Arial", Font.PLAIN, 10); // set the fonts
			Font customFont2 = new Font("Arial", Font.PLAIN, 15);

			LeaderboardEntry[] topPlayers = getTopPlayers(5); // get the 5 best players from the CSV file 

			int y = 145; // Initial vertical position for the labels
			for (LeaderboardEntry entry : topPlayers) {
				JLabel playerLabel = createLabel(entry.getUser(), customFont1);
				playerLabel.setBounds(185, y, 300, 30);

				JLabel scoreLabel = createLabel("" + entry.getMeanScore(), customFont2);
				scoreLabel.setBounds(320, y, 150, 30);

				JLabel timeLabel = createLabel("" + entry.getMeanTime() + "", customFont2);
				timeLabel.setBounds(390, y, 250, 30);

				panel.add(playerLabel);
				panel.add(scoreLabel);
				panel.add(timeLabel);

				y += 68; // Increase vertical position for the next set of labels
			}

		} catch (IOException e) {
			e.printStackTrace();
			panel = new JPanel(); // Fallback if the image loading fails
		}

		frame.setContentPane(panel);
		frame.setVisible(true);
	}

	private static JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		return label;
	}

	private static LeaderboardEntry[] getTopPlayers(int count) {
		// Read, sort, and return the top 'count' players from the CSV file
		LeaderboardEntry[] allEntries = readLeaderboardData("Leaderboard.csv");
		if (allEntries.length == 0) {
			return new LeaderboardEntry[0];
		}

		// Sort by your criteria (highest score and lowest time)
		java.util.Arrays.sort(allEntries, new LeaderboardComparator());

		int numPlayers = Math.min(count, allEntries.length);
		LeaderboardEntry[] topPlayers = new LeaderboardEntry[numPlayers];
		System.arraycopy(allEntries, 0, topPlayers, 0, numPlayers);

		return topPlayers;
	}

	private static LeaderboardEntry[] readLeaderboardData(String filename) {
        // Read leaderboard data from the CSV file
        List<LeaderboardEntry> entries = new ArrayList<>();
        try {
            File csvFile = new File(filename);
            if (csvFile.exists()) { // if a CSV file with their name already exists
                BufferedReader reader = new BufferedReader(new FileReader(csvFile)); // read the file
                String line;
                while ((line = reader.readLine()) != null) { // as long as the line it's on is not blank
                    String[] parts = line.split(","); // vales are seperated by commas
                    if (parts.length == 3) {
                        String user = parts[0];
                        int meanScore = Integer.parseInt(parts[1]);
                        int meanTime = Integer.parseInt(parts[2]);
                        entries.add(new LeaderboardEntry(user, meanScore, meanTime)); // add the stats to the leaderboard
                    }
                }
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entries.toArray(new LeaderboardEntry[0]);
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createLeaderboardWindow());

		LeaderboardGUI gui = new LeaderboardGUI();
		LeaderboardGUI.createLeaderboardWindow();
	}
}
