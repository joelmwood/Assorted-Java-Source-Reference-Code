//***************************************************
// The UnpaidOrder class performs operations on the *
// UnpaidOrder table in the CoffeeDB database.      *
//***************************************************

import java.sql.*;
import java.text.DecimalFormat;

public class UnpaidOrder
{
   // Constants for database connectivity
   public final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
   public final String DB_URL = "jdbc:derby:CoffeeDB";
   
   // Field for the database connection
   private Connection conn;
   
   //*******************************************
   // Constructor                              *
   //*******************************************
   
   public UnpaidOrder()
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
   
   //**************************************************
   // The submitOrder method submits an order to      *
   // the UnpaidOrder table in the CoffeeDB database. *
   //**************************************************
   
   public void submitOrder(String custName, String coffeeName,
                           int quantity, String orderDate)
                           throws SQLException
   {
      // Create a Customer object and get the customer's number.
      Customer cust = new Customer();
      String customerNumber = cust.getCustomerNum(custName);
      
      // Create a Coffee object and get the coffee's product number.
      Coffee coffee = new Coffee();
      String prodNum = coffee.getProdNum(coffeeName);
      
      // Get the coffee price per pound.
      double price = coffee.getPrice(prodNum);
      
      // Calculate the cost of the order.
      double cost = quantity * price;

      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
               
      // Create a Statement object for the query.
      Statement stmt = conn.createStatement();           

      // Execute the query.
      stmt.executeUpdate("INSERT INTO UnpaidOrder VALUES('" +
                         customerNumber + "', '" +
                         prodNum + "', '" + orderDate + "', " +
                         quantity + ", " + cost + ")");
               
      // Close the connection and statement objects.
      conn.close();
      stmt.close();
   }

   //************************************************
   // The getOrders method returns an array of      *
   // of Strings containing information about each  *
   // unpaid order.                                 *
   //************************************************
   
   public String[] getOrders() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
               
      // Create a Statement object for the query.
      Statement stmt =
         conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                              ResultSet.CONCUR_READ_ONLY);
            
      // Get all of the rows from the table.
      ResultSet resultSet = stmt.executeQuery(
                          "SELECT " +
                          "   Customer.Name, " +
                          "   UnpaidOrder.OrderDate, " +
                          "   UnpaidOrder.Cost " +
                          "FROM " +
                          "   Customer, UnpaidOrder " +
                          "WHERE " +
                          "   UnpaidOrder.CustomerNumber = " +
								  "   Customer.CustomerNumber");

               
      // Get the number of rows
      resultSet.last();                 // Move to the last row
      int numRows = resultSet.getRow(); // Get the current row number
      resultSet.first();                // Move back to the first row

      // Create an array for the information. The size of the
      // array is 2 larger than the number of rows so we can
      // have two heading lines
      String[] listData = new String[numRows + 2];
		
		// Create a DecimalFormat object to format numbers.
		DecimalFormat fmt = new DecimalFormat("#,##0.00");
      
      // Store the headings in the first element.
      listData[0] = "Customer                  " +
                    "Date        Cost\n";
      // Store a line of dashes in the second element.
      listData[1] = "--------------------------" +
                    "------------------\n";
      
      // Populate the array with order information.
      for (int index = 2; index < listData.length; index++)
      {
         // Get the customer number.
         listData[index] = resultSet.getString("Name") +
			          " " +
 					    resultSet.getString("OrderDate") +
						 " $" +
						 fmt.format(resultSet.getDouble("Cost")) +
						 "\n";
 
         // Go to the next row in the result set.
         resultSet.next();
      }
      
      // Close the connection and statement objects.
      conn.close();
      stmt.close();

      // Return the listData array.
      return listData;
   }
}