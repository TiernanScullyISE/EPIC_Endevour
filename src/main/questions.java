package main;

import java.util.Random;
import java.util.Scanner;

public class questions {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // new scanner

		System.out.println("Welcome to the quiz. Choose a quiz mode:");
		System.out.println("1 - Beginner");
		System.out.println("2 - Intermediate");
		System.out.println("3 - Advanced");
		System.out.print("Enter your choice: ");
		int quizMode = scanner.nextInt(); // user picks the difficulty
		scanner.nextLine(); 
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();

		runQuestions(username, quizMode);
	}

	public static void runQuestions(String username, int quizMode) {
		long startTime = System.currentTimeMillis();
		// index 1-3 are discrete math
		// index 4-6 are computer science
		// index 7-9 are computer org
		String[] beginnerQuestions = { "True or False: The empty set is a subset of every set.",
				"True or False: Discrete Mathematics is the study of mathematical structures that are countable or discrete",
				"True or False: A direct proof starts with the assumption of the negation of the statement to be proven",
				"True or False: ::= denotes definition",
				"True or False: Two logic expressions are considered semantically equivalent if they have the same variable names",
				"True or False: BNF is used to define the meaning and behavior of programming code",
				"True or False: A byte consists of 8 bits",
				"True or False: A CPU can directly execute high-level programming languages",
				"True or False: RAM stands for Random Access Memory, and it is a type of permanent storage" };
		boolean[] beginnerAnswers = { true, true, false, false, false, false, true, false, false };

		String[] intermediateQuestions = { "True or False: p <-> q means q if and only if p (biconditional statement)",
				"True or False: Mathematical induction is used to prove statements for all positive integers",
				"True or False: p -> q means if p then q (conditional statement)",
				"True or False: BNF stands for Backus-Naur Function",
				"True or False: Syntax in programming languages specifies the order of execution of statements, while semantics determines the structure of code",
				"True or False: Inductive reasoning is a method for proving statements by providing a counterexample to disprove them",
				"True or False: Caching is a technique used to improve memory access times in a computer system.",
				"True or False: Cache memory is slower but larger in size compared to main memory (RAM).",
				"True or False: The ALU (Arithmetic Logic Unit) is responsible for performing mathematical operations in the CPU." };
		boolean[] intermediateAnswers = { true, true, true, true, false, false, true, false, true };

		String[] expertQuestions = { "True or False: The symmetric difference of two sets is always commutative",
				"True or False: A partial order is always antisymmetric",
				"True or False: In proof by contradiction, we assume the statement is true and derive a contradiction",
				"True or False: An Ordered Binary Decision Diagram (OBDD) is the same as a Reduced Ordered BDD (ROBDD)",
				"True or False: Congruence is an equivalence property that allows for the substitution of equivalent expressions in any context.",
				"True or False: BNF is a notation used to describe the syntax of programming languages, focusing on the structure and grammar of code.",
				"True or False: The precision of a 3 digit decimal number from 000 to 999 is 1",
				"True or False: The error of a 3 digit decimal number from 000 to 999 is 0.5.",
				"True or False: The radix or base of a number system defines the range of possible values that a digit may have" };
		boolean[] expertAnswers = { true, true, true, false, true, true, true, true, true };

		String[] selectedCategoryQuestions; // set whatever difficulty the user chose to these
		boolean[] selectedCategoryAnswers;
		
		Random random = new Random();

		switch (quizMode) { 
		case 1: // set selectedCategoryQuestions to beginner difficulty
			selectedCategoryQuestions = beginnerQuestions;
			selectedCategoryAnswers = beginnerAnswers;
			break;
		case 2: // set selectedCategoryQuestions to intermediate difficulty
			selectedCategoryQuestions = intermediateQuestions;
			selectedCategoryAnswers = intermediateAnswers;
			break;
		case 3: // set selectedCategoryQuestions to expert difficulty
			selectedCategoryQuestions = expertQuestions;
			selectedCategoryAnswers = expertAnswers;
			break;

		default: // if they enter anything other than 1,2 or 3
			System.out.println("Invalid choice. Exiting.");
			return;
		}

		// select 9 random questions
		String[] selectedQuestions = new String[9]; // create new lists for the questions to be added to randomly
		boolean[] selectedAnswers = new boolean[9];

		for (int i = 0; i < 9; i++) { // loops 9 times because there are 9 questions
			int randomIndex = random.nextInt(selectedCategoryQuestions.length);
			selectedQuestions[i] = selectedCategoryQuestions[randomIndex];
			selectedAnswers[i] = selectedCategoryAnswers[randomIndex];
			// remove the selected question to avoid duplication
			selectedCategoryQuestions = removeElement(selectedCategoryQuestions, randomIndex);
			selectedCategoryAnswers = removeElement(selectedCategoryAnswers, randomIndex);
		}

		int correct = 0; // counts how many correct answers you've gotten
		int score = 0; // counts your total score based on if you got a question right and how hard the question was
		// beginner = score of 1, intermediate = score of 3, expert = score of 5

		System.out.println("Answer the following 9 questions:");

		for (int i = 0; i < 9; i++) { // loops 9 times because there are 9 questions
			System.out.println(selectedQuestions[i]); // prints the question
			Scanner scanner = new Scanner(System.in);
			boolean answer = scanner.nextBoolean(); // takes in answer
			if (answer == selectedAnswers[i]) { // checks if answer right
				correct++; // counts how many correct answers you've gotten
				System.out.println("Correct, you have answered " + correct + " questions correctly");
				if (i < 3) {
					score += 1; // index 1-3 are beginner questions
				} else if (i < 6) {
					score += 3; // index 4-6 are intermediate questions
				} else {
					score += 5; // index 7-9 are expert questions
				}
			} else {
				System.out.println("Incorrect, the answer was: " + selectedAnswers[i]); // tells them what the correct answer was
			}
		}

		long endTime = System.currentTimeMillis(); // get time they finish the quiz
		long elapsedTime = endTime - startTime; // end - start = elapsed
		long seconds = elapsedTime / 1000; // converts from miliseconds to seconds
		// System.out.println(seconds);
		long minutes = seconds / 60; // converts from seconds to minutes
		seconds %= 60; // get the modulus to get the amount of left over seconds

		//display stats to user
		System.out.println("The quiz is over, you answered " + correct + " questions correctly out of 18");
		double percent = (correct / 18.0) * 100;
		System.out.println("You got " + percent + "%");
		System.out.println("Quiz completed in " + minutes + " minutes and " + seconds + " seconds.");
		System.out.println("You got a total score of " + score);

		//write stats to CSV file
		PlayerDataWriter playerDataWriter = new PlayerDataWriter(username);
		playerDataWriter.writeScore(username, score, seconds);
		PlayerDataStatistics.PlayerData(username, score, seconds);
	}

	private static String[] removeElement(String[] array, int index) { // method to remove an element at a specific index from an array
		String[] newArray = new String[array.length - 1]; // make new array with a length of 1 less than the orginial
		for (int i = 0, j = 0; i < array.length; i++) { // loops through all elements in array
			if (i != index) { // if i is not equal to the element we want to remove
				newArray[j++] = array[i]; // add i to the new array
			}
		}
		return newArray; // returns a new array with every element except the one we wanted to remove
	}

	private static boolean[] removeElement(boolean[] array, int index) {
		boolean[] newArray = new boolean[array.length - 1];
		for (int i = 0, j = 0; i < array.length; i++) {
			if (i != index) {
				newArray[j++] = array[i];
			}
		}
		return newArray;
	}

}