import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.lang.*;
import javax.swing.table.*;
import javax.swing.JScrollPane;
import javax.swing.event.*;
import java.time.*;
import java.time.format.*;

class userAdmin extends JFrame
{
	public userAdmin()
	{
		// Frame Name
		super("User Administrator");
		
		setLayout(null);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1335, 500);
		setVisible(true);
        JTable table1 = new JTable()
		{
			public boolean isCellEditable(int row, int column)
			{
				return column == 2 || column == 3;
			}
		};
        
        Object[] top = {"UOW ID", "User Type", "Password", "UOW E-mail", "Last Login",
						"Last Logout", "Account Status"};

        DefaultTableModel model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(top);
        table1.setModel(model1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel1 = table1.getColumnModel();
		colModel1.getColumn(3).setPreferredWidth(250);

		DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer)table1.getDefaultRenderer(Object.class);
		renderer1.setHorizontalAlignment(SwingConstants.CENTER);
        
        table1.setBackground(Color.LIGHT_GRAY);
        table1.setForeground(Color.black);
        Font font1 = new Font("SansSerif", Font.PLAIN, 14);
        table1.setFont(font1);
        table1.setRowHeight(25);
		
		JScrollPane pane1 = new JScrollPane(table1);
        pane1.setBounds(10, 40, 1300, 230);
        
        setLayout(null);
		
		JLabel chgTitle1 = new JLabel("Current User Accounts");
		JLabel chgTitle2 = new JLabel("New User Creation");
		
		Font titleFont1 = new Font("SansSerif", Font.PLAIN, 20);
		chgTitle1.setFont(titleFont1);
		chgTitle2.setFont(titleFont1);
		
		JLabel chgID = new JLabel("UOW ID:");
		JLabel chgType = new JLabel("User Type:");
		JLabel chgPassword = new JLabel("Password:");
		JLabel chgEmail = new JLabel("UOW E-mail:");
        JLabel chgLogin = new JLabel("Last Login:");
        JLabel chgLogout = new JLabel("Last Logout:");
        
		JTextField chgIDTxt = new JTextField();
		JTextField chgTypeTxt = new JTextField();
        JTextField chgPasswordTxt = new JTextField();
		JTextField chgEmailTxt = new JTextField();
        JTextField chgLoginTxt = new JTextField();
        JTextField chgLogoutTxt = new JTextField();
          
        JButton chgCreate = new JButton("Create");
        JButton chgEdit = new JButton("Edit");
		JButton chgDelete = new JButton("Delete");
		JButton chgStatus = new JButton("Change");
        JButton chgExit = new JButton("Logout");
        
		chgTitle1.setBounds(570, 10, 200, 25);
		chgTitle2.setBounds(570, 280, 250, 25);
		
        chgID.setBounds(90, 320, 100, 25);
        chgPassword.setBounds(490, 320, 100, 25);
        chgEmail.setBounds(860, 320, 100, 25);
                  
        chgIDTxt.setBounds(200, 320, 200, 25);
        chgPasswordTxt.setBounds(580, 320, 200, 25);
        chgEmailTxt.setBounds(960, 320, 200, 25);
		
        chgCreate.setBounds(200, 400, 100, 25);
        chgEdit.setBounds(400, 400, 100, 25);
		chgDelete.setBounds(600, 400, 100, 25);
		chgStatus.setBounds(800, 400, 100, 25);
        chgExit.setBounds(1000, 400, 100, 25);
        
        add(pane1);
		
        add(chgTitle1);
		add(chgTitle2);
		
        add(chgID);
        add(chgPassword);
        add(chgEmail);
        
        add(chgIDTxt);
        add(chgPasswordTxt);
        add(chgEmailTxt);
        
        add(chgCreate);
        add(chgEdit);
		add(chgDelete);
		add(chgStatus);
        add(chgExit);
		
		Object[] obj = new Object[7];
		
		try
		{
			Scanner input = new Scanner (new File ("loginDetail.txt"));
			
			while(input.hasNextLine())
			{
				String aLine = input.nextLine();
				
				String [] Line = aLine.split(" ");
				
				obj[0] = Line[0];
				
				if(Line[0].charAt(0) == '6')
				{
					obj[1] = "Student";
				}
				
				else if(Line[0].charAt(0) == '1')
				{
					obj[1] = "Staff";
				}
				
				else if(Line[0].charAt(0) == '0')
				{
					obj[1] = "Administrator";
				}
				
				else if(Line[0].charAt(0) == '9')
				{
					obj[1] = "Super User";
				}
				
				obj[2] = Line[1];
				obj[3] = Line[2];
				obj[4] = Line[3];
				obj[5] = Line[4];
				obj[6] = Line[5];
					
				model1.addRow(obj);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
	
		Object[] create = new Object[7];
		
		chgCreate.addActionListener(new ActionListener()
		{    
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(chgIDTxt.getText().equals("") || chgPasswordTxt.getText().equals("") || 
				chgEmailTxt.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "One or More Field/s are empty");
				}
				
				else if(chgIDTxt.getText().charAt(0) == '0')
				{
					JOptionPane.showMessageDialog(null, "Account cannot be created");
				}
				
				else 
				{
					create[0] = chgIDTxt.getText();
				
					if(chgIDTxt.getText().charAt(0) == '6')
					{
						create[1] = "Student";
					}
					
					else if(chgIDTxt.getText().charAt(0) == '1')
					{
						create[1] = "Staff";
					}
					
					else if(chgIDTxt.getText().charAt(0) == '9')
					{
						create[1] = "Super User";
					}
					
					create[2] = chgPasswordTxt.getText();
					create[3] = chgEmailTxt.getText();
					create[4] = "NULL";
					create[5] = "NULL";
					create[6] = "SUSPENDED";
					
					model1.addRow(create);
						
					JOptionPane.showMessageDialog(null,"You have created a new user.");
						
					chgIDTxt.setText("");
					chgPasswordTxt.setText("");
					chgEmailTxt.setText("");
				}
					
				try
				{
					File file = new File("loginDetail.txt");
					if(!file.exists())
					{
						file.createNewFile();
					}
					
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					
					for(int i = 0 ; i < table1.getRowCount(); i++)
					{
						for(int j = 0; j < table1.getColumnCount(); j++)
						{
							if(j != 1 && i == (table1.getRowCount() - 1) && 
							   j == (table1.getColumnCount() - 1))
							{
								bw.write(table1.getModel().getValueAt(i, j) + "");
							}
								
							else if(j != 1 && j == (table1.getColumnCount() - 1))
							{
								bw.write(table1.getModel().getValueAt(i, j) + "\n");
							}
								
							else if(j != 1)
							{
								bw.write(table1.getModel().getValueAt(i, j) + " ");
							}
						}
					}
						
					bw.close();
					fw.close();
				}
					
				catch(Exception ex)
				{
					   ex.printStackTrace();
				}
			}
		});
		
		chgEdit.addActionListener(new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                try
				{
					File file = new File("loginDetail.txt");
					if(!file.exists())
					{
						file.createNewFile();
					}
					
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					
					for(int i = 0 ; i < table1.getRowCount(); i++)
					{
						for(int j = 0; j < table1.getColumnCount(); j++)
						{
							if(j != 1 && i == (table1.getRowCount() - 1) && 
							   j == (table1.getColumnCount() - 1))
							{
								bw.write(table1.getModel().getValueAt(i, j) + "");
							}
								
							else if(j != 1 && j == (table1.getColumnCount() - 1))
							{
								bw.write(table1.getModel().getValueAt(i, j) + "\n");
							}
								
							else if(j != 1)
							{
								bw.write(table1.getModel().getValueAt(i, j) + " ");
							}
						}
					}
						
					bw.close();
					fw.close();
					JOptionPane.showMessageDialog(null, "You've updated the account details");
				
					table1.clearSelection();
                }
				
				catch(Exception ex)
				{
                    ex.printStackTrace();
                }
            }  
        });
		
		chgDelete.addActionListener(new ActionListener()
		{        
            @Override
            public void actionPerformed(ActionEvent e)
			{          
				int z = table1.getSelectedRow();
				
				if(z >= 0)
				{
					model1.removeRow(z);
					
					try
					{
						File file = new File("loginDetail.txt");
						if(!file.exists())
						{
							file.createNewFile();
						}
						
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						
						for(int i = 0 ; i < table1.getRowCount(); i++)
						{
							for(int j = 0; j < table1.getColumnCount(); j++)
							{
								if(j != 1 && i == (table1.getRowCount() - 1) && 
								   j == (table1.getColumnCount() - 1))
								{
									bw.write(table1.getModel().getValueAt(i, j) + "");
								}
									
								else if(j != 1 && j == (table1.getColumnCount() - 1))
								{
									bw.write(table1.getModel().getValueAt(i, j) + "\n");
								}
									
								else if(j != 1)
								{
									bw.write(table1.getModel().getValueAt(i, j) + " ");
								}
							}
						}
							
						bw.close();
						fw.close();
				
						JOptionPane.showMessageDialog(null, "Account Deleted");
					}
				
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					System.out.println("Delete Error");
				}
			}
		});
		
		table1.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mouseClicked(MouseEvent e)
			{   
				chgStatus.addActionListener(new ActionListener()
				{        
					@Override
					public void actionPerformed(ActionEvent e)
					{
						int selectedRow = table1.getSelectedRow();
						
						if(( (String)table1.getValueAt(selectedRow, 6)).
							compareTo(itProject.active) == 0)
						{	
							model1.setValueAt("SUSPENDED", selectedRow, 6);

							table1.revalidate();
							table1.repaint();
							
							JOptionPane.showMessageDialog(null, "Account Status Changed");
							
							table1.clearSelection();
						}
						
						else if(( (String)table1.getValueAt(selectedRow, 6)).
							compareTo(itProject.suspend) == 0)
						{
							model1.setValueAt("ACTIVE", selectedRow, 6);
							
							table1.revalidate();
							table1.repaint();
							
							JOptionPane.showMessageDialog(null, "Account Status Changed");
							
							table1.clearSelection();
						}
					
						try
						{
							File file = new File("loginDetail.txt");
							if(!file.exists())
							{
								file.createNewFile();
							}
								
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
								
							for(int i = 0 ; i < table1.getRowCount(); i++)
							{
								for(int j = 0; j < table1.getColumnCount(); j++)
								{
									if(j != 1 && i == (table1.getRowCount() - 1) && 
									   j == (table1.getColumnCount() - 1))
									{
										bw.write(table1.getModel().getValueAt(i, j) + "");
									}
											
									else if(j != 1 && j == (table1.getColumnCount() - 1))
									{
										bw.write(table1.getModel().getValueAt(i, j) + "\n");
									}
											
									else if(j != 1)
									{
										bw.write(table1.getModel().getValueAt(i, j) + " ");
									}
								}
							}
									
							bw.close();
							fw.close();
						}
						
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}			
				});
			}
		});

		
		chgExit.addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				try
					{
						File file = new File("loginDetail.txt");
						if(!file.exists())
						{
							file.createNewFile();
						}
						
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						
						for(int i = 0 ; i < table1.getRowCount(); i++)
						{
							for(int j = 0; j < table1.getColumnCount(); j++)
							{
								if(j != 1 && i == (table1.getRowCount() - 1) && 
								   j == (table1.getColumnCount() - 1))
								{
									bw.write(table1.getModel().getValueAt(i, j) + "");
								}
								
								else if(j != 1 && i == itProject.position &&
								        (j == table1.getColumnCount() - 2))
								{
									bw.write(dft.format(now) + " ");
								}
									
								else if(j != 1 && j == (table1.getColumnCount() - 1))
								{
									bw.write(table1.getModel().getValueAt(i, j) + "\n");
								}
									
								else if(j != 1)
								{
									bw.write(table1.getModel().getValueAt(i, j) + " ");
								}
							}
						}
							
						bw.close();
						fw.close();
					}
				
					catch(Exception ex)
					{
						ex.printStackTrace();
					}

				dispose();
            }
		});
	}
}

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
    public void ReadAllEvent(String currentID)
	{
		userID = currentID;
		
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
        
		logoutButton.addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				ReadTxtFile.lastLogout.set(itProject.position, dft.format(now));
				SaveTxtFile stf = new SaveTxtFile();
				stf.openFile();
				stf.addRecords();
				stf.closeFile();

				dispose();
            }
		});
		
        workPanel.add (logoutButton);

        // Add in Account Button
        accountButton = new JButton("Account Settings");
        accountButton.setBounds(50, 220, 150, 30);
        accountButton.setBackground(Color.WHITE);
		
		accountButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				accountManagement accMng = new accountManagement(userID);
				accMng.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				accMng.setLocationRelativeTo(null);
				accMng.setSize(680, 420);
				accMng.setVisible(true);
			
				workPanel.add (accountButton);
			}
		});
		
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
				
		approveButton.addActionListener(new ActionListener()
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
				sysAdmin.ReadAllEvent(userID);
				sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
				sysAdmin.setSize(720, 550);
				sysAdmin.setVisible(true);
						
				dispose();
			}
		});
				
		workPanel.add (approveButton);
				
		// Add in venue button
		venueButton = new JButton("Check Venue Availability");
		venueButton.setFont(new Font(venueButton.getFont().getName(), Font.PLAIN, 16));
		venueButton.setBounds(340, 460, 240, 35);
				
		venueButton.addActionListener(new ActionListener()
		{       
			@Override
			public void actionPerformed(ActionEvent event)
			{
				systemAdminFrame sysAdmin = new systemAdminFrame();
				sysAdmin.ReadAllEvent(userID);
				sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
				sysAdmin.setSize(720, 810);
				sysAdmin.setVisible(true);
						
				dispose();
			}
		});
				
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
		endDate = new JLabel("End Date (ddmmyyyy): ");
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
				
		confirmButton.addActionListener(new ActionListener()
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
		});
				
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
}

class accountManagement extends JFrame
{
	private final JTextField textFieldMng1;
	static String newPassword;
	
	accountManagement(String password)
	{		
		// Frame Name
		super("Account Management");
		
		setLayout(null);
		setLocationRelativeTo(null);
		
		// UOW logo icon
		ImageIcon uowIcon = new ImageIcon("uow.jpg");
		JLabel labelUOW = new JLabel(uowIcon);
		labelUOW.setBounds(30, 20, 600, 198);
		
		JLabel labelMng1 = new JLabel("Account Management"); 
		labelMng1.setFont(new Font("SansSerif", Font.BOLD, 17));
		labelMng1.setBounds(250, 210, 300, 50);
		
		JLabel labelMng2 = new JLabel("ID:   " + password); 
		labelMng2.setFont(new Font("SansSerif",Font.PLAIN, 14));
		labelMng2.setBounds(174, 250, 300, 20);
		
		JLabel labelMng3 = new JLabel("Email:   " + ReadTxtFile.email.get(itProject.position)); 
		labelMng3.setFont(new Font("SansSerif",Font.PLAIN, 14));
		labelMng3.setBounds(152, 280, 300, 20);
		
		JLabel labelMng4 = new JLabel("Change Password To: "); 
		labelMng4.setFont(new Font("SansSerif",Font.PLAIN, 14));
		labelMng4.setBounds(50, 310, 300, 20);
		
		textFieldMng1 = new JTextField(10);
		textFieldMng1.setBounds(200, 310, 200, 20);
		
		JButton buttonMng1 = new JButton();
		buttonMng1.setText("Save");
		buttonMng1.setBounds(280, 350, 100, 20);
		ButtonHandler handler = new ButtonHandler();
		buttonMng1.addActionListener(handler);
		
		add(labelUOW);
		add(labelMng1);
		add(labelMng2);
		add(labelMng3);
		add(labelMng4);
		add(textFieldMng1);
		add(buttonMng1);
	}
	
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{	
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
				
			ReadTxtFile.lastLogout.set(itProject.position, dft.format(now));
			
			newPassword = String.valueOf(textFieldMng1.getText());
			
			ReadTxtFile.password.set(itProject.position, newPassword);
		
			SaveTxtFile stf = new SaveTxtFile();
			stf.openFile();
			stf.addRecords();
			stf.closeFile();
			
			dispose();
		}
	}
}

class staffBooking extends JFrame
{
    staffBooking(String password)
    {
        super("Staff Events Creation System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1335, 630);
		setVisible(true);
        JTable table = new JTable()
		{
			public boolean isCellEditable(int row, int column)
			{
				return column == 0 || column == 1 || column == 2 || column == 3 ||
				column == 4 || column == 5 || column == 6;
			}
		};
        
        Object[] columns = {"Event Name", "Price", "Start Date", "Total Days", "Time",
							"Venue", "Capacities", "Public/Private", "Tickets Booked"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(250);
		colModel.getColumn(5).setPreferredWidth(150);


		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(25);
        
		JLabel lblTitle1 = new JLabel("Events List");
		JLabel lblTitle2 = new JLabel("New Event Details");
		
		Font titleFont = new Font("SansSerif", Font.PLAIN, 20);
		lblTitle1.setFont(titleFont);
		lblTitle2.setFont(titleFont);
		
		JLabel lblName = new JLabel("Event Name:");
		JLabel lblPrice = new JLabel("Price:");
		JLabel lblDate = new JLabel("Start Date:");
		JLabel lblDay = new JLabel("Total Days:");
        JLabel lblTime = new JLabel("Time:");
        JLabel lblVenue = new JLabel("Venue:");
        JLabel lblCapacity = new JLabel("Capacities:");
        
		JTextField txtName = new JTextField();
		JTextField txtPrice = new JTextField();
        JTextField txtDate = new JTextField();
		JTextField txtDay = new JTextField();
        JTextField txtTime = new JTextField();
        JTextField txtVenue = new JTextField();
        JTextField txtCapacity = new JTextField();
          
        JButton btnCreate = new JButton("Create");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnExit = new JButton("Logout");
		JButton btnAcc = new JButton("Account");
        
		lblTitle1.setBounds(600, 10, 150, 25);
		lblTitle2.setBounds(570, 280, 250, 25);
		
        lblName.setBounds(90, 320, 100, 25);
        lblPrice.setBounds(490, 320, 100, 25);
        lblDate.setBounds(860, 320, 100, 25);
        lblDay.setBounds(90, 390, 100, 25);
        lblTime.setBounds(490, 390, 100, 25);
        lblVenue.setBounds(860, 390, 100, 25);
        lblCapacity.setBounds(90, 460, 100, 25);
                  
        txtName.setBounds(200, 320, 200, 25);
        txtPrice.setBounds(580, 320, 200, 25);
        txtDate.setBounds(960, 320, 200, 25);
        txtDay.setBounds(200, 390, 200, 25);
        txtTime.setBounds(580, 390, 200, 25);
        txtVenue.setBounds(960, 390, 200, 25);
        txtCapacity.setBounds(200, 460, 200, 25);
                
        btnAcc.setBounds(200, 540, 100, 25);
        btnCreate.setBounds(400, 540, 100, 25);
        btnEdit.setBounds(600, 540, 100, 25);
        btnDelete.setBounds(800, 540, 100, 25);
		btnExit.setBounds(1000, 540, 100, 25);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 40, 1300, 230);
        
        setLayout(null);
        
        add(pane);
		
        add(lblTitle1);
		add(lblTitle2);
		
        add(lblDate);
        add(lblTime);
        add(lblDay);
        add(lblName);
        add(lblPrice);
        add(lblCapacity);
        add(lblVenue);
        
        add(txtDate);
        add(txtTime);
        add(txtDay);
        add(txtName);
        add(txtPrice);
        add(txtCapacity);
        add(txtVenue);
        
        add(btnCreate);
        add(btnEdit);
        add(btnDelete);
        add(btnExit);
		add(btnAcc);
       
		Object[]in = new Object[9];
		try
		{
			Scanner input = new Scanner (new File ("eventsDetail.txt"));
			
			while(input.hasNextLine())
			{
				String aLine = input.nextLine();
				
				String [] Line = aLine.split(", ");
				
				in[0] = Line[0];
				in[1] = Line[1];
				in[2] = Line[2];
				in[3] = Line[3];
				in[4] = Line[4];
				in[5] = Line[5];
				in[6] = Line[6];
				in[7] = Line[7];
				in[8] = Line[8];
					
				model.addRow(in);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		
		btnAcc.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				accountManagement accMng = new accountManagement(password);
				accMng.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				accMng.setLocationRelativeTo(null);
				accMng.setSize(680, 420);
				accMng.setVisible(true);
			}
		});
		
        Object[] row = new Object[9];
        btnCreate.addActionListener(new ActionListener()
		{    
            @Override
            public void actionPerformed(ActionEvent e)
			{
				if(txtName.getText().equals("") || txtPrice.getText().equals("") || txtDate.getText().equals("") ||
				   txtDay.getText().equals("") || txtTime.getText().equals("") || txtVenue.getText().equals("") ||
				   txtCapacity.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "One or More Field/s are Empty");
				}
				
				else 
				{
					row[0] = txtName.getText();
					row[1] = txtPrice.getText();
					row[2] = txtDate.getText();
					row[3] = txtDay.getText();
					row[4] = txtTime.getText();
					row[5] = txtVenue.getText();
					row[6] = txtCapacity.getText();
					row[7] = "Private";
					row[8] = 0;
                
					model.addRow(row);
					
					JOptionPane.showMessageDialog(null,"You have saved/created an event");
					
					txtName.setText("");
					txtPrice.setText("");
					txtDate.setText("");
					txtDay.setText("");
					txtTime.setText("");
					txtVenue.setText("");
					txtCapacity.setText("");
				}
                
                try
				{
					File file = new File("eventsDetail.txt");
					if(!file.exists())
					{
						file.createNewFile();
					}
                
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter  bw = new BufferedWriter(fw);
                
					for(int i = 0 ; i < table.getRowCount(); i++)
					{
						for(int j = 0; j < table.getColumnCount(); j++)
						{
							if(i == (table.getRowCount() - 1) && j == (table.getColumnCount() - 1))
							{
								bw.write(table.getModel().getValueAt(i, j) + "");
							}
							
							else if(j == (table.getColumnCount() - 1))
							{
								bw.write(table.getModel().getValueAt(i, j) + "\n");
							}
							
							else
							{
								bw.write(table.getModel().getValueAt(i, j) + ", ");
							}
						}
					}
					
					bw.close();
					fw.close();
                }
				
				catch(Exception ex)
				{
                    ex.printStackTrace();
                }
            }
        });
           
        btnDelete.addActionListener(new ActionListener()
		{        
            @Override
            public void actionPerformed(ActionEvent e)
			{          
				int z = table.getSelectedRow();
				if(z >= 0)
				{
					model.removeRow(z);
					
					try
					{
						File file = new File("eventsDetail.txt");
						if(!file.exists())
						{
							file.createNewFile();
						}
					
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter  bw = new BufferedWriter(fw);
                
						for(int i = 0 ; i < table.getRowCount(); i++)
						{
							for(int j = 0; j < table.getColumnCount(); j++)
							{
								if(i == (table.getRowCount() - 1) && j == (table.getColumnCount() - 1))
								{
									bw.write(table.getModel().getValueAt(i, j) + "");
								}
								
								else if(j == (table.getColumnCount() - 1))
								{
									bw.write(table.getModel().getValueAt(i, j) + "\n");
								}
								
								else
								{
									bw.write(table.getModel().getValueAt(i, j) + ", ");
								}
							}
						}
					
						bw.close();
						fw.close();
						JOptionPane.showMessageDialog(null, "Event Deleted");
					}
				
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					System.out.println("Delete Error");
				}
			}
		});
           
        
        btnExit.addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				ReadTxtFile.lastLogout.set(itProject.position, dft.format(now));
				SaveTxtFile stf = new SaveTxtFile();
				stf.openFile();
				stf.addRecords();
				stf.closeFile();
				
				dispose();
            }
		});
        
        btnEdit.addActionListener(new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                try
				{
					File file = new File("eventsDetail.txt");
					if(!file.exists())
					{
						file.createNewFile();
					}
                
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter  bw = new BufferedWriter(fw);
                
					for(int i = 0 ; i < table.getRowCount(); i++)
					{
						for(int j = 0; j < table.getColumnCount(); j++)
						{
							if(i == (table.getRowCount() - 1) && j == (table.getColumnCount() - 1))
							{
								bw.write(table.getModel().getValueAt(i, j) + "");
							}
							
							else if(j == (table.getColumnCount() - 1))
							{
								bw.write(table.getModel().getValueAt(i, j) + "\n");
							}
								
							else
							{
								bw.write(table.getModel().getValueAt(i, j) + ", ");
							}
						}
					}
					
					bw.close();
					fw.close();
					JOptionPane.showMessageDialog(null, "You updated the event details");
					table.clearSelection();
                }
				
				catch(Exception ex)
				{
                    ex.printStackTrace();
                }
            }  
        });
    }
}

class ReadTxtFile
{
	private static Scanner input;
	static ArrayList <String> loginID = new ArrayList <String> ();
	static ArrayList <String> password = new ArrayList <String> ();
	static ArrayList <String> email = new ArrayList <String> ();
	static ArrayList <String> lastLogin = new ArrayList <String> ();
	static ArrayList <String> lastLogout = new ArrayList <String> ();
	static ArrayList <String> isSuspended = new ArrayList <String> ();
	
	public void openFile()
	{
		try
		{
			input = new Scanner(Paths.get("loginDetail.txt"));
		}
		
		catch(IOException ioException)
		{
			System.err.println("Error in opening file. Terminating...");
			System.exit(1);
		}
	}
	
	public void readRecords()
	{		
		try
		{
			while(input.hasNext())
			{
				if(loginID.size() == 0)
				{
					loginID.add(input.next());
					password.add(input.next());
					email.add(input.next());
					lastLogin.add(input.next());
					lastLogout.add(input.next());
					isSuspended.add(input.next());
				}
				
				else
				{
					for(int i = 0; i < loginID.size(); i++)
					{
						loginID.set(i, input.next());
						password.set(i, input.next());
						email.set(i, input.next());
						lastLogin.set(i, input.next());
						lastLogout.set(i, input.next());
						isSuspended.set(i, input.next());
					}
					
					while(input.hasNext())
					{
						loginID.add(input.next());
						password.add(input.next());
						email.add(input.next());
						lastLogin.add(input.next());
						lastLogout.add(input.next());
						isSuspended.add(input.next());
					}
				}
			}
		}
		
		catch(NoSuchElementException elementException)
		{
			System.err.println("File improperly formed. Terminating...");
		}
		
		catch(IllegalStateException stateException)
		{
			System.err.println("Error reading from file. Terminating...");
		}
	}
	
	public void closeFile()
	{
		if(input != null)
		{
			input.close();
		}
	}
}

class SaveTxtFile
{
	private static Formatter output;
	
	public void openFile()
	{
		try
		{
			output = new Formatter("loginDetail.txt");
		}
		
		catch(SecurityException securityException)
		{
			System.err.println("Write permission denied. Terminating...");
			System.exit(1);
		}
		
		catch(FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file. Terminating...");
			System.exit(1);
		}
	}
	
	public void addRecords()
	{		
		try
		{
			for(int i = 0; i < ReadTxtFile.loginID.size(); i++)
			{
				output.format("%s %s %s %s %s %s%n", ReadTxtFile.loginID.get(i), 
							  ReadTxtFile.password.get(i), ReadTxtFile.email.get(i),
							  ReadTxtFile.lastLogin.get(i), ReadTxtFile.lastLogout.get(i),
							  ReadTxtFile.isSuspended.get(i));
			}
		}
		
		catch(FormatterClosedException formatterClosedException)
		{
			System.err.println("Error writing to file. Terminating...");
			System.exit(1);
		}
	}
	
	public void closeFile()
	{
		if(output != null)
		{
			output.close();
		}
	}
}

class StudentFrame extends JFrame
{
	// Declaration
	private final JLabel picLabel;
	private final ImageIcon logo;
	private final JButton[] button = new JButton[5];
	
	// StudentFrame constructor to add in Picture and Button
	public StudentFrame(String pwd)
	{
		super("UOW Events Booking System");
		setLayout (null);
		setLocationRelativeTo(null);
		
		String currentPass = pwd;
		
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
		
		button[0].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				accountManagement accMng = new accountManagement(currentPass);
				accMng.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				accMng.setLocationRelativeTo(null);
				accMng.setSize(680, 420);
				accMng.setVisible(true);
				accMng.setLocationRelativeTo(null);
			}
		});
		
		// Add in Event Booking Button
		button[1] = new JButton ("Event Listing");
		button[1].setBounds(85, 300, 150, 40);
		add (button[1]);
		
		button[1].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentEvent studentEvent = new StudentEvent();
				studentEvent.readFile(currentPass);
				studentEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				studentEvent.setContentPane(studentEvent.createContentPane());
				studentEvent.setSize(650, 700);
				studentEvent.setVisible(true);
				studentEvent.setLocationRelativeTo(null);
			
				dispose();
			}
		});
		
		// Add in Cart Button
		button[2] = new JButton ("Shopping Cart");
		button[2].setBounds(285, 300, 150, 40);
		add (button[2]);
		
		button[2].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CartFrame cartFrame = new CartFrame(currentPass);
				cartFrame.ReadAllEvent();
				cartFrame.readCartDetails();
				cartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cartFrame.setContentPane(cartFrame.createCartFrame());
				cartFrame.setSize(650, 700);
				cartFrame.setVisible(true);
				cartFrame.setLocationRelativeTo(null);
					
				dispose();
			}
		});
		
		// Add in Booked Event Button
		button[3] = new JButton ("View Booked Event");
		button[3].setBounds(485, 300, 150, 40);
		add (button[3]);
		
		button[3].addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				BookedEvent bookedEvent = new BookedEvent(currentPass);
				bookedEvent.ReadAllEvent();
				bookedEvent.readBookedEvent();
				
				if(BookedEvent.bookedDetails.size() != 0)
				{
					bookedEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					bookedEvent.setContentPane(bookedEvent.createBookedPane(currentPass));
					bookedEvent.setSize(650, 800);
					bookedEvent.setVisible(true);
					bookedEvent.setLocationRelativeTo(null);
					dispose();
				}
				
				else 
				{
					JOptionPane.showMessageDialog(null, "No event booked.");
				}
            }
		});
		
		// Add in Log Out Button
		button[4] = new JButton ("Logout");
		button[4].setBounds(520, 240, 150, 30);
		button[4].setBackground(Color.WHITE);
		add (button[4]);
		
		button[4].addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				ReadTxtFile.lastLogout.set(itProject.position, dft.format(now));
				SaveTxtFile stf = new SaveTxtFile();
				stf.openFile();
				stf.addRecords();
				stf.closeFile();
				
				dispose();
            }
		});	
	}
}

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
	public void readFile(String pwd)
	{
		userID = pwd;
		
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
		eventList.setSelectedIndex(0);
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
			StudentFrame studentFrame = new StudentFrame(userID);
			studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

class BookedEvent extends JFrame
{
	// Declaration
	String userID;
	ArrayList <String> eventString = new ArrayList <String>();
	ArrayList <ArrayList<String>> eventDetails = new ArrayList<ArrayList<String>>();
	ArrayList <String> bookedString = new ArrayList <String>();
	static ArrayList <ArrayList<String>> bookedDetails = new ArrayList<ArrayList<String>>();
	ArrayList <ArrayList<String>> newEventDetails = new ArrayList<ArrayList<String>>();
	ArrayList <ArrayList<String>> newBookedDetails = new ArrayList<ArrayList<String>>();
	String[] bookedNameArr;
	private JLabel picLabel;
	private ImageIcon logo;
	private JLabel label1;
	private JButton backButton;
	private JList <String> bookedList;
	private JTextArea bookedStuff;
	private JLabel quanLabel;
	private JButton[] quanButton = new JButton[2];
	private JTextField quanField;
	private JButton confirmButton;
	private JTextArea infoLabel;
	
	// Reading in Event List - Public
	public void ReadAllEvent()
	{	
		eventDetails.clear();
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
	
	// Reading in Full Event List
	public void ReadFullEvent()
	{
		newEventDetails.clear();
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
				newEventDetails.add(tempData);
		}
	}
	
	// Reading in User Booked Event
	public void readBookedEvent()
	{
		bookedDetails.clear();
		
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
	
	// Read in Full Booked Event List
	public void readFullBookedEvent()
	{
		newBookedDetails.clear();
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
			
			newBookedDetails.add(tempData);
		}
	}
	
	// Writing to eventDetail.txt
	public void updateAllEvent(String eventName, int ticketQuantity)
	{
		ReadFullEvent();
		
		for (int i = 0; i < newEventDetails.size(); i++)
		{
			if (eventName.equals(newEventDetails.get(i).get(0)))
			{
				int tempVar = Integer.parseInt(newEventDetails.get(i).get(8));
				newEventDetails.get(i).set(8, Integer.toString(tempVar - ticketQuantity));
				
				break;
			}
		}
		
		String outputString = "";
		
		try 
		{		
			FileWriter fileWrite = new FileWriter("eventsDetail.txt", false);
			PrintWriter printWriter = new PrintWriter(fileWrite);
			
			for (int i = 0; i < newEventDetails.size(); i++)
			{
				outputString = newEventDetails.get(i).get(0);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(1);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(2);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(3);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(4);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(5);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(6);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(7);
				outputString += ", ";
				outputString += newEventDetails.get(i).get(8);
				
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
	
	// Writing to bookedEvents.txt
	public void updateBookedEvent (String eventName, int ticketQuantity)
	{
		readFullBookedEvent();
		
		for (int i = 0; i < newBookedDetails.size(); i++)
		{
			if ((eventName.equals(newBookedDetails.get(i).get(1))) && (userID.equals(newBookedDetails.get(i).get(0))))
			{
				int tempVar = Integer.parseInt(newBookedDetails.get(i).get(2));
				int tempInt = tempVar - ticketQuantity;
				
				if (tempInt == 0)
				{
					newBookedDetails.remove(i);
				}
				
				else
				{
					newBookedDetails.get(i).set(2, Integer.toString(tempVar - ticketQuantity));
				}
				
				break;
			}
		}
		
		String outputString = "";
		
		try
		{
			FileWriter fileWriter = new FileWriter("bookedEvents.txt", false);
			PrintWriter printWriterBooked = new PrintWriter(fileWriter);
			
			for (int i = 0; i < newBookedDetails.size(); i++)
			{
				outputString = newBookedDetails.get(i).get(0);
				outputString += ", ";
				outputString += newBookedDetails.get(i).get(1);
				outputString += ", ";
				outputString += newBookedDetails.get(i).get(2);
				
				printWriterBooked.println(outputString);
			}
			
			printWriterBooked.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error");
			System.exit(1);
		}
	}
	
	// Construct an instance of BookedEvent
	public BookedEvent(String pwd)
	{
		super("UOW Events Booking System");
		
		userID = pwd;
	}
	
	// Create BookedEvent Frame
	public Container createBookedPane(String pwd)
	{
		JPanel backPanel = new JPanel();

		JPanel frontPanel = new JPanel();
		frontPanel.setLayout(null);
		
		// Add in UOW Logo
		logo = new ImageIcon ("uow.jpg");
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
		bookedList.setSelectedIndex(0);
		JScrollPane scrollBPane = new JScrollPane(bookedList);
		scrollBPane.setBounds(30, 285, 400, 150);
		frontPanel.add (scrollBPane);
		
		// Add in Back Button
		backButton = new JButton("Return to Main Menu");
		backButton.setBounds(400, 210, 180, 50);
		backButton.addActionListener(new BackButtonHandler());
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
		
		else
		{
			JOptionPane.showMessageDialog(null, "It's Empty. You can start booking for events.");
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
				
				quanButton[0].setEnabled(true);
				quanButton[1].setEnabled(false);
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
		quanButton[0].addActionListener(new minusButtonHandler());
		frontPanel.add(quanButton[0]);
		
		quanButton[1] = new JButton ("+");
		quanButton[1].setBounds(225, 550, 45, 20);
		quanButton[1].setBackground(Color.WHITE);
		quanButton[1].addActionListener(new plusButtonHandler());
		quanButton[1].setEnabled(false);
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
		confirmButton.addActionListener(new confirmButtonHandler());
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
	
	// Adding ActionListener for the buttons
	private class BackButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StudentFrame studentFrame = new StudentFrame(userID);
			studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			studentFrame.setSize(720, 420);
			studentFrame.setVisible(true);
			
			dispose();
		}
	}
	
	private class minusButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int tempQuan;
			tempQuan = Integer.parseInt(quanField.getText());
			
			quanButton[1].setEnabled(true);
			
			quanField.setText(Integer.toString(tempQuan - 1));
			
			if (tempQuan == 1)
			{
				quanButton[0].setEnabled(false);
			}
		}
	}
	
	private class plusButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int tempQuan;
			int maxQuan;
			int tempInd;
			
			tempInd = bookedList.getSelectedIndex();
			maxQuan = Integer.parseInt(bookedDetails.get(tempInd).get(2));
			tempQuan = Integer.parseInt(quanField.getText());
			
			quanButton[0].setEnabled(true);
			
			quanField.setText(Integer.toString(tempQuan + 1));
			
			if (tempQuan == maxQuan - 1)
			{
				quanButton[1].setEnabled(false);
			}
		}
	}
	
	private class confirmButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int maxQuan;
			int tempInd;
			
			tempInd = bookedList.getSelectedIndex();
			maxQuan = Integer.parseInt(bookedDetails.get(tempInd).get(2));
			
			int newQuan;
			newQuan = Integer.parseInt(quanField.getText());
			
			String eventName;
			int ticketQuantity;
			ticketQuantity = maxQuan - newQuan;
			eventName = bookedDetails.get(tempInd).get(1);
			
			updateAllEvent(eventName, ticketQuantity);
			updateBookedEvent(eventName, ticketQuantity);
			
			JOptionPane.showMessageDialog(null, "Ticket quantity updated!");
			
			BookedEvent bookedEvent = new BookedEvent(userID);
			bookedEvent.ReadAllEvent();
			bookedEvent.readBookedEvent();
			bookedEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			bookedEvent.setContentPane(bookedEvent.createBookedPane(userID));
			bookedEvent.setSize(650, 800);
			bookedEvent.setVisible(true);
			
			dispose();
		}
	}
}

class CartFrame extends JFrame
{
	// Declaration
	Boolean havePromo = false;
	String userID;
	ArrayList <String> eventString = new ArrayList <String>();
	ArrayList <ArrayList<String>> eventDetails = new ArrayList<ArrayList<String>>();
	ArrayList <String> cartString = new ArrayList <String>();
	ArrayList <ArrayList<String>> cartDetails = new ArrayList<ArrayList<String>>();
	ArrayList <ArrayList<String>> newCartDetails = new ArrayList<ArrayList<String>>();
	private JLabel picLabel;
	private ImageIcon logo;
	private JLabel label1;
	private JTable eventTable;
	private JButton backButton;
	private JLabel priceLabel;
	private JTextField priceField;
	private JTextField promoField;
	private JButton promoButton;
	private JButton paymentButton;
	private JLabel infoLabel;
	
	// Reading in Event List
	public void ReadAllEvent()
	{	
		eventString = new ArrayList <String>();
		eventDetails = new ArrayList<ArrayList<String>>();
	
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
			
			eventDetails.add(tempData);
		}
	}
	
	// Reading in User Cart Details
	public void readCartDetails()
	{
		cartString = new ArrayList <String>();
		cartDetails = new ArrayList<ArrayList<String>>();
		
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
	
	// Read in Full Cart Details
	public void readFullCartList()
	{

		newCartDetails = new ArrayList<ArrayList<String>>();
		
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
			
			newCartDetails.add(tempData);
		}
	}
	
	// Writing to eventDetail.txt
	public void updateEvent(String eventName, int ticketQuantity)
	{
		ReadAllEvent();
		
		for (int i = 0; i < eventDetails.size(); i++)
		{
			if (eventName.equals(eventDetails.get(i).get(0)))
			{
				int tempVar = Integer.parseInt(eventDetails.get(i).get(8));
				eventDetails.get(i).set(8, Integer.toString(tempVar + ticketQuantity));
				
				break;
			}
		}
		
		String outputString = "";
		
		try 
		{		
			FileWriter fileWrite = new FileWriter("eventsDetail.txt", false);
			PrintWriter printWriter = new PrintWriter(fileWrite);
			
			for (int i = 0; i < eventDetails.size(); i++)
			{
				outputString = eventDetails.get(i).get(0);
				outputString += ", ";
				outputString += eventDetails.get(i).get(1);
				outputString += ", ";
				outputString += eventDetails.get(i).get(2);
				outputString += ", ";
				outputString += eventDetails.get(i).get(3);
				outputString += ", ";
				outputString += eventDetails.get(i).get(4);
				outputString += ", ";
				outputString += eventDetails.get(i).get(5);
				outputString += ", ";
				outputString += eventDetails.get(i).get(6);
				outputString += ", ";
				outputString += eventDetails.get(i).get(7);
				outputString += ", ";
				outputString += eventDetails.get(i).get(8);
				
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
	
	// Writing to cartDetails.txt
	public void updateCart(String eventName, int ticketQuantity)
	{
		readCartDetails();
		readFullCartList();
		
		for (int i = 0; i < newCartDetails.size(); i++)
		{
			if ((eventName.equals(newCartDetails.get(i).get(1))) && (userID.equals(newCartDetails.get(i).get(0))))
			{
				int tempVar = Integer.parseInt(newCartDetails.get(i).get(2));
				int tempInt = tempVar - ticketQuantity;
				
				if (tempInt == 0)
				{
					newCartDetails.remove(i);
				}
				
				else
				{
					newCartDetails.get(i).set(2, Integer.toString(tempVar - ticketQuantity));
				}
				
				break;
			}
		}
		
		String outputString = "";
		
		try
		{
			FileWriter fileWriter = new FileWriter("cartDetails.txt", false);
			PrintWriter printWriterCart = new PrintWriter(fileWriter);
			
			for (int i = 0; i < newCartDetails.size(); i++)
			{
				outputString = newCartDetails.get(i).get(0);
				outputString += ", ";
				outputString += newCartDetails.get(i).get(1);
				outputString += ", ";
				outputString += newCartDetails.get(i).get(2);
				
				printWriterCart.println(outputString);
			}
			
			printWriterCart.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error");
			System.exit(1);
		}
	}
	
	// Writing to bookedEvents.txt
	public void updateBooked(String eventName, int ticketQuantity)
	{
		if (ticketQuantity != 0)
		{
			String outputString = "";
			outputString = userID;
			outputString += ", ";
			outputString += eventName;
			outputString += ", ";
			outputString += Integer.toString(ticketQuantity);
			
			try
			{		
				FileWriter fileWriter = new FileWriter("bookedEvents.txt", true);
				PrintWriter printWriterBooked = new PrintWriter(fileWriter);
				printWriterBooked.println(outputString);
				printWriterBooked.close();
			}
			
			catch (IOException e)
			{
				System.out.println("Error");
				System.exit(1);
			}
		}
	}
	
	// Changing jTable Column size
	protected void setupTable(JTable table) 
	{
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(230);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.setRowHeight(40);
	}
	
	// Construct an instance of CartFrame
	public CartFrame(String pwd)
	{
		super("UOW Events Booking System");
		
		userID = pwd;
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
		backButton.addActionListener(new BackButtonHandler());
		activePanel.add (backButton);
		
		// Add in Shopping Cart Label
		label1 = new JLabel("Shopping Cart");
		label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
		label1.setBounds(30, 220, 200, 100);
		activePanel.add (label1);
		
		// Creating a table to store Event in Cart
		Object columnName[] = {"Event", "Price ($)", "Slot Left", "Quantity"};
		Object[][] cartData = new Object[cartDetails.size()][4];
		
		// Adding in cart Details
		for (int i = 0; i < cartDetails.size(); i++)
		{	
			// Getting Event Name
			cartData[i][0] = cartDetails.get(i).get(1);
			
			
			// Getting Event Price and Slot Left
			for (int j = 0; j < eventDetails.size(); j++)
			{
				if (cartDetails.get(i).get(1).equals(eventDetails.get(j).get(0)))
				{
					cartData[i][1] = Double.toString(Double.parseDouble(eventDetails.get(j).get(1))
					* Integer.parseInt(cartDetails.get(i).get(2)));
					cartData[i][2] = Integer.toString(Integer.parseInt(eventDetails.get(j).get(6))
					- Integer.parseInt(eventDetails.get(j).get(8)));
					break;
				}
			}
			
			// Getting Ticket Quantity user Booked
			cartData[i][3] = cartDetails.get(i).get(2);
		}
		
		DefaultTableModel cartModel = new DefaultTableModel(cartData, columnName);
		
		eventTable = new JTable(cartModel)
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
		
		eventTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent event)
			{
				int selectedIndex[] = eventTable.getSelectedRows();
				
				String getData;
				double totalPrice = 0;
				
				for (int j = 0; j < selectedIndex.length; j++)
				{					
					getData = String.valueOf(eventTable.getModel().getValueAt(selectedIndex[j], 1));
					totalPrice += Double.parseDouble(getData);
				}
				
				if (havePromo)
				{
					totalPrice = (totalPrice / 100) * 85;
				}
				
				String price = "$";
				price += String.format("%.2f", totalPrice);
				
				priceField.setText(price);
			}
			
		});
		
		setupTable(eventTable);
		
		JScrollPane eventScroll = new JScrollPane(eventTable);
		eventScroll.setBounds(30, 290, 540, 140);
		activePanel.add(eventScroll);
		
		// Add in Price Label
		priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font(priceLabel.getFont().getName(), Font.PLAIN, 20));
		priceLabel.setBounds(30, 450, 200, 100);
		activePanel.add (priceLabel);
		
		// Add in Price TextField	
		priceField = new JTextField("$0.00");
		priceField.setEditable(false);
		priceField.setBackground(Color.WHITE);
		priceField.setFont(new Font(priceField.getFont().getName(), Font.PLAIN, 20));
		priceField.setBounds(95, 480, 90, 40);
		activePanel.add (priceField);
		
		// Add in Promo TextField
		promoField = new JTextField("Enter Code Here");
		promoField.setFont(new Font(promoField.getFont().getName(), Font.PLAIN, 16));
		promoField.setBounds(40, 530, 120, 35);
		activePanel.add (promoField);
		
		// Add in Promo Button
		promoButton = new JButton("Enter Promo Code");
		promoButton.setFont(new Font(promoButton.getFont().getName(), Font.PLAIN, 16));
		promoButton.addActionListener(new PromoButtonHandler());
		promoButton.setBounds(165, 530, 200, 35);
		activePanel.add (promoButton);
		
		// Add in Payment Button
		paymentButton = new JButton("Make Payment");
		paymentButton.setFont(new Font(paymentButton.getFont().getName(), Font.PLAIN, 16));
		paymentButton.addActionListener(new PaymentButtonHandler());
		paymentButton.setBounds(390, 570, 180, 35);
		activePanel.add (paymentButton);
		
		// Add in Info Label 
		infoLabel = new JLabel ("Please take note that all events in cart would be purged away after 30mins!");
		infoLabel.setFont(new Font(priceLabel.getFont().getName(), Font.PLAIN, 14));
		infoLabel.setForeground(Color.RED);
		infoLabel.setBounds(30, 400, 480, 100);
		activePanel.add (infoLabel);
		
		Container a = getContentPane();
		a.add(topPanel, BorderLayout.NORTH);
		a.add(activePanel,BorderLayout.CENTER);
		return a;
	}
	
	// Adding in ActionListener for Buttons
	private class BackButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StudentFrame studentFrame = new StudentFrame(userID);
			studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			studentFrame.setSize(720, 420);
			studentFrame.setVisible(true);
			
			dispose();
		}
	}
	
	private class PromoButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (promoField.getText().equals("STUDENT15"))
			{
				havePromo = true;
				
				String temp = priceField.getText().substring(1, priceField.getText().length());

				double current = Double.parseDouble(temp);
				current = (current / 100) * 85;
				
				String price = "$";
				price += String.format("%.2f", current);
				
				priceField.setText(price);
				
				promoButton.setEnabled(false);
				promoField.setEditable(false);
				
				JOptionPane.showMessageDialog(null, "Promo Code Activated! 15 % Off Total Price!");
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Promo Code! Please Re-enter!");
			}
		}
	}
	
	private class PaymentButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int selectedIndex[] = eventTable.getSelectedRows();
				
			String eventName;
			int ticketQuantity;
			
			for (int j = 0; j < selectedIndex.length; j++)
			{					
				eventName = String.valueOf(eventTable.getModel().getValueAt(selectedIndex[j], 0));
				ticketQuantity = Integer.parseInt(String.valueOf(eventTable.getModel().getValueAt(selectedIndex[j], 3)));
				
				updateEvent(eventName, ticketQuantity);
				updateCart(eventName, ticketQuantity);
				updateBooked(eventName, ticketQuantity);
			}
			
			JOptionPane.showMessageDialog(null, "Payment Received! Ticket Successfully Booked!");
			
			CartFrame cartFrame = new CartFrame(userID);
			cartFrame.ReadAllEvent();
			cartFrame.readCartDetails();
			cartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cartFrame.setContentPane(cartFrame.createCartFrame());
			cartFrame.setSize(650, 700);
			cartFrame.setVisible(true);
			
			dispose();
		}
	}
}

class student
{
	student(String userID)
	{
		StudentFrame studentFrame = new StudentFrame(userID);
		studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentFrame.setSize(720, 420);
		studentFrame.setVisible(true);
	}
}

public class itProject extends JFrame
{
	private final JTextField textField1;
	private final JPasswordField passwordField1;
	String idEntered;
	String passwordEntered;
	static int position;
	boolean idCorrect;
	boolean passwordCorrect;
	static String active = "ACTIVE";
	static String suspend = "SUSPENDED";
	
	itProject()
	{	
		// Frame Name
		super("UOW Events Booking System");
		
		// UOW logo icon
		ImageIcon uowIcon = new ImageIcon("uow.jpg");
		JLabel labelUOW = new JLabel(uowIcon);
		
		// Label for making choices as staff or student
		JLabel labelLog1 = new JLabel("Login As: "); 
		
		// Label for login info
		JLabel labelLog2 = new JLabel("Login ID: ");
		JLabel labelLog3 = new JLabel("Password: ");
		
		// Box to type login info
		textField1 = new JTextField(10);
		passwordField1 = new JPasswordField(10);
		
		// Button to confirm login
		JButton buttonLog1 = new JButton();
		buttonLog1.setText("Login");
		ButtonHandler handler = new ButtonHandler();
		buttonLog1.addActionListener(handler);
		
		// Note for user
		JLabel labelLog4 = new JLabel("Please note that this applicaiton" +
									  " is ONLY for CSIT214 - IT Project Management.");
		labelLog4.setFont(new Font("Courier New", Font.ITALIC, 12));
		labelLog4.setForeground(Color.RED);
		
		// Setting the UI layout and adding item to it
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		add(labelUOW);
		add(labelLog2);
		add(textField1);
		add(labelLog3);
		add(passwordField1);
		add(buttonLog1);
		add(labelLog4);
	}
	
	private class ButtonHandler implements ActionListener
	{
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
	
		@Override
		public void actionPerformed(ActionEvent event)
		{
			idEntered = String.valueOf(textField1.getText());
			passwordEntered = String.valueOf(passwordField1.getPassword());
			
			ReadTxtFile rtf = new ReadTxtFile();
			rtf.openFile();
			rtf.readRecords();
			rtf.closeFile();
			
			for(int i = 0; i < ReadTxtFile.loginID.size(); i++)
			{
				if(idEntered.compareTo(ReadTxtFile.loginID.get(i)) == 0)
				{
					idCorrect = true;
					position = i;
				}
			}
			
			if(passwordEntered.compareTo(ReadTxtFile.password.get(position)) == 0)
			{
				passwordCorrect = true;
			}
			
			if(textField1.getText().isEmpty() && passwordField1.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(itProject.this,
				String.format("Please fill in your Login ID and password.", event.getActionCommand()));
			}
			
			else if(textField1.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(itProject.this,
				String.format("Please fill in your login ID.", event.getActionCommand()));
			}
				
			else if(passwordField1.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(itProject.this,
				String.format("Please fill in your password.", event.getActionCommand()));
			}
	
			else if(ReadTxtFile.isSuspended.get(position).compareTo(active) == 0 && 
					idCorrect == true && passwordCorrect == true)
			{
				if(idEntered.charAt(0) == '6')
				{	
					student std = new student(idEntered);
					passwordCorrect = false;
					
					ReadTxtFile.lastLogin.set(position, dft.format(now));
					
					SaveTxtFile stf0 = new SaveTxtFile();
					stf0.openFile();
					stf0.addRecords();
					stf0.closeFile();
				}
				
				else if(idEntered.charAt(0) == '1')
				{
					staffBooking stf = new staffBooking(idEntered);
					passwordCorrect = false;
					
					ReadTxtFile.lastLogin.set(position, dft.format(now)); 

					SaveTxtFile stf1 = new SaveTxtFile();
					stf1.openFile();
					stf1.addRecords();
					stf1.closeFile();
				}
				
				else if(idEntered.charAt(0) == '0')
				{
					userAdmin userAd = new userAdmin();
					passwordCorrect = false;
					
					ReadTxtFile.lastLogin.set(position, dft.format(now));
					
					SaveTxtFile stf2 = new SaveTxtFile();
					stf2.openFile();
					stf2.addRecords();
					stf2.closeFile();
				}
				
				else if(idEntered.charAt(0) == '9')
				{
					systemAdminFrame sysAdmin = new systemAdminFrame();
					sysAdmin.ReadAllEvent(idEntered);
					sysAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sysAdmin.setContentPane(sysAdmin.createSysAdmFrame());
					sysAdmin.setSize(720, 550);
					sysAdmin.setVisible(true);
					
					ReadTxtFile.lastLogin.set(position, dft.format(now));
					
					SaveTxtFile stf3 = new SaveTxtFile();
					stf3.openFile();
					stf3.addRecords();
					stf3.closeFile();
				}
			}
			
			else if(ReadTxtFile.isSuspended.get(position).compareTo(suspend) == 0 && 
					idCorrect == true && passwordCorrect == true)
			{

				JOptionPane.showMessageDialog(itProject.this, String.format("Your login account " + 
										"have been suspended. Please contact the administrator.",
										event.getActionCommand()));
			}
			
			else if(idCorrect == false || passwordCorrect == false)
			{
				JOptionPane.showMessageDialog(itProject.this, String.format("Wrong login ID/ " + 
										"Password. Please try again", event.getActionCommand()));
			}
		}
	}
	
	public static void main(String [] args)
	{	
		itProject jc = new itProject();
		jc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jc.setLocationRelativeTo(null);
		jc.setSize(680, 350);
		jc.setVisible(true);
	}
}