package main;

import java.util.Scanner;

public class QuizManager { // Class for running the quiz
    private static final int NUM_QUESTIONS = 9; // Number of questions in the quiz
    private static final int BEGINNER_MODE = 1; 
    private static final int INTERMEDIATE_MODE = 2; 
    private static final int EXPERT_MODE = 3;

    private static QuizCategory selectQuizCategory(int quizMode) { // Select the quiz category
        switch (quizMode) { // Switch statement for the quiz mode
            case BEGINNER_MODE: 
                return new BeginnerQuiz();
            case INTERMEDIATE_MODE:
                return new IntermediateQuiz();
            case EXPERT_MODE:
                return new ExpertQuiz();
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
                return null;
        }
    }

    public void runQuiz(String username, int quizMode) { // Run the quiz
        QuizCategory quizCategory = selectQuizCategory(quizMode);
        runQuiz(username, quizCategory);
    }

    private static void runQuiz(String username, QuizCategory quizCategory) { // Run the quiz
        long startTime = System.currentTimeMillis(); // Start the timer

        quizCategory.loadQuestions(); // Load the questions

        Question[] selectedQuestions = quizCategory.getQuestions(); // Get the questions

        int correct = 0; // Number of correct answers
        int score = 0; // 
        int correctAnswerInput = 1; 

        System.out.println("\nAnswer the following 9 questions:\n");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < NUM_QUESTIONS; i++) { // Loop through the questions
            System.out.println(selectedQuestions[i].getText()); // Print the question text
            String[] answers = selectedQuestions[i].getAnswers(); // Get the possible answers
            for (int j = 0; j < answers.length; j++) { // Loop through the possible answers
                System.out.println((j + 1) + ". " + answers[j]); // Print the possible answers
            }

            int userAnswer = getUserInput(scanner); // Get the user's answer

            if (userAnswer == correctAnswerInput) { // Check if the user's answer is correct
                correct++;
                System.out.println("Correct, you have answered " + correct + " questions correctly\n");
                if (i < 3) { // Check if the question is in the first 3 questions
                    score += 1; // Add 1 to the score becuase the questions is a beginner question and they are worth 1 points
                } else if (i < 6) { // Check if the question is in the first 6 questions
                    score += 3; // Add 3 to the score becuase the questions is a intermediate question and they are worth 3 points
                } else { // Check if the question is in the last 3 questions
                    score += 5; // Add 5 to the score becuase the questions is a expert question and they are worth 5 points
                }
            } else { // If the user's answer is incorrect
                System.out.println("Incorrect, the answer was: " + answers[0] + "\n"); // Print the correct answer
            }
        }

        scanner.close(); 
        long endTime = System.currentTimeMillis(); // End the timer
        long elapsedTime = endTime - startTime; // Calculate the elapsed time
        long seconds = elapsedTime / 1000; // Convert the elapsed time to seconds
        seconds %= 60; // Get the remainder of the seconds

        System.out.println("The quiz is over, you answered " + correct + " questions correctly out of 9");
        double percent = (correct / (double) NUM_QUESTIONS) * 100; // Calculate the percentage of correct answers
        System.out.println("You got " + percent + "%");
        System.out.println("Quiz completed in " + elapsedTime / 60000 + " minutes and " + seconds + " seconds.");
        System.out.println("You got a total score of " + score);

        PlayerDataWriter playerDataWriter = new PlayerDataWriter(username); // Create an instance of PlayerDataWriter to handle the user's CSV file
        playerDataWriter.writeScore(username, score, seconds); // Write the score and seconds to the CSV file
        PlayerDataStatistics.PlayerData(username, score, seconds); // Calculate the statistics
    }

    private static int getUserInput(Scanner scanner) { // Get the user's input
        while (true) { // Loop until the user enters a valid input
            try {
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) { // If the user enters an invalid input such as string
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
