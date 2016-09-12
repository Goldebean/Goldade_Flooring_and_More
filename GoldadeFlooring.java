import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/****************************************************************************** 
Program Name: Goldade Flooring & More.java 
Programmer's Name: Heather Goldade 
Program Description: This program is designed for the user to input the
requested flooring type, and area size to be ordered for the customer. 
Once the floor type and area in filled in, the total cost will be displayed
and the customer's information can be filled in. Once the information is
submitted, the program will save the order to the MySQL database. The
user can also view all the saved orders and display them in the last panel.
*******************************************************************************/


public class GoldadeFlooring extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	//create the components for the GUI and declare variables
	JLabel lblFloorType1 = new JLabel("Flooring Type:");
	JLabel lblFloorType2 = new JLabel("Flooring Type:");
	JLabel lblFloorDimensions = new JLabel("Flooring Dimensions:");
	JLabel lblWidth = new JLabel("Width");
	JLabel lblLength = new JLabel("Length");
	JLabel lblSquareFoot1 = new JLabel("  square foot");
	JLabel lblSquareFoot2 = new JLabel("square foot");
	JLabel lblCustomerInfo = new JLabel("Enter the Customer Information:");
	JLabel lblFirstName = new JLabel("First Name:");
	JLabel lblLastName = new JLabel("Last Name:");
	JLabel lblStreetAddress = new JLabel("Street Address:");
	JLabel lblCity = new JLabel("City:");
	JLabel lblState = new JLabel("State:");
	JLabel lblZipCode = new JLabel("Zip Code:");
	JLabel lblSummary = new JLabel("Order Summary:");
	JLabel lblCustomerName = new JLabel("Customer Name:");
	JLabel lblCustomerAddress = new JLabel("Customer Address:");
	JLabel lblCost = new JLabel("Total Cost:");
	JLabel lblCustomerOrders = new JLabel("Customer Orders:");
	
	JRadioButton radioWood = new JRadioButton("Wood", false);
	JRadioButton radioCarpet = new JRadioButton("Carpet", false);
	
	JButton btnCalculateArea = new JButton("Calculate");
	JButton btnCalculateCost = new JButton("    Total    ");
	JButton btnSubmit = new JButton("Submit");
	JButton btnSummary = new JButton("Order Summary");
	JButton btnOrders = new JButton("View Orders");
	
	JTextField txtFirstName = new JTextField("", 10);
	JTextField txtLastName = new JTextField("", 10);
	JTextField txtCustomerName = new JTextField(20);
	JTextField txtStreetAddress = new JTextField("", 20);
	JTextField txtCustomerAddress = new JTextField("", 30);
	JTextField txtCity = new JTextField("", 13);
	JTextField txtState = new JTextField("", 2);
	JTextField txtZipCode = new JTextField("", 5);
	JTextField txtWidth = new JTextField("", 10);
	JTextField txtLength = new JTextField("", 10);
	JTextField txtArea1 = new JTextField(10);
	JTextField txtArea2 = new JTextField(10);
	JTextField txtCost1 = new JTextField("0.00", 10);
	JTextField txtCost2 = new JTextField(10);
	JTextField txtFloorType = new JTextField("", 10);
	
	static JTextArea areaOrders = new JTextArea("", 40, 40);
	
	DecimalFormat dFormat = new DecimalFormat( "0.00" );
	PreparedStatement sqlQuery;
	Statement sqlSelect;
	ResultSet results;
	String sqlInsert = "INSERT into CustomerOrders(customerName, customerAddress, flooringType, floorArea, floorCost)" + " VALUES(?, ?, ?, ?, ?)";
	final String DATABASE_URL = "jdbc:mysql://Devry.edupe.net:4300/CIS355_3204";
	Connection connection = null;
    
	public GoldadeFlooring()
	{
		super("Goldade Flooring & More");
		JTabbedPane tab = new JTabbedPane();
		getContentPane().setBackground(Color.gray);
		GridBagConstraints con = new  GridBagConstraints();
		
		EventHandler handler = new EventHandler();
		
		JPanel pnlFlooringSelection = new JPanel(); // construct panel 1 - Flooring Selection	
		pnlFlooringSelection.setLayout(new GridBagLayout());
		
		con.insets = new Insets(0,10,10,0);
		
		con.anchor = GridBagConstraints.FIRST_LINE_START;
		pnlFlooringSelection.add(lblFloorType1, con);
		
		con.gridx = 1;
		con.gridy = 1;
		con.anchor = GridBagConstraints.CENTER;
		pnlFlooringSelection.add(radioWood, con);
		
		con.gridx = 1;
		con.gridy = 2;
		con.anchor = GridBagConstraints.CENTER;
		pnlFlooringSelection.add(radioCarpet, con);

		con.gridx = 0;
		con.gridy = 3;
		con.anchor = GridBagConstraints.LINE_START;
		pnlFlooringSelection.add(lblFloorDimensions, con);
		
		con.gridx = 1;
		con.gridy = 4;
		con.anchor = GridBagConstraints.CENTER;
		pnlFlooringSelection.add(lblWidth, con);
		
		con.gridx = 2;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LINE_END;
		pnlFlooringSelection.add(txtWidth, con);
		
		con.gridx = 1;
		con.gridy = 5;
		con.anchor = GridBagConstraints.CENTER;
		pnlFlooringSelection.add(lblLength, con);
		
		con.gridx = 2;
		con.gridy = 5;
		con.anchor = GridBagConstraints.LINE_END;
		pnlFlooringSelection.add(txtLength, con);
		
		con.gridx = 1;
		con.gridy = 7;
		con.anchor = GridBagConstraints.LINE_END;
		pnlFlooringSelection.add(btnCalculateArea, con);
		
		con.gridx = 2;
		con.gridy = 7;
		con.anchor = GridBagConstraints.PAGE_END;
		pnlFlooringSelection.add(txtArea1, con);
		txtArea1.setEditable(false);
		
		con.gridx = 3;
		con.gridy = 7;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlFlooringSelection.add(lblSquareFoot1, con);
		
		con.gridx = 1;
		con.gridy = 8;
		con.anchor = GridBagConstraints.LINE_END;
		pnlFlooringSelection.add(btnCalculateCost, con);
		
		con.gridx = 2;
		con.gridy = 8;
		con.anchor = GridBagConstraints.LINE_END;
		pnlFlooringSelection.add(txtCost1, con);
		txtCost1.setEditable(false);
		getContentPane().setBackground(Color.gray);
		tab.addTab("Flooring Selection", null, pnlFlooringSelection, "floor selection");
		
		ButtonGroup radioGroup = new ButtonGroup(); // add radio buttons to group to hold the button chosen
		radioGroup.add(radioWood);
		radioGroup.add(radioCarpet);
		
		radioWood.addActionListener(handler); // register the radio button events with their variables
		radioCarpet.addActionListener(handler);
		btnCalculateArea.addActionListener(handler);
		btnCalculateCost.addActionListener(handler);
		
		
		JPanel pnlCustomerInformation = new JPanel(); // construct panel 2 - Customer Information
		pnlCustomerInformation.setLayout(new GridBagLayout());
		
		con.insets = new Insets(0,10,20,0);
		con.anchor = GridBagConstraints.FIRST_LINE_START;
		con.gridx = 0;
		con.gridy = 0;
		pnlCustomerInformation.add(lblCustomerInfo, con);
		
		con.gridx = 0;
		con.gridy = 1;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlCustomerInformation.add(lblFirstName, con);
		
		con.gridx = 1;
		con.gridy = 1;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtFirstName, con);
		
		con.gridx = 0;
		con.gridy = 2;
		con.anchor = GridBagConstraints.LINE_END;
		pnlCustomerInformation.add(lblLastName, con);
		
		con.gridx = 1;
		con.gridy = 2;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtLastName, con);
		
		con.gridx = 0;
		con.gridy = 3;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlCustomerInformation.add(lblStreetAddress, con);
		
		con.gridx = 1;
		con.gridy = 3;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtStreetAddress, con);
		
		con.gridx = 0;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlCustomerInformation.add(lblCity, con);
		
		con.gridx = 1;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtCity, con);
		
		con.gridx = 2;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlCustomerInformation.add(lblState, con);
		
		con.gridx = 3;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtState, con);
		
		con.gridx = 4;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlCustomerInformation.add(lblZipCode, con);
		
		con.gridx = 5;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlCustomerInformation.add(txtZipCode, con);
		
		con.gridx = 1;
		con.gridy = 5;
		pnlCustomerInformation.add(btnSubmit, con);
		tab.addTab("Customer Information", null, pnlCustomerInformation, "enter customer information");
		
		btnSubmit.addActionListener(handler);
		
		JPanel pnlSummary = new JPanel(); // construct panel 3 - Summary
		pnlSummary.setLayout(new GridBagLayout());
		con.insets = new Insets(0,10,20,0);
		
		con.anchor = GridBagConstraints.FIRST_LINE_START;
		con.gridx = 0;
		con.gridy = 0;
		pnlSummary.add(lblSummary, con);
		
		con.gridx = 0;
		con.gridy = 1;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlSummary.add(lblCustomerName, con);
		
		con.gridx = 1;
		con.gridy = 1;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlSummary.add(txtCustomerName, con);
		txtCustomerName.setEditable(false);
		
		con.gridx = 0;
		con.gridy = 2;
		con.anchor = GridBagConstraints.LINE_END;
		pnlSummary.add(lblCustomerAddress, con);
		
		con.gridx = 1;
		con.gridy = 2;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlSummary.add(txtCustomerAddress, con);
	    txtCustomerAddress.setEditable(false);
	    
	    con.gridx = 0;
		con.gridy = 3;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlSummary.add(lblFloorType2, con);
		
		con.gridx = 1;
		con.gridy = 3;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlSummary.add(txtFloorType, con);
		txtFloorType.setEditable(false);
		
		con.gridx = 0;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlSummary.add(lblSquareFoot2, con);
		
		con.gridx = 1;
		con.gridy = 4;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlSummary.add(txtArea2, con);
		txtArea2.setEditable(false);
		
		con.gridx = 0;
		con.gridy = 5;
		con.anchor = GridBagConstraints.LAST_LINE_END;
		pnlSummary.add(lblCost, con);
		
		con.gridx = 1;
		con.gridy = 5;
		con.anchor = GridBagConstraints.LAST_LINE_START;
		pnlSummary.add(txtCost2, con);
		txtCost2.setEditable(false);
		
		tab.addTab("Summary", null, pnlSummary, "order summary");
		
		JPanel pnlOrders = new JPanel(); // construct panel 4 - Customer Orders
		pnlOrders.setLayout(new FlowLayout());
		pnlOrders.add(lblCustomerOrders);
		pnlOrders.add(btnOrders);
		pnlOrders.add(areaOrders);
		areaOrders.setEditable(false);
		tab.addTab("Orders", null, pnlOrders, "customer orders");
		
		btnOrders.addActionListener(handler);
		
		getContentPane().add(tab);
	}	
	
		private class EventHandler implements ActionListener
		{
			double woodPrice = 20, carpetPrice = 10, floorPrice = 0, cost = 0.0, area = 0.0, width = 0.0, length = 0.0;
			String floorChoice = "none", wood = "Wood", carpet = "Carpet", firstName = "", 
				   lastName = "", streetAddress = "", city = "", state = "", customerName, customerAddress;
			int zipCode = 0;

			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(e.getSource() == radioWood || e.getSource() == radioCarpet) // panel 1 - floor selection event
					{
						if(radioWood.isSelected())
						{
							floorChoice = wood;
							floorPrice = woodPrice;
						}
						if(radioCarpet.isSelected())
						{
							floorChoice = carpet;
							floorPrice = carpetPrice;
						}
					}
					
					if(e.getSource() == btnCalculateArea)
					{
						width = Double.parseDouble(txtWidth.getText());
						length = Double.parseDouble(txtLength.getText());
						
						area = width * length;
						txtArea1.setText(String.valueOf(dFormat.format(new Double(area))));
					}
					
					if(e.getSource() == btnCalculateCost)
					{
						cost = floorPrice * area;
						txtCost1.setText(String.valueOf(dFormat.format(new Double(cost))));
						
					}
					else if(e.getSource() == btnCalculateArea && !(width >= 0 && length >= 0) )
					{
						JOptionPane.showMessageDialog(GoldadeFlooring.this,
								("Please enter a number for length and width!"), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} 	
					
					if(e.getSource() == btnSubmit) // panel 2 - event
					{
						firstName = txtFirstName.getText();
						lastName = txtLastName.getText();
						streetAddress = txtStreetAddress.getText();
						city = txtCity.getText();
						state = txtState.getText();
						zipCode = Integer.parseInt(txtZipCode.getText());
						
						customerName = firstName + " " + lastName;
						customerAddress = streetAddress + "  " + city + ", " + state + " " + zipCode;
						
						txtCustomerName.setText(customerName);
						txtCustomerAddress.setText(customerAddress);
						txtFloorType.setText(floorChoice);
						txtArea2.setText(String.valueOf(new Double(area)));
						txtCost2.setText(String.valueOf(new Double(cost)));
						
						SaveOrders(customerName, customerAddress, floorChoice, area, cost);
					}
				}
				catch(Exception event)
				{
					JOptionPane.showMessageDialog(GoldadeFlooring.this,
							("Please properly input the required Fields"), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				
				if(e.getSource() == btnOrders) // panel 4 - floor selection event
				{
					ViewOrders();
				}
			}
			
		  
		  void SaveOrders(String customerName, String customerAddress, String floorChoice, double area, double cost)
		  {
			  try 
			  {
				connection = DriverManager.getConnection(DATABASE_URL, "3204", "Nicole3712"); // connect to the URL database" String for the URL, username, password;
			    System.out.println("Connection to database established!"); // confirm that the connection was made
			        
			    sqlQuery = connection.prepareStatement(sqlInsert); //perform the INSERT query and use the connection to store it in the online database

				sqlQuery.setString(1, customerName); // input the variables in the INSERT statement. The integers assign the columns
				sqlQuery.setString(2, customerAddress);
				sqlQuery.setString(3, floorChoice);
				sqlQuery.setDouble(4, area);
				sqlQuery.setDouble(5, cost);
				sqlQuery.execute(); // execute the INSERT query 
			  } 
			  catch (SQLException e) 
			  {
				  e.printStackTrace();
			  }
		  }
		  
		  void ViewOrders()
		  {
			  String columnTitles = "Customer Name	       		Customer Address	Floor Type	Square Footage    Total Cost";

			try 
			{	
				connection = DriverManager.getConnection(DATABASE_URL, "3204", "Nicole3712"); // connect to the URL database" String for the URL, username, password;
				
			    sqlSelect = connection.createStatement(); // tell the program to use the SELECT query for the database
			    results = sqlSelect.executeQuery("SELECT * from CustomerOrders"); // return all (*) the results of the SELECT statement in the table
			    
			    areaOrders.setText(columnTitles); // create a header for the columns

			    while (results.next()) // print every row until there is no data left to print
			    {
			    	 areaOrders.append("\n" + results.getString("customerName") +"		  "+ results.getString("customerAddress") +"	    "+ results.getString("flooringType") 
			    			+"	"+ results.getDouble("floorArea") + "	  $" + results.getDouble("floorCost")); // print the rows from each column
			    }
			 } 
			 catch (SQLException event) // catch errors
			 {
				System.out.println("Connection Failed!");
				System.out.println(event.getMessage());
				return;
			 } 
			 finally 
			 {
				try
				{
					if(connection != null)
		            connection.close();
		            System.out.println("Connection closed");
				} 
				catch (SQLException event2) 
				{
					event2.printStackTrace();
				}
			 }
		   }
	}

	public static void main(String[] args) 
	{		
		GoldadeFlooring goldadeFlooring = new GoldadeFlooring();
		goldadeFlooring.setVisible(true);
		goldadeFlooring.setSize(675,350);
		goldadeFlooring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JOptionPane.showMessageDialog(null, "Welcome to Goldade Flooring & More!\nPlease select your flooring to get started.");
	}
	
}
