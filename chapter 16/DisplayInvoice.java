//************************************************
// NOT FINISHED!!!! SCREWED UP!!!!               *
//************************************************

import java.sql.*;   		// Needed for JDBC classes
import java.util.Scanner;	// Needed for the Scanner class

public class DisplayInvoice
{
   public static void main(String[] args)
   {
      // Create constants for the driver name and URL.
      // NOTE: These values are specific for Cloudscape.
      final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
      final String DB_URL = "jdbc:derby:CoffeeDB";
		
		String invoiceNumber;	// To hold an invoice number
      
      try
      {
         // Load the JDBC driver.
         Class.forName(DRIVER);

         // Create a connection to the database.
         Connection conn =
                DriverManager.getConnection(DB_URL);
         
         // Get a Statement object.
         Statement stmt = conn.createStatement();
			
			// Create a Scanner object for keyboard input.
			Scanner keyboard = new Scanner(System.in);
			
			// Get an invoice number from the user.
			System.out.print("Enter an invoice number: ");
			invoiceNumber = keyboard.nextLine();
			
         // Make an SQL statement to retrieve the data.
         String sql = "SELECT * FROM Invoice, Customer " +
			             " WHERE Invoice.InvoiceNumber = '" + invoiceNumber +
							 "' AND Invoice.CustomerNumber = Customer.CustomerNumber";

         // Execute the statement.
         ResultSet result = stmt.executeQuery(sql);
			
			// Display the invoice data.
			while(result.next())
			{
				System.out.println("Invoice Number: " + result.getString("InvoiceNumber"));
				System.out.println("Invoice Date: " + result.getString("InvoiceDate"));
				System.out.println("Customer Number: " + result.getString("CustomerNumber"));
				System.out.println("Customer Name: " + result.getString("Name"));
				System.out.println("Customer Address: " + result.getString("Address"));
				System.out.println("Customer City: " + result.getString("City"));
				System.out.println("Customer State: " + result.getString("State"));
				System.out.println("Customer ZIP: " + result.getString("Zip"));
			}
         
         // Make an SQL statement to retrieve InvoiceItem data.
         String sql = "SELECT * FROM Invoiceitem, Coffee " +
			             " WHERE InvoiceItem.InvoiceNumber = '" + invoiceNumber +
							 "' AND Coffee.ProductNumber = InvoiceItem. ";

         // Execute the statement.
         ResultSet result = stmt.executeQuery(sql);
			
			System.out.println("QTY\tITEM #\tDESC\tPRICE\tCOST");
			// Display the invoice data.
			while(result.next())
			{
				System.out.println(result.getDouble("Quantity"));
				System.out.println(result.getString("ProductNumber"));
				System.out.println(result.getString("Description"));
				System.out.println("Customer Name: " + result.getString("Name"));
				System.out.println("Customer Address: " + result.getString("Address"));
				System.out.println("Customer City: " + result.getString("City"));
				System.out.println("Customer State: " + result.getString("State"));
				System.out.println("Customer ZIP: " + result.getString("Zip"));
			}


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