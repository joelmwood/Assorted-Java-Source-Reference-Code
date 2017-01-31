
import javax.swing.*;      // For Swing classes
import java.sql.*;         // For JDBC classes
import java.awt.*;			// For layout manager classes
import java.awt.event.*;   // For ActionListener interface

public class FindCoffeeByNum extends JFrame
{
	// Components
   private JPanel buttonPanel;      // A panel for the buttons
   private JPanel inputPanel;       // A panel for user input
   private JTextField prodNumField; // Product number input
	private JTextArea searchResults; // Results of the search
   private JButton submitButton;    // Submit button
   private JButton exitButton;      // Exit button
	
	private CoffeeFinder coffeeFinder;
	
	// Constants
	private final int NUM_ROWS = 12;	// Rows in the text area
	private final int NUM_COLS = 50; // Columns in the text area
	
	//**********************************************
	// Constructor                                 *
	//**********************************************
	
	public FindCoffeeByNum()
	{
		// Display a title.
		super("Find Coffee by Product Number");
		
		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Build the input panel.
		buildInputPanel();
		
		// Build the button panel.
		buildButtonPanel();
		
		// Create a text area to display search results.
		searchResults = new JTextArea(NUM_ROWS, NUM_COLS);
		searchResults.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(searchResults);
		
		// Add the text area to the content pane.
		add(scrollPane, BorderLayout.CENTER);
		
		// Add the inputPanel to the content pane.
		add(inputPanel, BorderLayout.NORTH);

		// Add the buttonPanel to the content pane.
		add(buttonPanel, BorderLayout.SOUTH);
		
		// Pack and display.
		pack();
		setVisible(true);
		
		try
		{
			coffeeFinder = new CoffeeFinder();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: " +
			                              e.getMessage());
			System.exit(0);
		}
	}
	
	//*******************************************************
	// buildInputPanel method                               *
	//*******************************************************
	
	private void buildInputPanel()
	{
		// Create a panel for the input components.
		inputPanel = new JPanel();
		
		// Create a message to prompt the user.
		JLabel message = new JLabel("Enter a product number");
		
		// Create a text field for input.
		prodNumField = new JTextField(10);
		
		// Add the label and text field to the panel.
		inputPanel.add(message);
		inputPanel.add(prodNumField);
	}

	//*******************************************************
	// buildButtonPanel method                              *
	//*******************************************************
	
	private void buildButtonPanel()
	{
		// Create a panel for the buttons.
		buttonPanel = new JPanel();
		
		// Create a Submit button.
		submitButton = new JButton("Submit");
		
		// Create an Exit button.
		exitButton = new JButton("Exit");
		
		// Register an action listener for th Submit button.
		submitButton.addActionListener(new SubmitButtonListener());

		// Register an action listener for the Exit button.
		exitButton.addActionListener(new ExitButtonListener());
		
		// Add the buttons to the panel.
		buttonPanel.add(submitButton);
		buttonPanel.add(exitButton);
	}
	
	//****************************************************
	// SubmitButtonListener inner class                 *
	//****************************************************
	
	private class SubmitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Clear the previous results.
			searchResults.setText("");
			
			String prodNum = prodNumField.getText();
			String resultString = coffeeFinder.findByProdNum(prodNum);
			searchResults.setText(resultString);
		}
	}
	
	//****************************************************
	// ExitButtonListener inner class                    *
	//****************************************************
	
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Close the database connection.
			coffeeFinder.close();
			
			// Exit the application.
			System.exit(0);
		}
	}
	
	//**************************************************
	// main method                                     *
	//**************************************************
	
	public static void main(String[] args)
	{
		FindCoffeeByNum findCoffee = new FindCoffeeByNum();
	}
}