import java.io.*;
import java.sql.*;
import java.util.*;

public class BuildCoffeeDB
{
	public static void main(String[] args) throws Exception
	{
		final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		final String DB_URL = "jdbc:derby:CoffeeDB;create=true";
		
		int numRows;	// Number of rows affected by a statement
		ResultSet result;
		String input;
		
		// Open the text file.
		BufferedReader inFile = new BufferedReader(new FileReader("CoffeeData_18_ONLY.csv"));

		// Load the Cloudscape driver.
		Class.forName(DRIVER);
		
		// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		// Drop the existing Coffee table.
		stmt.execute("drop table coffee");
		
		// Create the CoffeeTable table.
		System.out.println("Creating the Coffee table...");
		stmt.execute("create table Coffee ("    +
		             "Description char(25), "   +
		             "ProdNum char(10) not null primary key, " +
						 "Price double)");
		
		// Process the text file....
		input = inFile.readLine();
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
		conn.commit();
		conn.close();
		System.out.println("Done");
	}
	
}