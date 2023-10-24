package main;

import java.util.ArrayList;

public class QuestionBank {
	ArrayList<Question> bank;
	
	
	public QuestionBank() {
		bank = new ArrayList<Question>();
		String[] answers = new String[2];
		answers[0] = "clean bandit";
		answers[1] = "arcade fire";
		Question myFirstQuestion = new Question("What's the best band in the world", answers, 1 );
		bank.add(myFirstQuestion);
		answers = new String[2];
		answers[0] = "clean bandit";
		answers[1] = "arcade fire";
		Question MySecondQuestion = new Question("What is my name", answers, 2 );
		bank.add(MySecondQuestion);
		
		System.out.println(bank.get(0).questionText);
		for(int i = 0; i < bank.get(0).answers.length; i++) {
			System.out.println(bank.get(0).answers[i]);
		}
		
		
		// read in questions - some sort of loop to add each question to the arraylist
	}
}
