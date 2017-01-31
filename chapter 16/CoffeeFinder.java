import java.sql.*;         	  // For JDBC classes
import javax.swing.JOptionPane; // For JOptionPane

public class CoffeeFinder
{
	// Constants
	public final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public final String DB_URL = "jdbc:derby:CoffeeDB";
	
	// Field for the database connection
	private Connection conn;

	//******************************************************
	// Constructor                                         *
	//******************************************************
	
	public CoffeeFinder() throws ClassNotFoundException,
	                             SQLException
	{
		// Load the Cloudscape driver.
		Class.forName(DRIVER);

		// Create a connection to the database.
		conn = DriverManager.getConnection(DB_URL);
	}
	
	String findByProdNum(String prodNum)
	{
		String query = "SELECT * FROM Coffee " +
		               "WHERE ProductNumber LIKE '%" +
							prodNum + "%'";
							
		String rowData = "";
		
		int rowCount = 0;
		
		try
		{
			// Create a Statement object.
			Statement stmt = conn.createStatement();
					
			// Execute the query.
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next())
			{
				// Concatenate the next row to the rowData string.
				rowData += result.getString(1) +
				           result.getString(2) +
							  result.getDouble(3) +
							  "\n";
							  
				// Update the record count.
				rowCount++;
			}
			
			rowData += "---------------------\n" +
			           rowCount + " row(s) found.";
			
			// Close the statement.
			stmt.close();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Database Error: " +
			                              e.getMessage());
		}
		
		// Return the rowData string.
		return rowData;
	}
	
	public void close()
	{
		try
		{
			// Close the database connection.
			conn.close();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Database Error: " +
			                              e.getMessage());
		}
	}
}