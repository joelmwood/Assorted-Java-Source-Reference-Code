import javax.swing.*;
import java.awt.Dimension;

public class Test
{
   public static void main(String[] args)
   {
      String[] colNames = {"Name", "Telephone" };
      String[][] rowData = {{ "Jean", "555-2222" },
                            { "Tim",  "555-1212" },
                            { "Matt", "555-9999" },
                            { "Rose", "555-4545" },
                            { "Geri", "555-5214" }, 
                            { "Shawn","555-7821" }, 
                            { "Renee", "555-9640" }, 
                            { "Joe",   "555-8657" } };

		JFrame frame = new JFrame();
		JTable table = new JTable(rowData, colNames);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Put the table in a scroll pane.
		JScrollPane rtScrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(200, 100));

		//rtScrollPane.setHorizontalScrollBarPolicy(
		//      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//rtScrollPane.setVerticalScrollBarPolicy(
		//      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.add(rtScrollPane);
		//frame.setSize(300, 100);
		frame.pack();
		frame.setVisible(true);
   }
}