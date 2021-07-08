//package javaapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;

public class staffBooking
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Staff Events Creation System");
        JTable table = new JTable();
        
        Object[] columns = {"Event Name", "Price", "Start Date", "Total Days", "Time",
							"Promo", "Capacities", "Public/Private", "Tickets Booked"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(250);  

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment( SwingConstants.CENTER );
        
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(25);
              
		JLabel lblName = new JLabel("Event Name:");
		JLabel lblPrice = new JLabel("Price:");
		JLabel lblDate = new JLabel("Start Date:");
		JLabel lblDay = new JLabel("Total Days:");
        JLabel lblTime = new JLabel("Time:");
        JLabel lblPromo = new JLabel("Promo:");
        JLabel lblCapacity = new JLabel("Capacities:");
		JLabel lblP = new JLabel("Public/Private:");
        
		JTextField txtName = new JTextField();
		JTextField txtPrice = new JTextField();
        JTextField txtDate = new JTextField();
		JTextField txtDay = new JTextField();
        JTextField txtTime = new JTextField();
        JTextField txtPromo = new JTextField();
        JTextField txtCapacity = new JTextField();
		JTextField txtP = new JTextField();
          
        JButton btnCreate = new JButton("Create");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnExit = new JButton("Exit");
        
        lblName.setBounds(90, 280, 100, 25);
        lblPrice.setBounds(490, 280, 100, 25);
        lblDate.setBounds(860, 280, 100, 25);
        lblDay.setBounds(90, 350, 100, 25);
        lblTime.setBounds(490, 350, 100, 25);
        lblPromo.setBounds(860, 350, 100, 25);
        lblCapacity.setBounds(90, 420, 100, 25);
        lblP.setBounds(490, 420, 100, 25);
                  
        txtName.setBounds(200, 280, 200, 25);
        txtPrice.setBounds(580, 280, 200, 25);
        txtDate.setBounds(960, 280, 200, 25);
        txtDay.setBounds(200, 350, 200, 25);
        txtTime.setBounds(580, 350, 200, 25);
        txtPromo.setBounds(960, 350, 200, 25);
        txtCapacity.setBounds(200, 420, 200, 25);
        txtP.setBounds(580, 420, 200, 25);
                
        btnCreate.setBounds(300, 500, 100, 25);
        btnEdit.setBounds(500, 500, 100, 25);
        btnDelete.setBounds(700, 500, 100, 25);
        btnExit.setBounds(900, 500, 100, 25);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 20, 1300, 230);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        frame.add(lblDate);
        frame.add(lblTime);
        frame.add(lblDay);
        frame.add(lblName);
        frame.add(lblPrice);
        frame.add(lblCapacity);
        frame.add(lblP);
        frame.add(lblPromo);
        
        frame.add(txtDate);
        frame.add(txtTime);
        frame.add(txtDay);
        frame.add(txtName);
        frame.add(txtPrice);
        frame.add(txtCapacity);
        frame.add(txtP);
        frame.add(txtPromo);
        
        frame.add(btnCreate);
        frame.add(btnEdit);
        frame.add(btnDelete);
        frame.add(btnExit);
       
		Object[] in = new Object[9];
		try
		{
			Scanner input = new Scanner (new File ("eventsDetail.txt"));
			input.useDelimiter(", ");
			
			while (input.hasNext())
			{
				in[0] = input.next();
                in[1] = input.next();
                in[2] = input.next();
                in[3] = input.next();
                in[4] = input.next();
                in[5] = input.next();
                in[6] = input.next();
                in[7] = input.next();
                in[8] = input.next();
				
				model.addRow(in);
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		
        Object[] row = new Object[9];
        btnCreate.addActionListener(new ActionListener()
		{    
            @Override
            public void actionPerformed(ActionEvent e)
			{
                row[0] = txtName.getText();
                row[1] = txtPrice.getText();
                row[2] = txtDate.getText();
                row[3] = txtDay.getText();
                row[4] = txtTime.getText();
                row[5] = txtPromo.getText();
                row[6] = txtCapacity.getText();
                row[7] = txtP.getText();
				row[8] = 0;
                
                model.addRow(row);
                
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
							if(j != (table.getColumnCount() - 1))
								{
									bw.write(table.getModel().getValueAt(i, j) + ", ");
								}
								else
								{
									bw.write(table.getModel().getValueAt(i, j) + "");
								}
						}
						bw.write("\n");
					}
					
					bw.close();
					fw.close();
					JOptionPane.showMessageDialog(null,"You have saved/created an event");
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
								if(j != (table.getColumnCount() - 1))
								{
									bw.write(table.getModel().getValueAt(i, j) + ", ");
								}
								else
								{
									bw.write(table.getModel().getValueAt(i, j) + "");
								}
							}
							bw.write("\n");
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
                System.exit(0);
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
							if(j != (table.getColumnCount() - 1))
								{
									bw.write(table.getModel().getValueAt(i, j) + ", ");
								}
								else
								{
									bw.write(table.getModel().getValueAt(i, j) + "");
								}
						}
						
						bw.write("\n");
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
        
        frame.setSize(1335, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  
    }
}