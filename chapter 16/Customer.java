//**************************************************
// The Customer class performs operations on the   *
// Customer table in the CoffeeDB database.        *
//**************************************************

import java.sql.*;

public class Customer
{
   // Constants for database connectivity
   public final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
   public final String DB_URL = "jdbc:derby:CoffeeDB";
   
   // Field for the database connection
   private Connection conn;
	
	//*******************************************
	// Constructor                              *
	//*******************************************
	
	public Customer()
	{
		try
		{
			// Load the Cloudscape driver.
			Class.forName(DRIVER);
		}
      catch (Exception ex)
      {
         ex.printStackTrace();
         System.exit(0);
      }
	}
	
	//************************************************
	// The getCustomerNames method returns an array  *
	// of Strings containing all the customer names. *
	//************************************************
	
	public String[] getCustomerNames() throws SQLException
	{
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
					
		// Create a Statement object for the query.
		Statement stmt =
		   conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			                     ResultSet.CONCUR_READ_ONLY);
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT Name FROM Customer");
					
		// Get the number of rows
		resultSet.last();				       // Move to the last row
		int numRows = resultSet.getRow(); // Get the current row number
		resultSet.first();                // Move back to the first row

		// Create an array for the customer names.
		String[] listData = new String[numRows];
		
		// Populate the array with customer names.
		for (int index = 0; index < numRows; index++)
		{
			// Store the customer name in the array.
			listData[index] = resultSet.getString(1);

			// Go to the next row in the result set.
			resultSet.next();
		}
		
		// Close the connection and statement objects.
		conn.close();
		stmt.close();

		// Return the listData array.
		return listData;
	}

	//************************************************
	// The getCustomerNum method returns a specific  *
	// customer's number.                            *
	//************************************************
	
	public String getCustomerNum(String name) throws SQLException
	{
		String customerNumber = "";
		
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
					
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT CustomerNumber " +
								  "FROM Customer " +
								  "WHERE Name = '" + name + "'");
					
		if (resultSet.next())
			customerNumber = resultSet.getString(1);
	
		// Close the connection and statement objects.
		conn.close();
		stmt.close();

		// Return the customer number.
		return customerNumber;
	}
}