//*********************************************************
// The DisplayTable class displays a populated JTable.    *
//*********************************************************

import javax.swing.*;
import java.awt.*;

public class DisplayTable extends JFrame
{
   // Constants for size.
   private final int WIDTH = 400;
   private final int HEIGHT = 200;
   
   //******************************************************
   // Constructor                                         *
   //******************************************************
   
   public DisplayTable(String[][] data, String[] colNames)
   {
      // Set the window title.
      setTitle("Query Results");
      
      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      // Create a JTable with the results.
      JTable table = new JTable(data, colNames);
      
      // Put the table in a scroll pane.
      JScrollPane scrollPane = new JScrollPane(table);

      // Add the table to the content pane.
      add(scrollPane, BorderLayout.CENTER);
      
      // Set the size and display.
      setSize(400, 200);
      setVisible(true);
   }
}