// TONY!!!! We're not using this any longer. Use CustomerPanel instead!!!
import java.sql.*;
import javax.swing.*;

public class CustomerList extends JList
{
	public CustomerList(Connection conn) throws SQLException
	{
		int numRows;					// Number of rows
		String[] listData;			// Customer names
		
		// Create a Statement object for the query.
		Statement stmt =
		   conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			                     ResultSet.CONCUR_READ_ONLY);
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		                    "SELECT Name FROM CUSTOMER");
					
		// Get the number of rows
		resultSet.last();				   // Move to the last row
		numRows = resultSet.getRow(); // Get the current row number
		resultSet.first();            // Move back to the first row

		// Create an array for the customer names.
		listData = new String[numRows];
		
		// Populate the array with customer names.
		for (int index = 0; index < numRows; index++)
		{
			// Store the customer name in the array.
			listData[index] = resultSet.getString(1);
			System.out.println(listData[index]);

			// Go to the next row in the result set.
			resultSet.next();
		}
		
		// Store the customer names as the JList list data.
		setListData(listData);
	}
}