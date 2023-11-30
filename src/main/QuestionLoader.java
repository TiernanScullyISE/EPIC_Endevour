package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class QuestionLoader { // Class for loading the questions from the CSV file
    public static void readCSV(String filePath, Question[] questions) { // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < questions.length) {
                String[] values = line.split(",");
                String questionText = values[0]; // Get the question text
                String[] answers = Arrays.copyOfRange(values, 1, values.length); // Get the possible answers

                questions[index] = new Question(questionText, answers); // Create a new question
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void randomizeQuestions(Question[] questions) { // Randomize the questions
        Random random = new Random();
        for (int i = 0; i < questions.length; i++) {
            int swapIndex = random.nextInt(questions.length); // Get a random index
            // Swap questions[i] and questions[swapIndex]
            Question temp = questions[i]; // Create a temporary variable to store questions[i]
            questions[i] = questions[swapIndex]; // Set questions[i] to questions[swapIndex]
            questions[swapIndex] = temp; // Set questions[swapIndex] to the temporary variable
        }
    }
}
