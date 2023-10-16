package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] beginnerDM = {"True or False: The empty set is a subset of every set.","True or False: In propositional logic, the conjunction (AND) of two false statements is true.","True or False: A direct proof starts with the assumption of the negation of the statement to be proven."}; // DM = discrete maths
		String[] intermediateDM = {"True or False: A relation is reflexive if every element in its domain is related to itself.","True or False: Mathematical induction is used to prove statements for all positive integers.","True or False: A bipartite graph can have an odd cycle."};
		String[] expertDM = {"True or False: The symmetric difference of two sets is always commutative.","True or False: A partial order is always antisymmetric.","True or False: In proof by contradiction, we assume the statement is true and derive a contradiction."};

		boolean[] beginnerDMAns = {true,true,false}; // beginnerDMAns = beginner Discrete Maths Answers
		boolean[] intermediateDMAns = {true,true,true};
		boolean[] expertDMAns = {true,true,true};
		
		int totalDMLength = beginnerDM.length + intermediateDM.length + expertDM.length; // get the total length of the new array
		String[] allDM = new String[totalDMLength]; //create new array to have all the DM questions
		boolean[] allDMAns = new boolean[9]; // create new array to have all the DM answers
		
		int index = 0; // index for new list

		// add all the questions to the array with all the DM questions
		for (String question : beginnerDM) { // loops through all the elements of beginnerDM 
		    allDM[index] = question; // sets whatever the question at index question in beginnerDM to the index in allDM
		    index++; // index +=1 so that it sets the next question to the next index
		}

		for (String question : intermediateDM) {
		    allDM[index] = question;
		    index++;
		}

		for (String question : expertDM) {
		    allDM[index] = question;
		    index++;
		}
		
		index = 0;
		// add all the answers to a list
		for (boolean i : beginnerDMAns) {
			allDMAns[index] = i;
			index++;
		}
		
		for (boolean i : intermediateDMAns) {
			allDMAns[index] = i;
			index++;
		}
		
		for (boolean i : expertDMAns) {
			allDMAns[index] = i;
			index++;
		}
		/*
		for (int i = 0; i < allDM.length; i++) {
			System.out.println(allDM[i]);
		}
		*/
////////////////////////////////////
		
		String[] beginnerCSci = {"True or False: The AND operation in Boolean logic returns true if both inputs are true.","True or False: Two logic expressions are considered semantically equivalent if they have the same variable names.","True or False: BNF is used to define the meaning and behavior of programming code."}; // CSci = computer science
		String[] intermediateCSci = {"True or False: In multi-valued logic systems, a ternary logic can have three possible values: true, false, and maybe.","True or False: Syntax in programming languages specifies the order of execution of statements, while semantics determines the structure of code.","True or False: Inductive reasoning is a method for proving statements by providing a counterexample to disprove them."};
		String[] expertCSci = {"True or False: Boolean algebra can be extended to include don't-care values, which represent conditions where the output value is not important.","True or False: Congruence is an equivalence property that allows for the substitution of equivalent expressions in any context.","True or False: BNF is a notation used to describe the syntax of programming languages, focusing on the structure and grammar of code."};
		
		boolean[] beginnerCSciAns = {true,false,false}; // CSciAns = computer science answers
		boolean[] intermediateCSciAns = {true,false,false};
		boolean[] expertCSciAns = {true,true,true};
		
		int totalCSciLength = beginnerCSci.length + intermediateCSci.length + expertCSci.length; // get the total length of the new array
		String[] allCSci = new String[totalCSciLength]; //create new array to have all the CSci questions
		boolean[] allCSciAns = new boolean[9]; // create new array to have all the DM answers
		
		index = 0; // index for new list

		// add all the questions to the array with all the CSci questions
		for (String question : beginnerCSci) { // loops through all the elements of beginnerCSci 
		    allCSci[index] = question; // sets whatever the question at index question in beginnerDM to the index in allCSci
		    index++; // index +=1 so that it sets the next question to the next index
		}

		for (String question : intermediateCSci) {
		    allCSci[index] = question;
		    index++;
		}

		for (String question : expertCSci) {
		    allCSci[index] = question;
		    index++;
		}
		
		index = 0;
		// add all the answers to a list
		for (boolean i : beginnerCSciAns) {
			allCSciAns[index] = i;
			index++;
		}
		
		for (boolean i : intermediateCSciAns) {
			allCSciAns[index] = i;
			index++;
		}
		
		for (boolean i : expertCSciAns) {
			allCSciAns[index] = i;
			index++;
		}
		/*
		for (int i = 0; i < allCSci.length; i++) {
			System.out.println(allCSci[i]);
		}
		*/
/////////////////////////////////////
		//COrg = computer organisation
		String[] beginnerCOrg = {"True or False: A byte consists of 8 bits.","True or False: A CPU can directly execute high-level programming languages.","True or False: RAM stands for Random Access Memory, and it is a type of permanent storage."}; // COrg = computer organisation
		String[] intermediateCOrg = {"True or False: Caching is a technique used to improve memory access times in a computer system.","True or False: Cache memory is slower but larger in size compared to main memory (RAM).","True or False: The ALU (Arithmetic Logic Unit) is responsible for performing mathematical operations in the CPU."};
		String[] expertCOrg = {"True or False: A superscalar processor can execute multiple instructions simultaneously.","True or False: The concept of \"endianess\" determines how multi-byte data is stored in memory.","True or False: A digital signal processor (DSP) is specifically designed for complex scientific calculations."};
		
		boolean[] beginnerCOrgAns = {true,false,false}; // COrgAns = computer organisation answers
		boolean[] intermediateCOrgAns = {true,false,true};
		boolean[] expertCOrgAns = {true,true,false};
		
		int totalCOrgLength = beginnerCOrg.length + intermediateCOrg.length + expertCOrg.length; // get the total length of the new array
		String[] allCOrg = new String[totalCOrgLength]; //create new array to have all the COrg questions
		boolean[] allCOrgAns = new boolean[9]; // create new array to have all the DM answers
		
		index = 0; // index for new list

		// add all the questions to the array with all the COrg questions
		for (String question : beginnerCOrg) { // loops through all the elements of beginnerCOrg 
		    allCOrg[index] = question; // sets whatever the question at index question in beginnerCOrg to the index in allCOrg
		    index++; // index +=1 so that it sets the next question to the next index
		}

		for (String question : intermediateCOrg) {
		    allCOrg[index] = question;
		    index++;
		}

		for (String question : expertCOrg) {
		    allCOrg[index] = question;
		    index++;
		}
		
		index = 0;
		// add all the answers to a list
		for (boolean i : beginnerCOrgAns) {
			allCOrgAns[index] = i;
			index++;
		}
		
		for (boolean i : intermediateCOrgAns) {
			allCOrgAns[index] = i;
			index++;
		}
		
		for (boolean i : expertCOrgAns) {
			allCOrgAns[index] = i;
			index++;
		}
		/*
		for (int i = 0; i < allCOrg.length; i++) {
			System.out.println(allCOrg[i]);
		}
		*/
/////////////////////////////////////////
		
		//System.out.println(allDM.length+allCSci.length+allCOrg.length);
		
		String[] questionsDM = new String[6], questionsCSci = new String[6], questionsCOrg = new String[6]; // make arrays that will have the random 6 questions in them
		boolean[] answersDM = new boolean[6], answersCSci = new boolean[6], answersCOrg = new boolean[6];
		
		ArrayList<String> allDMList = new ArrayList<>(); // Create an ArrayList from the original array so i can remove elements from the list 
        for (String item : allDM) {
            allDMList.add(item);
        }
        
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
		
		int correct = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Answer the following 6 questions on Discrete Maths:");
		for (int i = 0; i < 6; i++) {
			System.out.println(questionsDM[i]);
			boolean answer = scanner.nextBoolean();
			if (answer == answersDM[i]) {
				correct++;
				System.out.println("Correct, you have answered "+correct+" questions correctly");
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
			} else {
				System.out.println("Incorrect, the answer was: "+answersCOrg[i]);
			}
		}
		
		System.out.println("The quiz is over, you answered "+correct+" questions correctly out of 18");
		double percent = (correct/18.0)*100;
		System.out.println("You got "+percent+"%");
	}

}
