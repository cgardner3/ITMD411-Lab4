/*
 * Cheryl Gardner
 * 11/24/2020
 * Lab 04-411
 * Purpose: To name the three methods that the bank needs to process
 * the names are: readData(), processData(), and printData()
 */

//Declare a new abstract class called Client that can be extended to the other program
public abstract class Client{
	
	//read all of the details of the file with all the information
	public abstract void readData();
	//process and store all of the information of each variable in arrays 
	public abstract void processData();
	//print all of the data that was read and processed to the user
	public abstract void printData();
	
}