import java.io.*;
import java.sql.*;
import java.util.*;

public class BuildCoffeeDB2
{
	public static void main(String[] args) throws Exception
	{
		final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		final String DB_URL = "jdbc:derby:CoffeeDB;create=true";
		
		// Load the Cloudscape driver.
		Class.forName(DRIVER);
		
		// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		
		makeCoffeeTable(conn);
		makeCustomerTable(conn);
		makeOrderTable(conn);
		
		conn.close();
		System.out.println("Done");
	}

	private static void makeCoffeeTable(Connection conn) throws SQLException,
	                                                     IOException
	{
		// Open the text file.
		BufferedReader inFile = new BufferedReader(new FileReader("CoffeeData_18_ONLY.csv"));

		// Create the CoffeeTable table.
		System.out.println("Creating the Coffee table...");
		
		// Create a Statement object.
		Statement stmt = conn.createStatement();

		stmt.execute("create table Coffee ("    +
		             "Description char(25), "   +
		             "ProductNumber char(10) not null primary key, " +
						 "Price double)");
		
		// Process the text file....
		String input = inFile.readLine();
		while (input != null)
		{
			// Get the tokens.
			StringTokenizer tok = new StringTokenizer(input, ",");
			String desc = tok.nextToken();
			String prodNum = tok.nextToken();
			double price = Double.parseDouble(tok.nextToken());
			
			System.out.println("Adding " + desc + ", " + prodNum + ", " + price);
			
			// Store the record in the database.
			stmt.execute("insert into Coffee values ('" +
			             desc + "', '"+
							 prodNum + "', " +
							 price + ")");
			
			// Read the next record from the text file.
			input = inFile.readLine();
		}
		
		inFile.close();
		stmt.close();
	}

	private static void makeCustomerTable(Connection conn) throws SQLException
	{
      // Get a Statement object.
      Statement stmt = conn.createStatement();
         
      // Make an SQL statement to create the table.
      String sql = "CREATE TABLE Customer" +
            "( CustomerNumber CHAR(10) NOT NULL PRIMARY KEY, " +
            "  Name CHAR(25),"    +
            "  Address CHAR(25)," +
            "  City CHAR(12),"    +
            "  State CHAR(2),"    +
            "  Zip CHAR(5) )";

      // Execute the statement.
      stmt.execute(sql);
         
      // Add some rows to the new table.
      sql = "INSERT INTO Customer VALUES" +
               "('101', 'Downtown Cafe', '17 N. Main Street'," +
               " 'Asheville', 'NC', '55515')";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO Customer VALUES" +
               "('102', 'Main Street Grocery'," +
					" '110 E. Main Street'," +
               " 'Canton', 'NC', '55555')";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO Customer VALUES" +
            "('103', 'The Coffee Place', '101 Center Plaza'," +
            " 'Waynesville', 'NC', '55516')";
      stmt.executeUpdate(sql);
		stmt.close();
	}
	
	private static void makeOrderTable(Connection conn) throws SQLException
	{
         // Get a Statement object.
         Statement stmt = conn.createStatement();
			
         // Make an SQL statement to create the table.
         String sql = "CREATE TABLE UnpaidOrder" +
                      " ( CustomerNumber CHAR(10) NOT NULL " +
							 "       REFERENCES Customer(CustomerNumber), " +
                      "   ProductNumber CHAR(10) NOT NULL " +
							 "       REFERENCES Coffee(ProductNumber), " +
							 "   OrderDate CHAR(10), " +
                      "   Quantity DOUBLE, " +
                      "   Cost DOUBLE) ";

         // Execute the statement.
         stmt.execute(sql);
         
         // Add some rows to the new table.
         sql = "INSERT INTO UnpaidOrder VALUES" +
               "('102', '19-001', '3/12/2006', 10, 96.5)";
         stmt.executeUpdate(sql);
			
         sql = "INSERT INTO UnpaidOrder VALUES" +
               "('102', '14-001', '3/12/2006', 10, 89.5)";					
         stmt.executeUpdate(sql);
			
         sql = "INSERT INTO UnpaidOrder VALUES" +
               "('103', '14-002', '3/14/2006', 5, 44.75)";					
         stmt.executeUpdate(sql);

         sql = "INSERT INTO UnpaidOrder VALUES" +
               "('101', '17-001', '3/15/2006', 20, 159.0)";					
         stmt.executeUpdate(sql);
			
         sql = "INSERT INTO UnpaidOrder VALUES" +
               "('101', '18-001', '3/11/2006', 10, 184.5)";					
         stmt.executeUpdate(sql);

		stmt.close();
	}
}