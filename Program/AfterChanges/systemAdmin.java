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

// System Admin Frame
class systemAdminFrame extends JFrame
{
    // Declaration
    String userID;
	ArrayList <String> eventString = new ArrayList <String>();
    ArrayList <ArrayList<String>> allEventDetails = new ArrayList<ArrayList<String>>();
    ArrayList <ArrayList<String>> approvedEventDetails = new ArrayList<ArrayList<String>>();
    private JLabel picLabel;
    private ImageIcon logo;
    private JButton logoutButton;
    private JButton accountButton;
    private JLabel label1;
    private JTable eventTable;
    private JButton approveButton;
    private JButton venueButton;
    private JLabel venue;
    private JLabel startDate;
    private JLabel endDate;
    private JTextField venueField;
    private JTextField sDateField;
    private JTextField eDateField;
    private JButton confirmButton;
    private JTable venueTable;

    
    // Reading in Full Event
    public void ReadAllEvent()
	{
		// ************* For MY to link ****************
		userID = "6788764";
		
		allEventDetails.clear();
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
		// 0 - name, 1 - price, 2 - start date, 3 - total day, 4 - time, 5 - venue,
		// 6 - capacities, 7 - public/private, 8 - amount of ticket booked
		for (int i = 0; i < eventString.size(); i++)
		{
			String[] data = eventString.get(i).split(", ");
			ArrayList <String> tempData = new ArrayList <String>();
			
			for (int j = 0; j < data.length; j++)
			{
				tempData.add(data[j]);
			}
			
            allEventDetails.add(tempData);
		}
    }
    
    // Reading in approved events
    public void ReadApprovedEvent()
    {
        approvedEventDetails.clear();
		// Separating eventString
		// 0 - name, 1 - price, 2 - start date, 3 - total day, 4 - time, 5 - venue,
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
            approvedEventDetails.add(tempData);
		}
    }

    // Writing to eventDetails.txt
    public void updateEvent(String eventName)
    {
        for (int i = 0; i < allEventDetails.size(); i++)
		{
			if (eventName.equals(allEventDetails.get(i).get(0)))
			{
                allEventDetails.get(i).set(7, "Public");
                
				break;
			}
		}
		
		String outputString = "";
		
		try 
		{		
			FileWriter fileWrite = new FileWriter("eventsDetail.txt", false);
			PrintWriter printWriter = new PrintWriter(fileWrite);
			
			for (int i = 0; i < allEventDetails.size(); i++)
			{
				outputString = allEventDetails.get(i).get(0);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(1);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(2);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(3);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(4);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(5);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(6);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(7);
				outputString += ", ";
				outputString += allEventDetails.get(i).get(8);
				
				printWriter.println(outputString);
			}
			
			printWriter.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error");
			System.exit(1);
		}
    }

    // Changing jTable Column size - event
	protected void setupTable(JTable table) 
	{
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMaxWidth(180);
        table.getColumnModel().getColumn(3).setMaxWidth(120);
        table.getColumnModel().getColumn(4).setMaxWidth(90);
		table.setRowHeight(40);
    }
    
    // Changing jTable Column size - venue 
    protected void setUpVenue(JTable table)
    {
        table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(220);
		table.getColumnModel().getColumn(1).setMaxWidth(180);
		table.setRowHeight(40);
    }

    // Construct an instance of systemAdminFrame
    public systemAdminFrame()
    {
        super("UOW Events Booking System");
    }

    // Create systemAdminFrame Frame
    public Container createSysAdmFrame()
    {
        JPanel blankPanel = new JPanel();
		JPanel workPanel = new JPanel();
        workPanel.setLayout(null);
        
        // Add in UOW Logo
		logo = new ImageIcon ("UOW.jpg");
		Image img = logo.getImage();
		Image newImg = img.getScaledInstance(640, 200, Image.SCALE_SMOOTH);
		Icon pict = new ImageIcon(newImg);
		picLabel = new JLabel(pict);
		picLabel.setBounds(30, 5, 640, 200);
        workPanel.add (picLabel);

        // Add in Logout Button
        logoutButton = new JButton ("Logout");
        logoutButton.setBounds(500, 220, 150, 30);
        logoutButton.setBackground(Color.WHITE);
        // ************* For MY to add in ActionListener ****************
        workPanel.add (logoutButton);

        // Add in Account Button
        accountButton = new JButton("Account Settings");
        accountButton.setBounds(50, 220, 150, 30);
        accountButton.setBackground(Color.WHITE);
        // ************* For MY to add in ActionListener ****************
        workPanel.add (accountButton);

        // Add in Event Listing Label 
        label1 = new JLabel("Event Listing");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 18));
		label1.setBounds(40, 230, 200, 100);
		workPanel.add (label1);
        
        // Creating a table to display all event
        Object columnName[] = {"Event", "Price ($)", "Date", "Venue", "Status"};
        Object [][] eventData = new Object[allEventDetails.size()][5];

        // Adding in event Details
        for (int i = 0; i < allEventDetails.size(); i++)
        {
            // Getting Event Name
            eventData[i][0] = allEventDetails.get(i).get(0);

            // Getting Event Price
            eventData[i][1] = allEventDetails.get(i).get(1);

            // Getting Event Date
            String update;
            String[] eventDate = allEventDetails.get(i).get(2).split("");
            update = eventDate[0];
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
            if (!allEventDetails.get(i).get(3).equals("1"))
            {
                int day = Integer.parseInt(allEventDetails.get(i).get(3));
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
            eventData[i][2] = update;

            // Getting Event Venue
            eventData[i][3] = allEventDetails.get(i).get(5);

            // Getting Event Status
            if (allEventDetails.get(i).get(7).equals("Private"))
            {
                eventData[i][4] = "Pending";
            }
            else
            {
                eventData[i][4] = "Approved";
            }
        }

        DefaultTableModel eventModel = new DefaultTableModel(eventData, columnName);

        eventTable = new JTable(eventModel)
        {
            @Override
            public Class getColumnClass (int column)
            {
                return String.class;
            }

            @Override
            public boolean isCellEditable (int row, int column)
            {
                return false;
            }
        };

        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent event)
            {
                int selectedIndex = eventTable.getSelectedRow();
                
                String getData;
                getData = String.valueOf(eventTable.getModel().getValueAt(selectedIndex, 4));

                if (getData.equals("Pending"))
                {
                    approveButton.setEnabled(true);
                }
                else
                {
                    approveButton.setEnabled(false);
                }
            }
            
        });

        setupTable(eventTable);

        JScrollPane eventScroll = new JScrollPane(eventTable);
        eventScroll.setBounds(30, 300, 620, 140);
        workPanel.add(eventScroll);

        // Add in approve button
        approveButton = new JButton("Approve Event");
        approveButton.setFont(new Font(approveButton.getFont().getName(), Font.PLAIN, 16));
        approveButton.setBounds(100, 460, 140, 35);
        approveButton.setEnabled(false);
        approveButton.addActionListener(new approveButtonHandler());
        workPanel.add (approveButton);
        
        // Add in venue button
        venueButton = new JButton("Check Venue Availability");
        venueButton.setFont(new Font(venueButton.getFont().getName(), Font.PLAIN, 16));
        venueButton.setBounds(340, 460, 240, 35);
        venueButton.addActionListener(new venueButtonHandler());
        workPanel.add (venueButton);

        // Add in venue label
        venue = new JLabel("Venue: ");
        venue.setFont(new Font(venue.getFont().getName(), Font.PLAIN, 15));
		venue.setForeground(Color.BLUE);
		venue.setBounds(150, 475, 480, 100);
        workPanel.add (venue);
        
        // Add in venue TextField
        venueField = new JTextField();
        venueField.setFont(new Font(venueField.getFont().getName(), Font.PLAIN, 16));
		venueField.setBounds(200, 510, 180, 30);
        workPanel.add (venueField);
        
        // Add in start date label
        startDate = new JLabel("Start Date (ddmmyyyy): ");
        startDate.setFont(new Font(startDate.getFont().getName(), Font.PLAIN, 15));
		startDate.setForeground(Color.BLUE);
		startDate.setBounds(40, 505, 480, 100);
        workPanel.add (startDate);
        
        // Add in start dateTextField
        sDateField = new JTextField();
        sDateField.setFont(new Font(sDateField.getFont().getName(), Font.PLAIN, 16));
		sDateField.setBounds(200, 545, 180, 30);
		workPanel.add (sDateField);

        // Add in end date label
        endDate = new JLabel("Start Date (ddmmyyyy): ");
        endDate.setFont(new Font(endDate.getFont().getName(), Font.PLAIN, 15));
		endDate.setForeground(Color.BLUE);
		endDate.setBounds(40, 540, 480, 100);
        workPanel.add (endDate);
        
        // Add in end dateTextField
        eDateField = new JTextField();
        eDateField.setFont(new Font(eDateField.getFont().getName(), Font.PLAIN, 16));
		eDateField.setBounds(200, 580, 180, 30);
        workPanel.add (eDateField);
        
        // Add in confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font(confirmButton.getFont().getName(), Font.PLAIN, 16));
        confirmButton.setBounds(440, 580, 140, 30);
        confirmButton.addActionListener(new confirmButtonHandler());
        workPanel.add (confirmButton);

        // add in venue table
        Object header[] = {"Event", "Date"};
        Object[][] venueData = new Object [approvedEventDetails.size()][4];

        DefaultTableModel venueModel = new DefaultTableModel(venueData, header);

        venueTable = new JTable(venueModel)
		{
			@Override
			public Class getColumnClass (int column)
			{
				return String.class;
			}

			@Override
			public boolean isCellEditable (int row, int column)
			{
				return false;
			}
        };

        setUpVenue(venueTable);

        JScrollPane venueScroll = new JScrollPane(venueTable);
		venueScroll.setBounds(120, 625, 400, 120);
		workPanel.add(venueScroll);
        
        Container l = getContentPane();
		l.add(blankPanel, BorderLayout.NORTH);
		l.add(workPanel,BorderLayout.CENTER);
		return l;
    }

    // Adding in ActionListener for Buttons
    private class approveButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            int selectedIndex = eventTable.getSelectedRow();
                    
            String getData;
            getData = String.valueOf(eventTable.getModel().getValueAt(selectedIndex, 0));

            updateEvent(getData);

            JOptionPane.showMessageDialog(null, "Event Successfully Approved!");

            systemAdminFrame sysAdmin = new systemAdminFrame();
            sysAdmin.ReadAllEvent();
            sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
            sysAdmin.setSize(720, 550);
            sysAdmin.setVisible(true);
            
            dispose();
        }
    }

    private class venueButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            systemAdminFrame sysAdmin = new systemAdminFrame();
            sysAdmin.ReadAllEvent();
            sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
            sysAdmin.setSize(720, 810);
            sysAdmin.setVisible(true);
            
            dispose();
        }
    }

    private class confirmButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            ReadApprovedEvent();

            String vName = venueField.getText();
            String[] sDate = sDateField.getText().split("");
            String[] eDate = eDateField.getText().split("");
            String[] evDate;
            String newEvent = "";
            Boolean isUsed = false;
            int year = (Integer.parseInt(sDate[4]) * 1000) + (Integer.parseInt(sDate[5]) * 100) + (Integer.parseInt(sDate[6]) * 10) + Integer.parseInt(sDate[7]);
            int month = (Integer.parseInt(sDate[2]) * 10) + Integer.parseInt(sDate[3]);
            int sday = (Integer.parseInt(sDate[0]) * 10) + Integer.parseInt(sDate[1]);
            int eday = (Integer.parseInt(eDate[0]) * 10) + Integer.parseInt(eDate[1]);


            for (int i = 0; i < approvedEventDetails.size(); i++)
            {
                isUsed = false;
                if (vName.equals(approvedEventDetails.get(i).get(5)))
                {
                    evDate = approvedEventDetails.get(i).get(2).split("");
                    int numOfDays = Integer.parseInt(approvedEventDetails.get(i).get(3));
                    int evyear = (Integer.parseInt(evDate[4]) * 1000) + (Integer.parseInt(evDate[5]) * 100) + (Integer.parseInt(evDate[6]) * 10) + Integer.parseInt(evDate[7]);
                    int evmonth = (Integer.parseInt(evDate[2]) * 10) + Integer.parseInt(evDate[3]);
                    int evday = (Integer.parseInt(evDate[0]) * 10) + Integer.parseInt(evDate[1]);

                    for (int j = 0; j < numOfDays; j++)
                    {
                        if (evyear == year)
                        {
                            if (evmonth == month)
                            {
                                if (evday >= sday && evday <= eday)
                                {
                                    isUsed = true;
                                    newEvent = evDate[0];
                                    newEvent += evDate[1];
                                    newEvent += "/";
                                    newEvent += evDate[2];
                                    newEvent += evDate[3];
                                    newEvent += "/";
                                    newEvent += evDate[4];
                                    newEvent += evDate[5];
                                    newEvent += evDate[6];
                                    newEvent += evDate[7];

                                    if (!approvedEventDetails.get(i).get(3).equals("1"))
                                    {
                                        int day = Integer.parseInt(approvedEventDetails.get(i).get(3));
                                        newEvent += " to ";
                                        
                                        // Getting day as int
                                        String tempStr = "";
                                        tempStr += evDate[0];
                                        tempStr += evDate[1];
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
                                        
                                        newEvent += finalDay;
                                        newEvent += "/";
                                        newEvent += evDate[2];
                                        newEvent += evDate[3];
                                        newEvent += "/";
                                        newEvent += evDate[4];
                                        newEvent += evDate[5];
                                        newEvent += evDate[6];
                                        newEvent += evDate[7];
                                        break;
                                    }
                                }
                                evday++;
                            }
                        }
                    }
                }

                if (isUsed)
                {
                    DefaultTableModel model = (DefaultTableModel) venueTable.getModel();
                    model.addRow(new Object[]{approvedEventDetails.get(i).get(0), newEvent});
                }
            }
        }
    }
}

class systemAdmin
{
    public static void main(String[] args)
	{
        systemAdminFrame sysAdmin = new systemAdminFrame();
        sysAdmin.ReadAllEvent();
        sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
		sysAdmin.setSize(720, 550);
		sysAdmin.setVisible(true);
	}
}