package main;

class BeginnerQuiz implements QuizCategory { // Class for the beginner quiz
    private static final String FILE_PATH = "beginnerQuestions.csv"; // Path to the CSV file
    private Question[] questions = new Question[9]; // Array of questions

    @Override
    public void loadQuestions() { // Load the questions from the CSV file
        QuestionLoader.readCSV(FILE_PATH, questions);
    }

    @Override
    public Question[] getQuestions() { // Return the questions
        return questions;
    }
}

class IntermediateQuiz implements QuizCategory { // Class for the intermediate quiz
    private static final String FILE_PATH = "intermediateQuestions.csv";
    private Question[] questions = new Question[9];

    @Override
    public void loadQuestions() {
        QuestionLoader.readCSV(FILE_PATH, questions);
    }

    @Override
    public Question[] getQuestions() {
        return questions;
    }
}

class ExpertQuiz implements QuizCategory { // Class for the expert quiz
    private static final String FILE_PATH = "expertQuestions.csv";
    private Question[] questions = new Question[9];

    @Override
    public void loadQuestions() {
        QuestionLoader.readCSV(FILE_PATH, questions);
    }

    @Override
    public Question[] getQuestions() {
        return questions;
    }
}
