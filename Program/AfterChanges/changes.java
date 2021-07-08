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

class changes extends JFrame
{
	public static void main(String[] args)
	{		
		changes chg = new changes();
	}
	
	public changes()
	{
		// Frame Name
		super("User Administrator");
		
		setLayout(null);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1335, 500);
		setVisible(true);
        JTable table1 = new JTable();
        
        Object[] top = {"UOW ID", "User Type", "Password", "UOW E-mail", "Last Login",
						"Last Logout"};

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
        JButton chgExit = new JButton("Logout");
        
		chgTitle1.setBounds(570, 10, 200, 25);
		chgTitle2.setBounds(570, 280, 250, 25);
		
        chgID.setBounds(90, 320, 100, 25);
        chgPassword.setBounds(490, 320, 100, 25);
        chgEmail.setBounds(860, 320, 100, 25);
                  
        chgIDTxt.setBounds(200, 320, 200, 25);
        chgPasswordTxt.setBounds(580, 320, 200, 25);
        chgEmailTxt.setBounds(960, 320, 200, 25);
		
        chgCreate.setBounds(300, 400, 100, 25);
        chgEdit.setBounds(500, 400, 100, 25);
		chgDelete.setBounds(700, 400, 100, 25);
        chgExit.setBounds(900, 400, 100, 25);
        
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
        add(chgExit);
		
		Object[] obj = new Object[6];
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
				
				obj[2] = Line[1];
				obj[3] = Line[2];
				obj[4] = Line[3];
				obj[5] = Line[4];
					
				model1.addRow(obj);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
	
	
		Object[] create = new Object[6];
		chgCreate.addActionListener(new ActionListener()
		{    
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(chgIDTxt.getText().equals("") || chgPasswordTxt.getText().equals("") || 
				chgEmailTxt.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "One or More Field/s are Empty");
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
					
					create[2] = chgPasswordTxt.getText();
					create[3] = chgEmailTxt.getText();
					create[4] = "NULL";
					create[5] = "NULL";
					
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
					JOptionPane.showMessageDialog(null, "You updated the account details");
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
		
		chgExit.addActionListener(new ActionListener()
		{       
            @Override
            public void actionPerformed(ActionEvent e)
			{
				dispose();
            }
		});
	}
}