import java.sql.*;
import javax.swing.*;

public class CoffeeList extends JList
{
	public CoffeeList(Connection conn) throws SQLException
	{
		int numRows;					// Number of rows
		String[] listData;			// Customer names
		
		// Create a Statement object for the query.
		Statement stmt =
		   conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			                     ResultSet.CONCUR_READ_ONLY);
				
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
                         "SELECT Description FROM Coffee");
					
		// Get the number of rows
		resultSet.last();				   // Move to the last row
		numRows = resultSet.getRow(); // Get the current row number
		resultSet.first();            // Move back to the first row

		// Create an array for the coffee descriptions.
		listData = new String[numRows];
		
		// Populate the array with coffee descriptions.
		for (int index = 0; index < numRows; index++)
		{			
			// Store the coffee descrption in the array.
			listData[index] = resultSet.getString(1);

			// Go to the next row in the result set.
			resultSet.next();
		}
		
		// Store the coffee descriptions as the JList list data.
		setListData(listData);
	}
}