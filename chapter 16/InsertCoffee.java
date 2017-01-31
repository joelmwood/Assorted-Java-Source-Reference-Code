
import javax.swing.*;      // For Swing classes
import java.sql.*;         // For JDBC classes
import java.awt.*;			// For layout manager classes
import java.awt.event.*;   // For ActionListener interface

public class InsertCoffee extends JFrame
{
	// Components
	private JTextField descField;    // Description
   private JTextField prodNumField; // Product number input
	private JTextField priceField;   // Price
	
	private CoffeeInserter coffeeInserter;
	
	//**********************************************
	// Constructor                                 *
	//**********************************************
	
	public InsertCoffee()
	{
		// Display a title.
		super("Insert a Coffee Record");
		
		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Build the input panel.
		buildInputPanel();
		
		// Build the button panel.
		buildButtonPanel();
		
		// Pack and display.
		pack();
		setVisible(true);
		
		try
		{
			coffeeInserter = new CoffeeInserter();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: " +
			                              e.getMessage());
			System.exit(0);
		}
	}
	
	//*******************************************************
	// buildInputPanels method                               *
	//*******************************************************
	
	private void buildInputPanel()
	{
		// Create a set of panels for the input components.
		JPanel descPanel = new JPanel();
		JPanel prodNumPanel = new JPanel();
		JPanel pricePanel = new JPanel();
		
		// Create prompts for the column values.
		JLabel descPrompt = new JLabel("Description");
		JLabel prodNumPrompt = new JLabel("Product number");
		JLabel pricePrompt = new JLabel("Price");
		
		// Create text fields for the column values.
		descField = new JTextField(25);
		prodNumField = new JTextField(10);
		priceField = new JTextField(6);
		
		// Build the description panel.
		descPanel.add(descPrompt);
		descPanel.add(descField);

		// Build the product number panel.
		prodNumPanel.add(prodNumPrompt);
		prodNumPanel.add(prodNumField);

		// Build the price panel.
		pricePanel.add(pricePrompt);
		pricePanel.add(priceField);
		
		// Create a panel to hold all the other panels.
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3, 1));
		inputPanel.add(descPanel);
		inputPanel.add(prodNumPanel);
		inputPanel.add(pricePanel);
		
		// Add the input panel to the content pane.
		add(inputPanel, BorderLayout.CENTER);
	}

	//*******************************************************
	// buildButtonPanel method                              *
	//*******************************************************
	
	private void buildButtonPanel()
	{
		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		
		// Create a Submit button.
		JButton submitButton = new JButton("Submit");
		
		// Create an Exit button.
		JButton exitButton = new JButton("Exit");
		
		// Register an action listener for th Submit button.
		submitButton.addActionListener(new SubmitButtonListener());

		// Register an action listener for the Exit button.
		exitButton.addActionListener(new ExitButtonListener());
		
		// Add the buttons to the panel.
		buttonPanel.add(submitButton);
		buttonPanel.add(exitButton);
		
		// Add the buttonPanel to the content pane.
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	//****************************************************
	// SubmitButtonListener inner class                 *
	//****************************************************
	
	private class SubmitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String desc = descField.getText();
			String prodNum = prodNumField.getText();
			double price = Double.parseDouble(priceField.getText());
			try
			{
				coffeeInserter.insert(desc, prodNum, price);
			}
			catch (SQLException ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			// Clear the text fields.
			descField.setText("");
			prodNumField.setText("");
			priceField.setText("");
		}
	}
	
	//****************************************************
	// ExitButtonListener inner class                    *
	//****************************************************
	
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				// Close the database connection.
				coffeeInserter.close();
			}
			catch (SQLException ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			// Exit the application.
			System.exit(0);
		}
	}
	
	//**************************************************
	// main method                                     *
	//**************************************************
	
	public static void main(String[] args)
	{
		InsertCoffee insertCoffee = new InsertCoffee();
	}
}