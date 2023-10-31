package main;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class questions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the quiz. Choose a quiz mode:");
        System.out.println("1 - Beginner");
        System.out.println("2 - Intermediate");
        System.out.println("3 - Advanced");
        System.out.print("Enter your choice: ");
        int quizMode = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        runQuestions(username, quizMode);
    }

    public static void runQuestions(String username, int quizMode) {
    	long startTime = System.currentTimeMillis();
        String[] beginnerQuestions = {
            "True or False: The empty set is a subset of every set.",
            "True or False: Discrete Mathematics is the study of mathematical structures that are countable or discrete",
            "True or False: A direct proof starts with the assumption of the negation of the statement to be proven",
            "True or False: ::= denotes definition",
            "True or False: Two logic expressions are considered semantically equivalent if they have the same variable names",
            "True or False: BNF is used to define the meaning and behavior of programming code",
            "True or False: A byte consists of 8 bits",
            "True or False: A CPU can directly execute high-level programming languages",
            "True or False: RAM stands for Random Access Memory, and it is a type of permanent storage"
        };
        boolean[] beginnerAnswers = { true, true, false, false, false, false, true, false, false };

        String[] intermediateQuestions = {
            "True or False: p <-> q means q if and only if p (biconditional statement)",
            "True or False: Mathematical induction is used to prove statements for all positive integers",
            "True or False: p -> q means if p then q (conditional statement)",
            "True or False: BNF stands for Backus-Naur Function",
            "True or False: Syntax in programming languages specifies the order of execution of statements, while semantics determines the structure of code",
            "True or False: Inductive reasoning is a method for proving statements by providing a counterexample to disprove them",
            "True or False: An Ordered Binary Decision Diagram (OBDD) is the same as a Reduced Ordered BDD (ROBDD)",
            "True or False: Congruence is an equivalence property that allows for the substitution of equivalent expressions in any context",
            "True or False: BNF is a notation used to describe the syntax of programming languages, focusing on the structure and grammar of code"
        };
        boolean[] intermediateAnswers = { true, true, true, true, false, false, false, true, true };

        String[] expertQuestions = {
            "True or False: The symmetric difference of two sets is always commutative",
            "True or False: A partial order is always antisymmetric",
            "True or False: In proof by contradiction, we assume the statement is true and derive a contradiction",
            "True or False: A superscalar processor can execute multiple instructions simultaneously",
            "True or False: The concept of \"endianess\" determines how multi-byte data is stored in memory",
            "True or False: A digital signal processor (DSP) is specifically designed for complex scientific calculations",
            "True or False: A GPU (Graphics Processing Unit) is typically designed for parallel processing of graphics and general-purpose computation",
            "True or False: Quicksort is generally faster than mergesort for small lists but can be less efficient for large lists",
            "True or False: An NP-complete problem is a problem for which a proposed solution can be verified quickly but for which there is no known quick method to find a solution"
        };
        boolean[] expertAnswers = { true, true, true, true, true, false, true, true, true };

        String[] selectedCategoryQuestions;
        boolean[] selectedCategoryAnswers;
        Random random = new Random();

        switch (quizMode) {
            case 1:
                selectedCategoryQuestions = beginnerQuestions;
                selectedCategoryAnswers = beginnerAnswers;
                break;
            case 2:
                selectedCategoryQuestions = intermediateQuestions;
                selectedCategoryAnswers = intermediateAnswers;
                break;
            case 3:
                selectedCategoryQuestions = expertQuestions;
                selectedCategoryAnswers = expertAnswers;
                break;
           

            default:
                System.out.println("Invalid choice. Exiting.");
                return;
        }

        if (selectedCategoryQuestions.length < 9) {
            System.out.println("Not enough questions for the selected mode. Please add more questions.");
            return;
        }

        // Select 9 random questions
        String[] selectedQuestions = new String[9];
        boolean[] selectedAnswers = new boolean[9];

        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(selectedCategoryQuestions.length);
            selectedQuestions[i] = selectedCategoryQuestions[randomIndex];
            selectedAnswers[i] = selectedCategoryAnswers[randomIndex];
            // Remove the selected question to avoid duplication
            selectedCategoryQuestions = removeElement(selectedCategoryQuestions, randomIndex);
            selectedCategoryAnswers = removeElement(selectedCategoryAnswers, randomIndex);
        }

        int correct = 0;
        int score = 0;

        System.out.println("Answer the following 9 questions:");

        for (int i = 0; i < 9; i++) {
            System.out.println(selectedQuestions[i]);
            Scanner scanner = new Scanner(System.in);
            boolean answer = scanner.nextBoolean();
            if (answer == selectedAnswers[i]) {
                correct++;
                System.out.println("Correct, you have answered " + correct + " questions correctly");
                if (i < 3) {
                    score += 1; // Beginner question
                } else if (i < 6) {
                    score += 3; // Intermediate question
                } else {
                    score += 5; // Expert question
                }
            } else {
                System.out.println("Incorrect, the answer was: " + selectedAnswers[i]);
            }
        }
        
<<<<<<< HEAD
        ArrayList<String> allCSciList = new ArrayList<>(); // Create an ArrayList from the original array so i can remove elements from the list 
        for (String item : allCSci) {
            allCSciList.add(item);
        }
        
        ArrayList<String> allCOrgList = new ArrayList<>(); // Create an ArrayList from the original array so i can remove elements from the list 
        for (String item : allCOrg) {
            allCOrgList.add(item);
        }
        
		Random random = new Random();
		
		for (int i = 0; i < 6; i++) { // loops 6 times because only need 6 questions
			int randIndex = random.nextInt(allDMList.size()); // the random index is a number between 0 and the length/size of allDM (9)
			questionsDM[i] = allDMList.get(randIndex); // add that index to questionsDM
			answersDM[i] = allDMAns[randIndex]; // add that index to answersDM
			allDMList.remove(randIndex); // remove the random index from the list because i don't want to pick it again
			// System.out.println(questionsDM[i]);
		} 

		for (int i = 0; i < 6; i++) { // loops 6 times because only need 6 questions
			int randIndex = random.nextInt(allCSciList.size()); // the random index is a number between 0 and the length/size (9)
			questionsCSci[i] = allCSciList.get(randIndex); // add that index to questionsDM
			answersCSci[i] = allCSciAns[randIndex]; // add that index to answersDM
			allCSciList.remove(randIndex); // remove the random index from the list because i don't want to pick it again
			// System.out.println(questionsCSci[i]);
		} 
		
		for (int i = 0; i < 6; i++) { // loops 6 times because only need 6 questions
			int randIndex = random.nextInt(allCOrgList.size()); // the random index is a number between 0 and the length/size (9)
			questionsCOrg[i] = allCOrgList.get(randIndex); // add that index to questionsDM
			answersCOrg[i] = allCOrgAns[randIndex]; // add that index to answersDM
			allCOrgList.remove(randIndex); // remove the random index from the list because i don't want to pick it again
			// System.out.println(questionsCOrg[i]);
		} 
		
////////////////////////////////////////////////
		
		int correct = 0, score = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the quiz. There are 3 different difficutly questions and each of them are worth a different amount of points, \nBeginner gives 1, Intermediate gives 3 and Expert gives 5. \nPress Enter to start.");
        scanner.nextLine(); // once they press enter the timer starts

        long startTime = System.currentTimeMillis(); // gets the time so when quiz over it can take it away from the time it is when they finish to get elapsed time
		
		System.out.println("Answer the following 6 questions on Discrete Maths:");
		for (int i = 0; i < 6; i++) {
			System.out.println(questionsDM[i]);
			boolean answer = scanner.nextBoolean();
			if (answer == answersDM[i]) {
				correct++;
				System.out.println("Correct, you have answered "+correct+" questions correctly");
				for (int j = 0; j < questionsDM.length; j++) { // check every index in questionsDM and if that element is in 
				    String question = questionsDM[i];			// either expertDM, intermediateDM or beginnerDM then you get score
				    if (isInArray(question, expertDM)) { 
				        score += 5; // expert gives 5 score
				    } else if (isInArray(question, intermediateDM)) {
				        score += 3; // intermediate gives 3 score
				    } else {
				        score += 1; // beginner gives 1 score
				    }
				    // System.out.println(score); testing if the score counter works as intended
				    break;
				}
			} else {
				System.out.println("Incorrect, the answer was: "+answersDM[i]);
			}
		}
		
		System.out.println("Answer the following 6 questions on Computer Science:");
		for (int i = 0; i < 6; i++) {
			System.out.println(questionsCSci[i]);
			boolean answer = scanner.nextBoolean();
			if (answer == answersCSci[i]) {
				correct++;
				System.out.println("Correct, you have answered "+correct+" questions correctly");
				for (int j = 0; j < questionsCSci.length; j++) {
				    String question = questionsCSci[i];
				    if (isInArray(question, expertCSci)) {
				        score += 5;
				    } else if (isInArray(question, intermediateCSci)) {
				        score += 3;
				    } else {
				        score += 1;
				    }
				    break;
				}
			} else {
				System.out.println("Incorrect, the answer was: "+answersCSci[i]);
			}
		}
		
		System.out.println("Answer the following 6 questions on Computer Organisation:");
		for (int i = 0; i < 6; i++) {
			System.out.println(questionsCOrg[i]);
			boolean answer = scanner.nextBoolean();
			if (answer == answersCOrg[i]) {
				correct++;
				System.out.println("Correct, you have answered "+correct+" questions correctly");
				for (int j = 0; j < questionsCOrg.length; j++) {
				    String question = questionsCOrg[i];
				    if (isInArray(question, expertCOrg)) {
				        score += 5;
				    } else if (isInArray(question, intermediateCOrg)) {
				        score += 3;
				    } else {
				        score += 1;
				    }
				    break;
				}
			} else {
				System.out.println("Incorrect, the answer was: "+answersCOrg[i]);
			}
		}
		
        long endTime = System.currentTimeMillis(); // get time they finish the quiz
        long elapsedTime = endTime - startTime; // end - start = elapsed
        long seconds = elapsedTime / 1000; // converts from miliseconds to seconds
        // System.out.println(seconds);
        long minutes = seconds / 60; // converts from seconds to minutes
        seconds %= 60; // get the modulus to get the amount of left over seconds
        
		System.out.println("The quiz is over, you answered "+correct+" questions correctly out of 18");
		double percent = (correct/18.0)*100;
		System.out.println("You got "+percent+"%");
		System.out.println("Quiz completed in " + minutes + " minutes and " + seconds + " seconds.");
		System.out.println("You got a total score of "+score);
	
	
}	

	// method to check if a question is in an array
	private static boolean isInArray(String question, String[] array) {
	    for (String item : array) {
	        if (item.equals(question)) {
	            return true;
	        }
	    }
	    return false;
	}

=======
        long endTime = System.currentTimeMillis(); // get time they finish the quiz
		long elapsedTime = endTime - startTime; // end - start = elapsed
		long seconds = elapsedTime / 1000; // converts from miliseconds to seconds
		// System.out.println(seconds);
		long minutes = seconds / 60; // converts from seconds to minutes
		seconds %= 60; // get the modulus to get the amount of left over seconds

		System.out.println("The quiz is over, you answered " + correct + " questions correctly out of 18");
		double percent = (correct / 18.0) * 100;
		System.out.println("You got " + percent + "%");
		System.out.println("Quiz completed in " + minutes + " minutes and " + seconds + " seconds.");
		System.out.println("You got a total score of " + score);

		PlayerDataWriter playerDataWriter = new PlayerDataWriter(username);
		playerDataWriter.writeScore(username, score, seconds);
		PlayerDataStatistics.PlayerData(username, score, seconds);
    }

    // Helper method to remove an element at a specific index from an array
    private static String[] removeElement(String[] array, int index) {
        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
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
>>>>>>> development
}