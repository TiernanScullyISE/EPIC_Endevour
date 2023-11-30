package main;

public interface QuizCategory { // Interface for the different quiz categories
    void loadQuestions(); // Load the questions from the CSV file

    Question[] getQuestions(); // Return the questions
}
