/*
 * Cheryl Gardner
 * 11/24/2020
 * Lab 04-411
 * Purpose: Write a program that will both display all the comparison results to the user 
 * but also write the program in a FileWriter
 */

//import the necessary packages that are needed for the program
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//Create a new public class called Records that will draw in all of the data from the BankRecords class
public class Records extends BankRecords {

//create formatted object to write output directly to console & file
	static FileWriter fw = null;

	//Declare a new class called Records that will try to write a new file called "bankrecords.txt"
	public Records() {
		try {
			fw = new FileWriter("bankrecords.txt");

		}
		//If it cannot be done, throw an exception to the user
		catch (IOException e5) {
			e5.printStackTrace();
		}
	}

	//Call the main method of the program
	public static void main(String[] args) {

		Records br = new Records();
		//Call the readData function from BankRecords to allow for the data to be read which can then be used for comparisons
		br.readData();

		//Print the header for all of the results displayed
		System.out.println("Data analytic results:");
		//Try to write it also to the file writer and if it cannot be done then throw an exception
		try {
			fw.write("Data analytic results:");
		}
		catch (IOException e4) {
			e4.printStackTrace();
		}
		// call functions to perform analytics
		//AvgComp();     // analyze average income per loc
		//femsComp();  // female count w. mort/savings accounts 
		//malesComp(); // male counts per loc. w. car & 1 child 

		// *** close out file object ***//

		//Try the following block and if it does not work then throw an exception
		try {
			//Write name to file
			fw.write("\n");
			fw.write("\nCheryl Gardner ");
			//Format the day for writing and then declare a new Date called day
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date day = new Date();
			//Write the new date to file which follows the format that is delcared above
			fw.write(dateFormat.format(day) + "\n");
			//Close out the file
			fw.close();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}

	  //Declare a new private method called AvgComp() to calculate the average income for both males and females
	  private static void AvgComp() {

		//Sort all of the robjs data using the SexComparator class that will separate the data based on gender
		Arrays.sort(robjs, new SexComparator());

	
        //Declare all of the variables needed to keep track of the number of males and females as well as the Income for all of them
		int maleCt = 0, femCt = 0;
        double maleInc =0, femInc = 0;
        
        //Keep going through the for loop as long as the value of i does not exceed the length of the array, adding one to i every time
		for (int i = 0; i < robjs.length; i++)
			//If the sex is female then add one to the female count and add that income to the total income
			if (robjs[i].getterSex().equals("FEMALE")) {
				++femCt;
				femInc += robjs[i].getterIncome();
			}  
			//If the sex is not female (Male) then add one to the male count and add that income to the total income
			else {
				++maleCt;
				maleInc += robjs[i].getterIncome(); 
			}
			 
		//Display the calculated averages to the console for the user to see
		System.out.printf("\nAvg inc for Females: $%.2f", (femInc/femCt));
		System.out.printf("\nAvg inc for Males: $%.2f", (maleInc/maleCt));
		
		//Try to write the averages to the file and if it cannot be done then throw an exception
		try {
			fw.write("\n");
			fw.write("\nAvg inc. for Females: $" + String.format("%.2f", (femInc/femCt)));
			fw.write("\nAvg inc. for Males: $" + String.format("%.2f", (maleInc/maleCt)));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	  
	  //Declare a new method called femsComp() to find the number of females that have both a mortgage and savings acct
	  private static void femsComp() {
		  
		  //Sort the array by sex so that all of the data is supported with the females being first
		  Arrays.sort(robjs, new SexComparator());
		  //Declare a new local variable to keep track of the number of females that have both accounts
		  int total = 0;
		 
		  //Keep the for loop going as long as the value of i does not exceed the array length, adding one to i every time
		  for (int i = 0; i < robjs.length; i++)
			  //If that row is a female and has a mortgage and savings account do this
			  if (robjs[i].getterSex().contentEquals("FEMALE") && robjs[i].getterMortgage().contentEquals("YES") && robjs[i].getterSave_Act().contentEquals("YES")) {
				  //Add one to the total count
				  total += 1;
			  }
		  //Make a new line in between the various data displayed and then display the number of females that meet the requirement
		  System.out.println("");
		  System.out.println("\nNum. of Females with Mortgage & savings acct: " + total);
		  //Try to write the data to the file and if it cannot be done then throw an exception
		  try {
			  fw.write("\n");
			  fw.write("\nNum. of Females with Mortgage & savings acct: " + total);
		  } 
		  catch (IOException e1) {
			  e1.printStackTrace();
		  }
	  }
	  //Declare a new method called malesComp() that will compare the males that have a car and one child
	  private static void malesComp() {
		  
		  //Sort all of the data based on the location to have all the data entries of one location next to each other
		  Arrays.sort(robjs, new LocationComparator());
		  //Declare the local variables needed to keep track of all the locations
		  int innercity = 0, rural = 0, suburban = 0, town = 0;
		  
		  //Keep the for loop going as long as the value of i does not exceed the length of the array adding one every time
		  for (int i = 0; i < robjs.length; i++)
			  //If the person is a male has a car and one child do this program
			  if(robjs[i].getterSex().contentEquals("MALE") && robjs[i].getterCar().contentEquals("YES") && robjs[i].getterChildren() == 1) {
				  //If the location is innercity add one to the innercity count
				  if(robjs[i].getterRegion().contentEquals("INNER_CITY")) {
					  innercity +=1;
				  }
				  //If the location is rural then add one to the rural count
				  else if(robjs[i].getterRegion().contentEquals("RURAL")) {
					  rural +=1;
				  }
				  //If the location is suburban add one to the suburban count 
				  else if(robjs[i].getterRegion().contentEquals("SUBURBAN")) {
					  suburban +=1;
				  }
				  //If it is not any of these three then add one to the town count
				  else{
					  town +=1;
				  }
			  }
		  
		  //Add a space between the input and display the number of males with a car and one child in each of the locations
		  System.out.println("");
		  System.out.println("Innercity region males with car & 1 child: " + innercity);
		  System.out.println("Rural region males with car & 1 child: " + rural);
		  System.out.println("Suburban region males with car & 1 child: " + suburban);
		  System.out.println("Town region males with car & 1 child: " + town);
		  
		  //Try to write the data to the file and if it cannot be done then throw an exception
		  try {
			  fw.write("\n");
			  fw.write("\nInnercity region males with car & 1 child: " + innercity);
			  fw.write("\nRural region males with car & 1 child: " + rural);
			  fw.write("\nSuburban region males with car & 1 child: " + suburban);
			  fw.write("\nTown region males with car & 1 child: " + town);  
		  }
		  catch (IOException e) {
			  e.printStackTrace();
		  }  
	  }
}