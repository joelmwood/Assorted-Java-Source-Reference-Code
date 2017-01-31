//**************************************************
// The Coffee class performs operations on the     *
// Coffee table in the CoffeeDB database.          *
//**************************************************

import java.sql.*;

public class Coffee
{
   // Constants for database connectivity
   public final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
   public final String DB_URL = "jdbc:derby:CoffeeDB";
   
   // Field for the database connection
   private Connection conn;
	
	//*******************************************
	// Constructor                              *
	//*******************************************
	
	public Coffee()
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
	// The getCoffeeNames method returns an array    *
	// of Strings containing all the coffee names.   *
	//************************************************
	
	public String[] getCoffeeNames() throws SQLException
	{
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
					
		// Create a Statement object for the query.
		Statement stmt =
		   conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			                     ResultSet.CONCUR_READ_ONLY);
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT Description FROM Coffee");
					
		// Get the number of rows
		resultSet.last();				       // Move to the last row
		int numRows = resultSet.getRow(); // Get the current row number
		resultSet.first();                // Move back to the first row

		// Create an array for the coffee names.
		String[] listData = new String[numRows];
		
		// Populate the array with coffee names.
		for (int index = 0; index < numRows; index++)
		{
			// Store the coffee name in the array.
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
	// The getProdNum method returns a specific      *
	// coffee's product number.                      *
	//************************************************
	
	public String getProdNum(String coffeeName) throws SQLException
	{
		String prodNum = "";
		
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
					
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT ProdNum " +
								  "FROM Coffee " +
								  "WHERE Description = '" + 
								  coffeeName + "'");
					
		if (resultSet.next())
			prodNum = resultSet.getString(1);
	
		// Close the connection and statement objects.
		conn.close();
		stmt.close();

		// Return the product number.
		return prodNum;
	}

	//*****************************************************
	// The getPrice method returns the price of a coffee. *
	//*****************************************************
	
	public double getPrice(String prodNum) throws SQLException
	{
		double price = 0.0;
		
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
					
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT Price " +
								  "FROM Coffee " +
								  "WHERE ProdNum = '" + 
								  prodNum + "'");
					
		if (resultSet.next())
			price = resultSet.getDouble(1);
	
		// Close the connection and statement objects.
		conn.close();
		stmt.close();

		// Return the price.
		return price;
	}
}