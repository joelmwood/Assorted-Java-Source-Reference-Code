//************************************************
// This program demonstrates creates the         *
// InvoiceItem table in the CoffeeDB database.   *
//************************************************

import java.sql.*;   // Needed for JDBC classes

public class CreateInvoiceItemTable
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
         String sql = "CREATE TABLE InvoiceItem" +
                      " ( InvoiceNumber CHAR(10) NOT NULL " +
							 "       REFERENCES Invoice(InvoiceNumber), " +
                      "   ProductNumber CHAR(10) NOT NULL " +
							 "       REFERENCES Coffee(ProductNumber), " +
                      "   Quantity DOUBLE, " +
                      "   Cost DOUBLE )";

         // Execute the statement.
         stmt.execute(sql);
         
         // Add some rows to the new table.
         sql = "INSERT INTO InvoiceItem VALUES" +
               "('51213', '19-001', 10, 96.5)";
         stmt.executeUpdate(sql);
			
         sql = "INSERT INTO InvoiceItem VALUES" +
               "('51213', '21-003', 20, 199.5)";
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