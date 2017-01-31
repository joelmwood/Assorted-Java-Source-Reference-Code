//***********************************************
// This program demonstrates creates a Customer *
// table in the CoffeeDB database.              *
//***********************************************

import java.sql.*;   // Needed for JDBC classes

public class ResultSetTester
{
   public static void main(String[] args)
   {
      // Create constants for the driver name and URL.
      // NOTE: These values are specific for Cloudscape.
      final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
      final String DB_URL = "jdbc:derby:CoffeeDB";
      
      try
      {
         // Load the JDBC driver.
         Class.forName(DRIVER);

         // Create a connection to the database.
         Connection conn =
                DriverManager.getConnection(DB_URL);
         
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
         // Make an SQL statement to create the table.
         String sql = "SELECT * FROM Coffee";

         // Execute the statement.
         ResultSet resultSet = stmt.executeQuery(sql);
         
			// Get a meta data object for the result set.
			ResultSetMetaData meta = resultSet.getMetaData();

			for (int i = 1; i <= meta.getColumnCount(); i++)
			{
				String colName = meta.getColumnLabel(i);
				int displaySize = meta.getColumnDisplaySize(i);
				
				if (colName.length() > displaySize)
					displaySize = colName.length();
				
				// Get the next column name.
				System.out.print(colName);
				
				// Pad with spaces.
				for (int x = 0; x <= (displaySize - colName.length()); x++)
					System.out.print(" ");
			}
			
			System.out.println();
			
			// Display the column data.
			while (resultSet.next())
			{
				for (int col = 1; col <= meta.getColumnCount(); col++)
				{
					System.out.print(resultSet.getString(col) + " ");
				}
				System.out.println();
			}

         // Close the connection.
         conn.close();
      }
      catch (Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
   }
}