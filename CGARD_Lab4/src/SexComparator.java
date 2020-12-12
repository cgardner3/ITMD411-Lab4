/*
 * Cheryl Gardner
 * 11/24/2020
 * Lab 04-411
 * Purpose: Write a program that will compare the strings of males and females
 * and then return the result to the user
 */

//Import the java.util.Comparator package that will be used later in the program
import java.util.Comparator;

//Declare a new public class called SexComparator that will compare the data of two different sexes
public class SexComparator implements Comparator<BankRecords>{
	 
	//Override the last result to start the new comparator fresh
	@Override
	//Compare two different strings of o1 and o2 and return an integer
	public int compare(BankRecords o1, BankRecords o2) {
	// Use the compare to function to compare the two strings to get an integer result
	int result = o1.getterSex().compareTo(o2.getterSex());
	//Return the result to be pushed back into the main program
	return result;
	}
}

