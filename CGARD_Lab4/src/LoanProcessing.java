/*
 * Cheryl Gardner 
 * 11/24/2020
 * Lab 04-411
 * Purpose: Write a program that will serialize and deserialize an object.
 * Additionally, it will print all of the data for the record set after reading data 
 * It will also show the extra credit needed. 
 */

//Import all of the necessary packages that are needed for the program
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

//Create a new public class named LoanProcessing that extended to the BankRecords program
public class LoanProcessing extends BankRecords {

	//Call the main method 
	public static void main(String[] args) {
		//Call the bank records program and then read the data 
		BankRecords br = new BankRecords();
		br.readData();

		//Declare a new HashMap called bankMap
		Map<Long, BankRecords> bankMap = new HashMap<Long, BankRecords>();
		
		//Declare all of the local variables
		long t = 0;
		long start = 0, stop = 0;
		
		//As long as the number is in robjs add it into the bankMap HashMap
		for (BankRecords number : robjs) {
			bankMap.put(++t, number);
		}
		
		//Extra Credit 
		//Serialize an object called bankrecords.ser
		
		
		//Try to declare new variables to create the object 
		try {
			FileOutputStream fileOut = new FileOutputStream("bankrecords.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			
			//Start the timer for the initial starting time
			start = System.currentTimeMillis();
			//Write the object bankMap
			objectOut.writeObject(bankMap);
			
			//Both flush and close out of both objectOut and fileOut
			objectOut.flush();
			objectOut.close();
			fileOut.flush();
			fileOut.close();
		}
		//Throw an exception if it does not work
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//Write a timer to sleep for 5 seconds
		try{
			Thread.sleep(5000);
		}
		//Throw a new exception if it does not work
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//Desearalize the object that was created earlier called bankrecords.ser
		
		//Declare new variables needed for the deserialization
		FileInputStream fileIn;
		ObjectInputStream objectIn;
		int count = 0;
		//Try to create new objects and then read the object
		try {
			fileIn = new FileInputStream("bankrecords.ser");
			objectIn = new ObjectInputStream(fileIn);
			
			bankMap = (Map<Long, BankRecords>) objectIn.readObject();
			//Find the current stop time when the process ended 
			stop = System.currentTimeMillis();
			
			//Print the HashMap with the key and values
			/*for (Entry<Long, BankRecords> mapEntry : bankMap.entrySet()) {
				Long key = (Long) mapEntry.getKey();
				String value = (String) mapEntry.getValue().getterId();
				System.out.println("Data=> " + "Key val: " + key + " id val: " + value);
				//Add one to the count variable every time that the for loop goes through
				count++;
			}*/
		}
		//Throw two different exceptions if the try block does not work
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Call the dao method to read it
		Dao dao = new Dao();
		
		//Create and insert records into the table created and then retrieve the records
		dao.createTable();
		dao.insertRecords(robjs);
		ResultSet rs = dao.retrieveRecords();
		
		//Create heading for display of all of the results 
		System.out.println("\nBANK-DETAIL REPORT:\n");
		System.out.println(" ID\t    Income\t Pep");
		
		//Add the pep, id and income of all of the various datas after converting them
		try {
			while (rs.next()) {
				String id = rs.getString("id").toUpperCase();
				Double income = rs.getDouble("income");
				String pep = rs.getString("pep");
				
				System.out.printf("%7s %10.2f %7s\n", id, income, pep);
			}
		}
		//Catch if the SQL does not work
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		//Print the total time that it took between the start and stop
		System.out.println("");
		System.out.println("Total Time: " + (stop - start) + " Milliseconds");
		
		//Try to close out of the result set
		try {
			rs.close();
		}
		//Catch if the closing does not work
		catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
