package main;

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
		
		for (int i = 0; i < allDM.length; i++) {
			System.out.println(allDM[i]);
		}
		
////////////////////////////////////
		
		String[] beginnerCSci = {}; // CSci = computer science
		String[] intermediateCSci = {};
		String[] expertCSci = {};
		
		boolean[] beginnerCSciAns = {}; // CSciAns = computer science answers
		boolean[] intermediateCSciAns = {};
		boolean[] expertCSciAns = {};
		
/////////////////////////////////////
		
		String[] beginnerCOrg = {}; // COrg = computer organisation
		String[] intermediateCOrg = {};
		String[] expertCOrg = {};
		
		boolean[] beginnerCOrgAns = {}; // COrgAns = computer organisation answers
		boolean[] intermediateCOrgAns = {};
		boolean[] expertCOrgAns = {};
		
/////////////////////////////////////////
	}

}
