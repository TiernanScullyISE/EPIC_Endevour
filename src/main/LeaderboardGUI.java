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

public class LeaderboardGUI { // This class is used to display the leaderboard
	private static JFrame frame;
	private static JPanel panel;
	private static BufferedImage backgroundImage;

	public static void createLeaderboardWindow() { // This method creates the leaderboard window
		frame = new JFrame("Leaderboard"); // create the window
		frame.setSize(500, 500); // set the size
		frame.setLocation(650, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define how to close the application

		try {
			// load the background image from a file.
			backgroundImage = ImageIO.read(new File("ldrboard.png"));

			panel = new JPanel() { // create a custom panel for drawing the background image
				@Override
				protected void paintComponent(Graphics g) { // draw the background image to cover the panel
					super.paintComponent(g); // make the panel transparent
					if (backgroundImage != null) { // set the panel as the content of the window
						g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 	// set the layout to null
																								// for absolute
																								// positioning, this is
																								// so i can add text to a
																								// photo shopped image
					}
				}
			};

			panel.setLayout(null); // set the layout to null for absolute positioning, this is so i can add text to
									// a photo shopped image

			Font customFont1 = new Font("Arial", Font.PLAIN, 10); // set the fonts
			Font customFont2 = new Font("Arial", Font.PLAIN, 15);

			LeaderboardEntry[] topPlayers = getTopPlayers(5); // get the 5 best players from the CSV file

			int y = 145; // Initial vertical position for the labels
			for (LeaderboardEntry entry : topPlayers) { // for each entry in the list
				JLabel playerLabel = createLabel(entry.getUser(), customFont1); // create and add labels for each
																				// statistic with custom bounds and font
				playerLabel.setBounds(185, y, 300, 30); // set the bounds for the labels

				JLabel scoreLabel = createLabel("" + entry.getMeanScore(), customFont2); // create and add labels for
																							// each statistic with
																							// custom bounds and font
				scoreLabel.setBounds(320, y, 150, 30); // set the bounds for the labels

				JLabel timeLabel = createLabel("" + entry.getMeanTime() + "", customFont2); 
				timeLabel.setBounds(390, y, 250, 30); 

				panel.add(playerLabel); // add the labels to the panel
				panel.add(scoreLabel); 
				panel.add(timeLabel);

				y += 68; // Increase vertical position for the next set of labels
			}

		} catch (IOException e) {
			e.printStackTrace();
			panel = new JPanel(); // Fallback if the image loading fails
		}

		frame.setContentPane(panel); // set the panel as the content of the window
		frame.setVisible(true); // make the window visible to the user
	}

	private static JLabel createLabel(String text, Font font) { // This method creates the labels for the leaderboard
		JLabel label = new JLabel(text); 
		label.setFont(font); // set the font for the label
		return label;
	}

	private static LeaderboardEntry[] getTopPlayers(int count) { // This method gets the top 5 players from the CSV file
		// Read, sort, and return the top 'count' players from the CSV file
		LeaderboardEntry[] allEntries = readLeaderboardData("Leaderboard.csv"); // read the data from the CSV file
		if (allEntries.length == 0) { // if there is no data in the CSV file
			return new LeaderboardEntry[0];
		}

		// Sort by your criteria (highest score and lowest time)
		java.util.Arrays.sort(allEntries, new LeaderboardComparator()); 

		int numPlayers = Math.min(count, allEntries.length); // get the top 5 players
		LeaderboardEntry[] topPlayers = new LeaderboardEntry[numPlayers]; // create an array for the top 5 players
		System.arraycopy(allEntries, 0, topPlayers, 0, numPlayers); // copy the top 5 players to the array

		return topPlayers;
	}

	private static LeaderboardEntry[] readLeaderboardData(String filename) { 
		// Read leaderboard data from the CSV file
		List<LeaderboardEntry> entries = new ArrayList<>();
		try { // try to read the data from the CSV file
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
						entries.add(new LeaderboardEntry(user, meanScore, meanTime)); // add the stats to the
																						// leaderboard
					}
				}
				reader.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return entries.toArray(new LeaderboardEntry[0]); 
	}

}
