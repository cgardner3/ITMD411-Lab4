/*
 * Cheryl Gardner 
 * 11/24/2020
 * Lab 04-411
 * Purpose: Write a program that creates a table, updates 
 * and then writes a select statement that is used to select 
 * all of the data to the table
 */

//Import all of the necessary packages
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

//Make a new public class named Dao
public class Dao {

	//Declare DB objects that will be used in the program 
	DBConnect conn = null;
	Statement stmt = null;

	//Create a new constructor called Dao
	public Dao() { 
		
		//Create a new connectivity instance
		conn = new DBConnect();
	}
	
	//Write a new method called createTable used to create a new table
	public void createTable() {
		try {
			//Open a connection and tell the user that they connected successfully
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");
			
			//Execute the create query in SQL to create a table 
			System.out.println("Creating table in given database...");
			stmt = conn.connect().createStatement();
			
			//Write the SQL statement used to create the table
			String sql = "CREATE TABLE C_GARD_tab1 "+
						"(pid INTEGER not NULL AUTO_INCREMENT, " +
						" id VARCHAR(10), " +
						" income numeric(8,2), " +
						" pep VARCHAR(4), " + 
						"PRIMARY KEY (pid))";
			
			//Execute the sql statement to update the changes
			stmt.executeUpdate(sql);
			//Tell the user that the table was created
			System.out.println("Created table in given database...");
			//Close the DB connection
			conn.connect().close();	
		}
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
	}
	
	//Write a new method used to insert all of the records into the table
	public void insertRecords(BankRecords[] robjs) {
		
		try {
			//Let the user know that they connected successfully and are trying to insert
			System.out.println("Connecting to a selected database to insert...");
			System.out.println("Connected database successfully...");
			//Start to create a new statement and declare the local variable
			stmt = conn.connect().createStatement();
			String sql = null;
			
			//Tell the user that they are inserting the records into the table
			System.out.println("Inserting records into the table...");
			
			//Include all object data into the database table
			for (int i = 0; i < robjs.length; ++i) {
			  //Insert id, income and pep into the database table with all of the values using an SQL statement
			  sql = "INSERT INTO C_GARD_tab1(id,income,pep) " + "VALUES (' "+robjs[i].getterId()+" ', ' "+robjs[i].getterIncome()+" ', ' "+robjs[i].getterPep()+" ' )";
			  //Execute the sql statements
			  stmt.executeUpdate(sql);
			}
			//Tell the user that the table was created and then close it out
			System.out.println("Created table in given database...");
			conn.connect().close();
		}
		//Catch if the try block does not work
		catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	//Declare a new method called Result set that will retrieve the Records
	public ResultSet retrieveRecords() {
		//Declare a new local variable
		ResultSet rs = null;
		//Tell the user what they are doing
		System.out.println("Connecting to a selected database for record retrievals...");
		//Try to connect and create a statement and tell the user they successfully connected
		try {
			stmt = conn.connect().createStatement();
			System.out.println("Connected to database successfully...");
		}
		//Throw an exception if the try block is not caught
		catch (SQLException e) {
			e.printStackTrace();
		}
		//Write a new sql statement for a select statement
		String sql = "SELECT id,income,pep FROM C_GARD_tab1 ORDER BY pep DESC";
		//Try to execute the SQL query and tell the user they are creating a select statement
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("Creating Select statement");
		}
		//Throw a catch if the try block does not work
		catch (SQLException e) {
			e.printStackTrace();
		}
		//Try to close the connection
	    try {
	    	conn.connect().close();
	    }
	    //If it does not work throw an exception
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    //Return the result set
		return rs;
	}
}
