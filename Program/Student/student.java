import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


// Main Student Frame
class StudentFrame extends JFrame
{
	// Declaration
	private final JLabel picLabel;
	private final ImageIcon logo;
	private final JButton[] button = new JButton[5];
	
	// StudentFrame constructor to add in Picture and Button
	public StudentFrame()
	{
		super("UOW Events Booking System");
		setLayout (null);
		
		// Add in UOW Logo
		logo = new ImageIcon ("uow.jpg");
		Image img = logo.getImage();
		Image newImg = img.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		Icon pict = new ImageIcon(newImg);
		picLabel = new JLabel(pict);
		picLabel.setBounds(50, 10, 600, 200);
		add (picLabel);
		
		// Add in Account Button
		button[0] = new JButton ("Account Settings");
		button[0].setBounds(50, 240, 150, 30);
		button[0].setBackground(Color.WHITE);
		add (button[0]);
		
		// Add in Event Booking Button
		button[1] = new JButton ("Event Listing");
		button[1].setBounds(85, 300, 150, 40);
		button[1].addActionListener(new EventButtonHandler());
		add (button[1]);
		
		// Add in Cart Button
		button[2] = new JButton ("Shopping Cart");
		button[2].setBounds(285, 300, 150, 40);
		button[2].addActionListener(new CartButtonHandler());
		add (button[2]);
		
		// Add in Booked Event Button
		button[3] = new JButton ("View Booked Event");
		button[3].setBounds(485, 300, 150, 40);
		button[3].addActionListener(new BookedButtonHandler());
		add (button[3]);
		
		// Add in Log Out Button
		button[4] = new JButton ("Logout");
		button[4].setBounds(520, 240, 150, 30);
		button[4].setBackground(Color.WHITE);
		add (button[4]);
	}
	
	// Adding in ActionListener for Buttons
	// ************* For MY to add in for Account Settings and Logout ****************
	private class EventButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StudentEvent studentEvent = new StudentEvent();
			studentEvent.readFile();
			studentEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			studentEvent.setContentPane(studentEvent.createContentPane());
			studentEvent.setSize(650, 700);
			studentEvent.setVisible(true);
			
			dispose();
		}
	}
	
	private class CartButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			CartFrame cartFrame = new CartFrame();
			cartFrame.ReadAllEvent();
			cartFrame.readCart();
			cartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cartFrame.setContentPane(cartFrame.createCartFrame());
			cartFrame.setSize(650, 700);
			cartFrame.setVisible(true);
			
			dispose();
		}
	}
	
	private class BookedButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BookedEvent bookedEvent = new BookedEvent();
			bookedEvent.ReadAllEvent();
			bookedEvent.readBookedEvent();
			bookedEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			bookedEvent.setContentPane(bookedEvent.createBookedPane());
			bookedEvent.setSize(650, 800);
			bookedEvent.setVisible(true);
			
			dispose();
		}
	}
}

// Event Listing Frame - Student
class StudentEvent extends JFrame
{
	// Declaration
	private JLabel picLabel;
	private ImageIcon logo;
	ArrayList <String> eventString = new ArrayList <String>();
	ArrayList <ArrayList<String>> eventDetails = new ArrayList<ArrayList<String>>();
	private JList <String> eventList;
	private JLabel label1;
	private JButton backButton;
	private JTextArea eventStuff;
	private JLabel label2;
	private JTextField ticketQuan;
	private JButton ATC;
	String[] nameArray;
	String userID;
	
	// Reading in eventsDetail.txt
	public void readFile()
	{
		// ************* For MY to link ****************
		userID = "6788764";
		
		try
		{
			Scanner input = new Scanner (new File ("eventsDetail.txt"));
			String aLine;
			
			while (input.hasNextLine())
			{
				aLine = input.nextLine();
				eventString.add(aLine);
			}
		}
		
		catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(1);
        }
		
		// Separating eventString
		// 0 - name, 1 - price, 2 - start date, 3 - total day, 4 - time, 5 - promotion,
		// 6 - capacities, 7 - public/private, 8 - Amt of Ticket Booked
		for (int i = 0; i < eventString.size(); i++)
		{
			String[] data = eventString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
			if (tempData.get(7).equals("Public"))
				eventDetails.add(tempData);
		}
		
		// Transfer Event Names into Array
		nameArray = new String [eventDetails.size()];
		for (int i = 0; i < eventDetails.size(); i++)
		{
			nameArray[i] = eventDetails.get(i).get(0);
		}
	}
	
	// Writing to File
	public void writeFile()
	{	
		String outputString;
		outputString = userID;
		outputString += ", ";
		outputString += eventDetails.get(eventList.getSelectedIndex()).get(0);
		outputString += ", ";
		outputString += ticketQuan.getText();
		
		try
		{		
			FileWriter fileWrite = new FileWriter("cartDetails.txt", true);
			PrintWriter printWriter = new PrintWriter(fileWrite);
			printWriter.println(outputString);
			printWriter.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error");
			System.exit(1);
		}
	}
	
	// Construct an instance of StudentEvent
	public StudentEvent()
	{
		super("UOW Events Booking System");
	}
	
	// Create StudentEvent Frame
	public Container createContentPane()
	{
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		// Add in UOW Logo
		logo = new ImageIcon ("uow.jpg");
		Image img = logo.getImage();
		Image newImg = img.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		Icon pict = new ImageIcon(newImg);
		picLabel = new JLabel(pict);
		picLabel.setBounds(20, 10, 600, 200);
		panel2.add (picLabel);
		
		// Add in Event Listing Label
		label1 = new JLabel("Event Listing");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
		label1.setBounds(30, 220, 200, 100);
		panel2.add (label1);
		
		// Add in Event List
		eventList = new JList<String>(nameArray);
		eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eventList.setFont(new Font(eventList.getFont().getName(), Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(eventList);
		scrollPane.setBounds(30, 285, 400, 150);
		panel2.add (scrollPane);
		
		// Add in Back Button
		backButton = new JButton("Return to Main Menu");
		backButton.setBounds(400, 210, 180, 50);
		backButton.addActionListener(new BackButtonHandler());
		panel2.add (backButton);
		
		// Add in field for quantity
		label2 = new JLabel("Quantity: ");
		label2.setFont(new Font(label2.getFont().getName(), Font.PLAIN, 16));
		label2.setBounds(40, 575, 80, 40);
		panel2.add (label2);
		
		ticketQuan = new JTextField("1");
		ticketQuan.setFont(new Font(ticketQuan.getFont().getName(), Font.PLAIN, 16));
		ticketQuan.setBounds(115, 580, 50, 30);
		panel2.add (ticketQuan);
		
		// Add in Add to Cart Button
		ATC = new JButton ("Add To Cart");
		ATC.setBounds(215, 575, 140, 40);
		ATC.addActionListener(new AddButtonHandler());
		panel2.add (ATC);
		
		// Add in Event Details
		String details = "";
		
		if (eventDetails.size() != 0)
		{
			// Getting Event Name
			details = " Event Name: ";
			details += eventDetails.get(0).get(0);
			
			// Getting Ticket Price
			details += "\n Ticket Price: $";
			details += eventDetails.get(0).get(1);
			
			// Getting Event Date
			details += "\n Event Date: ";
			String[] eventDate = eventDetails.get(0).get(2).split("");
			details += eventDate[0];
			details += eventDate[1];
			details += "/";
			details += eventDate[2];
			details += eventDate[3];
			details += "/";
			details += eventDate[4];
			details += eventDate[5];
			details += eventDate[6];
			details += eventDate[7];
			
			// Getting Event Date (if more than one day)
			if (!eventDetails.get(0).get(3).equals("1"))
			{
				int day = Integer.parseInt(eventDetails.get(0).get(3));
				details += " to ";
				
				// Getting day as int
				String tempStr = "";
				tempStr += eventDate[0];
				tempStr += eventDate[1];
				int tempDay = Integer.parseInt(tempStr) + day;
				
				// Convert day back to string
				String finalDay = "";
				if (tempDay <= 9)
				{
					finalDay += "0";
					finalDay += Integer.toString(tempDay);
				}
				
				else
				{
					finalDay += Integer.toString(tempDay);
				}
				
				details += finalDay;
				details += "/";
				details += eventDate[2];
				details += eventDate[3];
				details += "/";
				details += eventDate[4];
				details += eventDate[5];
				details += eventDate[6];
				details += eventDate[7];
			}
			
			// Getting Event Time
			details += "\n Time: ";
			details += eventDetails.get(0).get(4);
					
			// Getting Capacity left
			details += "\n Ticket Available: ";
			details += Integer.toString(Integer.parseInt(eventDetails.get(0).get(6)) 
			- Integer.parseInt(eventDetails.get(0).get(8)));	
		}
		
		eventStuff = new JTextArea (details);
		eventStuff.setFont(new Font(eventStuff.getFont().getName(), Font.PLAIN, 16));
		eventStuff.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		eventStuff.setBackground(Color.LIGHT_GRAY);
		eventStuff.setEditable(false);
		eventStuff.setBounds(30, 450, 400, 110);
		panel2.add (eventStuff);
		
		eventList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent event)
			{
				int selectedInd = eventList.getSelectedIndex();
				String updated;
				
				updated = " Event Name: ";
				updated += eventDetails.get(selectedInd).get(0);
				
				// Getting Ticket Price
				updated += "\n Ticket Price: $";
				updated += eventDetails.get(selectedInd).get(1);
				
				// Getting Event Date
				updated += "\n Event Date: ";
				String[] eventDate = eventDetails.get(selectedInd).get(2).split("");
				updated += eventDate[0];
				updated += eventDate[1];
				updated += "/";
				updated += eventDate[2];
				updated += eventDate[3];
				updated += "/";
				updated += eventDate[4];
				updated += eventDate[5];
				updated += eventDate[6];
				updated += eventDate[7];
				
				// Getting Event Date (if more than one day)
				if (!eventDetails.get(selectedInd).get(3).equals("1"))
				{
					int day = Integer.parseInt(eventDetails.get(selectedInd).get(3));
					updated += " to ";
					
					// Getting day as int
					String tempStr = "";
					tempStr += eventDate[0];
					tempStr += eventDate[1];
					int tempDay = Integer.parseInt(tempStr) + day;
					
					// Convert day back to string
					String finalDay = "";
					if (tempDay <= 9)
					{
						finalDay += "0";
						finalDay += Integer.toString(tempDay);
					}
					
					else
					{
						finalDay += Integer.toString(tempDay);
					}
					
					updated += finalDay;
					updated += "/";
					updated += eventDate[2];
					updated += eventDate[3];
					updated += "/";
					updated += eventDate[4];
					updated += eventDate[5];
					updated += eventDate[6];
					updated += eventDate[7];
				}
				
				// Getting Event Time
				updated += "\n Time: ";
				updated += eventDetails.get(selectedInd).get(4);
						
				// Getting Capacity left
				updated += "\n Ticket Available: ";
				updated += Integer.toString(Integer.parseInt(eventDetails.get(selectedInd).get(6)) 
					- Integer.parseInt(eventDetails.get(selectedInd).get(8)));

				eventStuff.setText(updated);
			}
		});
		
		Container c = getContentPane();
		c.add(panel1, BorderLayout.NORTH);
		c.add(panel2,BorderLayout.CENTER);
		return c;
	}
	
	// Adding in ActionListener for Buttons
	private class BackButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StudentFrame studentFrame = new StudentFrame();
			studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			studentFrame.setSize(720, 420);
			studentFrame.setVisible(true);
			
			dispose();
		}
	}
	
	private class AddButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int ticketLeft = Integer.parseInt(eventDetails.get(eventList.getSelectedIndex()).get(6)) 
					- Integer.parseInt(eventDetails.get(eventList.getSelectedIndex()).get(8));
			if (Integer.parseInt(ticketQuan.getText()) < ticketLeft)
			{
				writeFile();
				JOptionPane.showMessageDialog(null, "Event successfully added to cart!");
				ticketQuan.setText("1");
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "Slots unavailable. Please reduce the ticket quantity!");
				ticketQuan.setText(Integer.toString(ticketLeft));
			}
		}
	}
}


// Shopping Cart Frame
class CartFrame extends JFrame
{
	// Declaration 
	private JLabel picLabel;
	private ImageIcon logo;
	private JLabel label1;
	private JTable eventTable;
	private JButton backButton;
	String userID;
	ArrayList <String> eventString = new ArrayList <String>();
	ArrayList <ArrayList<String>> eventDetails = new ArrayList<ArrayList<String>>();
	ArrayList <String> cartString = new ArrayList <String>();
	ArrayList <ArrayList<String>> cartDetails = new ArrayList<ArrayList<String>>();
	private JLabel priceLabel;
	private JTextField priceField;
	private JTextField promoField;
	private JButton promoButton;
	private JButton paymentButton;
	
	// Reading in Full Event List
	public void ReadAllEvent()
	{
		// ************* For MY to link ****************
		userID = "6788764";
		
		try
		{
			Scanner input = new Scanner (new File ("eventsDetail.txt"));
			String aLine;
			
			while (input.hasNextLine())
			{
				aLine = input.nextLine();
				eventString.add(aLine);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		
		// Separating eventString
		// 0 - name, 1 - price, 2 - start date, 3 - total day, 4 - time, 5 - promotion,
		// 6 - capacities, 7 - public/private, 8 - amount of ticket booked
		for (int i = 0; i < eventString.size(); i++)
		{
			String[] data = eventString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
			if (tempData.get(7).equals("Public"))
				eventDetails.add(tempData);
		}
	}
	
	// Reading in User Cart
	public void readCart()
	{
		try 
		{
			Scanner input = new Scanner (new File ("cartDetails.txt"));
			String aLine;
			
			while (input.hasNextLine())
			{
				aLine = input.nextLine();
				cartString.add(aLine);
			}
		}
		
		catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(1);
        }
		
		// Separating cartString
		// 0 - User ID, 1 - Event Name, 2 - Amt of Ticket Brought
		for (int i = 0; i < cartString.size(); i++)
		{
			String[] data = cartString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
			if (tempData.get(0).equals(userID))
				cartDetails.add(tempData);
		}
	}
	
	// Changing jTable Column size
	protected void setupTable(JTable table) 
	{
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(160);
		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setMaxWidth(80);
		table.getColumnModel().getColumn(4).setMaxWidth(160);
		table.setRowHeight(40);
	}
	
	// Construct an instance of CartFrame
	public CartFrame()
	{
		super("UOW Events Booking System");
	}
	
	// Create CartFrame Frame
	public Container createCartFrame()
	{
		JPanel topPanel = new JPanel();
		JPanel activePanel = new JPanel();
		activePanel.setLayout(null);
		
		
		// Add in UOW Logo
		logo = new ImageIcon ("uow.jpg");
		Image img = logo.getImage();
		Image newImg = img.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		Icon pict = new ImageIcon(newImg);
		picLabel = new JLabel(pict);
		picLabel.setBounds(20, 10, 600, 200);
		activePanel.add (picLabel);
		
		// Add in Back Button
		backButton = new JButton("Return to Main Menu");
		backButton.setBounds(400, 210, 180, 50);
		activePanel.add (backButton);
		
		// Add in Shopping Cart Label
		label1 = new JLabel("Shopping Cart");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
		label1.setBounds(30, 220, 200, 100);
		activePanel.add (label1);
		
		// Creating a table to store Event in Cart
		Object columnName[] = {"", "Event", "Price ($)", "Slot Left", "Quantity"};
		Object[][] cartData = new Object[cartDetails.size()][5];
		
		// Adding in cart Details
		for (int i = 0; i < cartDetails.size(); i++)
		{
			// Setting Checkbox
			cartData[i][0] = true;
			
			// Getting Event Name
			cartData[i][1] = cartDetails.get(i).get(1);
			
			
			// Getting Event Price and Slot Left
			for (int j = 0; j < eventDetails.size(); j++)
			{
				if (cartDetails.get(i).get(1).equals(eventDetails.get(j).get(0)))
				{
					cartData[i][2] = Double.toString(Double.parseDouble(eventDetails.get(j).get(1))
					* Integer.parseInt(cartDetails.get(i).get(2)));
					cartData[i][3] = Integer.toString(Integer.parseInt(eventDetails.get(j).get(6))
					- Integer.parseInt(eventDetails.get(j).get(8)));
					break;
				}
			}
			
			// Getting Ticket Quantity user Booked
			cartData[i][4] = cartDetails.get(i).get(2);
		}
		
		DefaultTableModel cartModel = new DefaultTableModel(cartData, columnName);
		
		eventTable = new JTable(cartModel)
		{
			@Override
			public Class getColumnClass (int column)
			{
				switch (column)
				{
					case 0:
                        return Boolean.class;
                    default:
                        return String.class;
				}
			}

			public boolean isCellEditable (int row, int column)
			{
				if (column == 0)
				{
					return true;
				}
				
				else
				{
					return false;
				}
			}
			
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				if (column == 4)
				{
					return new PlusMinusCellRenderer();
				}
				
				return super.getCellRenderer(row, column);
			}
		}; 
		
		setupTable(eventTable);
		
		JScrollPane eventScroll = new JScrollPane(eventTable);
		eventScroll.setBounds(30, 290, 540, 140);
		activePanel.add(eventScroll);
		
		// Add in Price Label
		priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font(priceLabel.getFont().getName(), Font.PLAIN, 20));
		priceLabel.setBounds(30, 410, 200, 100);
		activePanel.add (priceLabel);
		
		// Add in Price TextField
		double totalPrice = 0;
		String getData;
		for (int i = 0; i < eventTable.getRowCount(); i++)
		{
			getData = String.valueOf(eventTable.getModel().getValueAt(i, 2));
			totalPrice += Double.parseDouble(getData);
		}
		
		String price = "$";
		price += String.format("%.2f", totalPrice);
		
		priceField = new JTextField(price);
		priceField.setEditable(false);
		priceField.setBackground(Color.WHITE);
		priceField.setFont(new Font(priceField.getFont().getName(), Font.PLAIN, 20));
		priceField.setBounds(95, 440, 90, 40);
		activePanel.add (priceField);
		
		// Add in Promo TextField
		promoField = new JTextField("Enter Code Here");
		promoField.setFont(new Font(priceField.getFont().getName(), Font.PLAIN, 16));
		promoField.setBounds(40, 500, 120, 35);
		activePanel.add (promoField);
		
		// Add in Promo Button
		promoButton = new JButton("Enter Promo Code");
		promoButton.setFont(new Font(promoButton.getFont().getName(), Font.PLAIN, 16));
		promoButton.setBounds(165, 500, 200, 35);
		activePanel.add (promoButton);
		
		// Add in Payment Button
		paymentButton = new JButton("Make Payment");
		paymentButton.setFont(new Font(paymentButton.getFont().getName(), Font.PLAIN, 16));
		paymentButton.setBounds(390, 550, 180, 35);
		activePanel.add (paymentButton);
		
		Container a = getContentPane();
		a.add(topPanel, BorderLayout.NORTH);
		a.add(activePanel,BorderLayout.CENTER);
		return a;
	}
}

// Edit the quantity cell in JTable
class PlusMinusCellRenderer extends JPanel implements TableCellRenderer
{
	JButton minus;
	JButton plus;
	JTextField totalQuan;
	
	public Component getTableCellRendererComponent(
						final JTable table, Object value,
						boolean isSelected, boolean hasFocus,
						int row, int column) 
	{	
		
		minus = new JButton("-");
		
		totalQuan = new JTextField( value.toString()); 
		plus = new JButton("+");
		
		this.add(minus);
		
		
		this.add(totalQuan);
		
		this.add(plus);
		
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getSource() == minus)
			{
				if (!totalQuan.getText().equals("0"))
				{
					int tmp = Integer.parseInt(totalQuan.getText());
					tmp -= 1;
					totalQuan.setText(Integer.toString(tmp));
				}
			}
			else
			{
				int tmp = Integer.parseInt(totalQuan.getText());
				tmp += 1;
				totalQuan.setText(Integer.toString(tmp));
			}
			}				
		});

		
		return this;
		
		
	}
	
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == minus)
			{
				if (!totalQuan.getText().equals("0"))
				{
					int tmp = Integer.parseInt(totalQuan.getText());
					tmp -= 1;
					totalQuan.setText(Integer.toString(tmp));
				}
			}
			else
			{
				int tmp = Integer.parseInt(totalQuan.getText());
				tmp += 1;
				totalQuan.setText(Integer.toString(tmp));
			}
		}
	}
}

// Booked Event Frame - Student
class BookedEvent extends JFrame
{
	// Declaration
	private JLabel picLabel;
	private ImageIcon logo;
	private JLabel label1;
	private JButton backButton;
	String userID;
	ArrayList <String> eventString = new ArrayList <String>();
	ArrayList <ArrayList<String>> eventDetails = new ArrayList<ArrayList<String>>();
	ArrayList <String> bookedString = new ArrayList <String>();
	ArrayList <ArrayList<String>> bookedDetails = new ArrayList<ArrayList<String>>();
	String[] bookedNameArr;
	private JList <String> bookedList;
	private JTextArea bookedStuff;
	private JLabel quanLabel;
	private JButton[] quanButton = new JButton[2];
	private JTextField quanField;
	private JButton confirmButton;
	private JTextArea infoLabel;
	
	// Reading in Full Event List
	public void ReadAllEvent()
	{
		// ************* For MY to link ****************
		userID = "6788764";
		
		try
		{
			Scanner input = new Scanner (new File ("eventsDetail.txt"));
			String aLine;
			
			while (input.hasNextLine())
			{
				aLine = input.nextLine();
				eventString.add(aLine);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		
		// Separating eventString
		// 0 - name, 1 - price, 2 - start date, 3 - total day, 4 - time, 5 - promotion,
		// 6 - capacities, 7 - public/private, 8 - amount of ticket booked
		for (int i = 0; i < eventString.size(); i++)
		{
			String[] data = eventString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
			if (tempData.get(7).equals("Public"))
				eventDetails.add(tempData);
		}
	}
	
	// Reading in User Booked Event
	public void readBookedEvent()
	{
		try 
		{
			Scanner input = new Scanner (new File ("bookedEvents.txt"));
			String aLine;
			
			while (input.hasNextLine())
			{
				aLine = input.nextLine();
				bookedString.add(aLine);
			}
		}
		
		catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(1);
        }
		
		// Separating bookedString
		// 0 - User ID, 1 - Event Name, 2 - Amt of Ticket Brought
		for (int i = 0; i < bookedString.size(); i++)
		{
			String[] data = bookedString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
			if (tempData.get(0).equals(userID))
				bookedDetails.add(tempData);
		}
		
		bookedNameArr = new String [bookedDetails.size()];
		for (int i = 0; i < bookedDetails.size(); i++)
		{
			bookedNameArr[i] = bookedDetails.get(i).get(1);
		}
		
	}
	
	// Construct an instance of BookedEvent
	public BookedEvent()
	{
		super("UOW Events Booking System");
	}
	
	// Create BookedEvent Frame
	public Container createBookedPane()
	{
		JPanel backPanel = new JPanel();

		JPanel frontPanel = new JPanel();
		frontPanel.setLayout(null);
		
		// Add in UOW Logo
		logo = new ImageIcon ("UOWLogo.png");
		Image img = logo.getImage();
		Image newImg = img.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		Icon pict = new ImageIcon(newImg);
		picLabel = new JLabel(pict);
		picLabel.setBounds(20, 10, 600, 200);
		frontPanel.add (picLabel);
		
		// Add in Booked Event Label
		label1 = new JLabel("Booked Event");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
		label1.setBounds(30, 220, 200, 100);
		frontPanel.add (label1);
		
		// Add in Booked List
		bookedList = new JList <String> (bookedNameArr);
		bookedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookedList.setFont(new Font(bookedList.getFont().getName(), Font.PLAIN, 14));
		JScrollPane scrollBPane = new JScrollPane(bookedList);
		scrollBPane.setBounds(30, 285, 400, 150);
		frontPanel.add (scrollBPane);
		
		// Add in Back Button
		backButton = new JButton("Return to Main Menu");
		backButton.setBounds(400, 210, 180, 50);
		frontPanel.add (backButton);
		
		// Add in Booked Details
		String booked = "";
		
		if (bookedDetails.size() != 0)
		{
			// Getting Event Name
			booked = " Event Name: ";
			booked += bookedDetails.get(0).get(1);
			
			// Getting Event Date and Time
			for (int i = 0; i < eventDetails.size(); i++)
			{
				if (bookedDetails.get(0).get(1).equals(eventDetails.get(i).get(0)))
				{
					booked += "\n Event Date: ";
					String[] eventDate = eventDetails.get(i).get(2).split("");
					booked += eventDate[0];
					booked += eventDate[1];
					booked += "/";
					booked += eventDate[2];
					booked += eventDate[3];
					booked += "/";
					booked += eventDate[4];
					booked += eventDate[5];
					booked += eventDate[6];
					booked += eventDate[7];
					
					// Getting Event Date (if more than one day)
					if (!eventDetails.get(i).get(3).equals("1"))
					{
						int day = Integer.parseInt(eventDetails.get(i).get(3));
						booked += " to ";
						
						// Getting day as int
						String tempStr = "";
						tempStr += eventDate[0];
						tempStr += eventDate[1];
						int tempDay = Integer.parseInt(tempStr) + day;
						
						// Convert day back to string
						String finalDay = "";
						if (tempDay <= 9)
						{
							finalDay += "0";
							finalDay += Integer.toString(tempDay);
						}
						
						else
						{
							finalDay += Integer.toString(tempDay);
						}
						
						booked += finalDay;
						booked += "/";
						booked += eventDate[2];
						booked += eventDate[3];
						booked += "/";
						booked += eventDate[4];
						booked += eventDate[5];
						booked += eventDate[6];
						booked += eventDate[7];
					}
					
					booked += "\n Time: ";
					booked += eventDetails.get(i).get(4);
					
					break;
				}	
			}
			
			// Getting Ticket Quantity
			booked += "\n Ticket Quantity: ";
			booked += bookedDetails.get(0).get(2);
		}
		
		bookedStuff = new JTextArea (booked);
		bookedStuff.setFont(new Font(bookedStuff.getFont().getName(), Font.PLAIN, 16));
		bookedStuff.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		bookedStuff.setBackground(Color.LIGHT_GRAY);
		bookedStuff.setEditable(false);
		bookedStuff.setBounds(30, 450, 400, 90);
		frontPanel.add (bookedStuff);
		
		bookedList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent event)
			{
				int selectInd = bookedList.getSelectedIndex();
				String update;
				
				// Getting Event Name
				update = " Event Name: ";
				update += bookedDetails.get(selectInd).get(1);
				
				// Getting Event Date and Time
				for (int i = 0; i < eventDetails.size(); i++)
				{
					if (bookedDetails.get(selectInd).get(1).equals(eventDetails.get(i).get(0)))
					{
						update += "\n Event Date: ";
						String[] eventDate = eventDetails.get(i).get(2).split("");
						update += eventDate[0];
						update += eventDate[1];
						update += "/";
						update += eventDate[2];
						update += eventDate[3];
						update += "/";
						update += eventDate[4];
						update += eventDate[5];
						update += eventDate[6];
						update += eventDate[7];
						
						// Getting Event Date (if more than one day)
						if (!eventDetails.get(i).get(3).equals("1"))
						{
							int day = Integer.parseInt(eventDetails.get(i).get(3));
							update += " to ";
							
							// Getting day as int
							String tempStr = "";
							tempStr += eventDate[0];
							tempStr += eventDate[1];
							int tempDay = Integer.parseInt(tempStr) + day;
							
							// Convert day back to string
							String finalDay = "";
							if (tempDay <= 9)
							{
								finalDay += "0";
								finalDay += Integer.toString(tempDay);
							}
							
							else
							{
								finalDay += Integer.toString(tempDay);
							}
							
							update += finalDay;
							update += "/";
							update += eventDate[2];
							update += eventDate[3];
							update += "/";
							update += eventDate[4];
							update += eventDate[5];
							update += eventDate[6];
							update += eventDate[7];
						}
						
						update += "\n Time: ";
						update += eventDetails.get(i).get(4);
						
						break;
					}	
				}
				
				// Getting Ticket Quantity
				update += "\n Ticket Quantity: ";
				update += bookedDetails.get(selectInd).get(2);	
				
				bookedStuff.setText(update);
				quanField.setText(bookedDetails.get(selectInd).get(2));
			}
		});	

		// Add in Quantity Label
		quanLabel = new JLabel("Quantity: ");
		quanLabel.setFont(new Font(quanLabel.getFont().getName(), Font.PLAIN, 16));
		quanLabel.setBounds(40, 540, 80, 40);
		frontPanel.add(quanLabel);
		
		// Add in + and - button
		quanButton[0] = new JButton ("-");
		quanButton[0].setBounds(120, 550, 45, 20);
		quanButton[0].setBackground(Color.WHITE);
		frontPanel.add(quanButton[0]);
		
		quanButton[1] = new JButton ("+");
		quanButton[1].setBounds(225, 550, 45, 20);
		quanButton[1].setBackground(Color.WHITE);
		frontPanel.add (quanButton[1]);
		
		// Add in quantity textbox
		quanField = new JTextField (bookedDetails.get(0).get(2));
		quanField.setBounds(170, 545, 50, 30);
		quanField.setEditable(false);
		frontPanel.add(quanField);
		
		// Add in confirm button
		confirmButton = new JButton ("Confirm");
		confirmButton.setBounds(350, 700, 80, 40);
		confirmButton.setBackground(Color.WHITE);
		frontPanel.add(confirmButton);
		
		// Add in info label1
		String post = "This is only for reducing of ticket! \nIf you would like to purchase more ticket, \nplease go to the Event Listing! \n\nPLEASE NOTE THAT THERE WILL BE NO REFUNDS!";
		infoLabel = new JTextArea (post);
		infoLabel.setFont(new Font(infoLabel.getFont().getName(), Font.PLAIN, 14));
		infoLabel.setForeground(Color.RED);
		infoLabel.setOpaque(false);
		infoLabel.setBounds(40, 590, 380, 100);
		frontPanel.add(infoLabel);
		
		Container b = getContentPane();
		b.add(backPanel, BorderLayout.NORTH);
		b.add(frontPanel, BorderLayout.CENTER);
		return b;
	}
}

class student
{
	public static void main(String[] args)
	{
		/*StudentFrame studentFrame = new StudentFrame();
		studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentFrame.setSize(720, 420);
		studentFrame.setVisible(true);*/
		
		/*StudentEvent studentEvent = new StudentEvent();
		studentEvent.readFile();
		studentEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentEvent.setContentPane(studentEvent.createContentPane());
		studentEvent.setSize(650, 700);
		studentEvent.setVisible(true);*/
		
		CartFrame cartFrame = new CartFrame();
		cartFrame.ReadAllEvent();
		cartFrame.readCart();
		cartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cartFrame.setContentPane(cartFrame.createCartFrame());
		cartFrame.setSize(650, 700);
		cartFrame.setVisible(true);
		
		/*BookedEvent bookedEvent = new BookedEvent();
		bookedEvent.ReadAllEvent();
		bookedEvent.readBookedEvent();
		bookedEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bookedEvent.setContentPane(bookedEvent.createBookedPane());
		bookedEvent.setSize(650, 800);
		bookedEvent.setVisible(true);*/
		
	}
}