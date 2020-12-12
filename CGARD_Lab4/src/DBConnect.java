/*
 * Cheryl Gardner 
 * 11/24/2020
 * Lab 04-411
 * Purpose: To call the sql database and enter the credentials to connect 
 * to the database connection
 */

//import the necessary packages needed for the program
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Declare a new class called DB Connect
public class DBConnect {

//Add the URL for the database to be a string called DB_URL
static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/411labs?autoReconnect=true&useSSL=false";

//Add all of the database credentials to login
static final String USER = "db411", PASS = "411";

//Declare a new connection called connect that throws SQLException
public Connection connect() throws SQLException {
	
	//Return the new connection that is created
	return DriverManager.getConnection(DB_URL, USER, PASS);
}
}
