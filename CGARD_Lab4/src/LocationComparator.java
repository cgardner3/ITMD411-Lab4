/*
 * Cheryl Gardner
 * 11/24/2020
 * Lab 04-411
 * Purpose: Write a program that will compare the strings of two different regions
 * and then return the result to the user
 */

//Import the java.util.Comparator that will be used in the program
import java.util.Comparator;

//Declare a class called LocationComparator that will compare data of multiple locations when all of the other things are similar
public class LocationComparator implements Comparator<BankRecords>{
	
	//Override the last result to start it fresh for the next
	@Override
	//Compare two records in the Bank Records Program
	public int compare(BankRecords o1, BankRecords o2) {
	//Compare the two strings and turn the result into an integer
	int result = o1.getterRegion().compareTo(o2.getterRegion());
	//Return the result of the comparison back to the BankRecords program that will give you the number 
	return result;
	}
}