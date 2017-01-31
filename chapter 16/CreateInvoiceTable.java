//************************************************
// This program demonstrates creates the Invoice *
// table in the CoffeeDB database.               *
//************************************************

import java.sql.*;   // Needed for JDBC classes

public class CreateInvoiceTable
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
         String sql = "CREATE TABLE Invoice" +
            "( InvoiceNumber CHAR(10) NOT NULL PRIMARY KEY, " +
            "  CustomerNumber CHAR(10) NOT NULL " +
				"        REFERENCES Customer(CustomerNumber),"    +
            "  InvoiceDate CHAR(10), " +
				"  TotalCost DOUBLE)";

         // Execute the statement.
         stmt.execute(sql);
         
         // Add some rows to the new table.
         sql = "INSERT INTO Invoice VALUES" +
               "('51213', '102', '3/15/2006', 295.50)";
         stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Invoice VALUES" +
               "('51214', '101', '3/17/2006', 0)";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Invoice VALUES" +
               "('51215', '103', '3/20/2006', 0)";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Invoice VALUES" +
               "('51216', '102', '3/21/2006', 0)";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Invoice VALUES" +
               "('51217', '102', '3/22/2006', 0)";
         stmt.executeUpdate(sql);
			
         // Close the connection.
         conn.close();
      }
      catch (Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
      }
   }
}