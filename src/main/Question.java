package main;

public class Question { // Class for the questions
    private String text; // The question text
    private String[] answers; // The possible answers

    public Question(String text, String[] answers) { // Constructor for the question
        this.text = text;
        this.answers = answers;
    }

    public String getText() { // Get the question text
        return text;
    }

    public String[] getAnswers() { // Get the possible answers
        return answers;
    }
}