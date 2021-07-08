import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.lang.*;

class accountManagement extends JFrame
{
	private final JTextField textFieldMng1;
	static String newPassword;
	
	accountManagement()
	{		
		// Frame Name
		super("Account Management");
		
		setLayout(null);
		
		// UOW logo icon
		ImageIcon uowIcon = new ImageIcon("uow.jpg");
		JLabel labelUOW = new JLabel(uowIcon);
		labelUOW.setBounds(30, 20, 600, 198);
		
		JLabel labelMng1 = new JLabel("Account Management"); 
		labelMng1.setFont(new Font("SansSerif", Font.BOLD, 17));
		labelMng1.setBounds(250, 210, 300, 50);
		
		JLabel labelMng2 = new JLabel("ID:   " + itProject.idEntered); 
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

class ReadTxtFile
{
	private static Scanner input;
	static ArrayList <String> loginID = new ArrayList <String> ();
	static ArrayList <String> password = new ArrayList <String> ();
	static ArrayList <String> email = new ArrayList <String> ();
	
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
				loginID.add(input.next());
				password.add(input.next());
				email.add(input.next());
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
				output.format("%s %s %s%n", ReadTxtFile.loginID.get(i), 
							  ReadTxtFile.password.get(i), ReadTxtFile.email.get(i));
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

public class itProject extends JFrame
{
	private final JTextField textField1;
	private final JPasswordField passwordField1;
	static String idEntered;
	static String passwordEntered;
	static int position;
	boolean idCorrect;
	boolean passwordCorrect;
	
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
		@Override
		public void actionPerformed(ActionEvent event)
		{
			idEntered = String.valueOf(textField1.getText());
			passwordEntered = String.valueOf(passwordField1.getPassword());
			
			for(int i = 0; i < ReadTxtFile.loginID.size(); i++)
			{
				if(idEntered.compareTo(ReadTxtFile.loginID.get(i)) == 0)
				{
					idCorrect = true;
					position = i;
				}
			}
			
			for(String d : ReadTxtFile.password)
			{
				if(passwordEntered.compareTo(d) == 0)
				{
					passwordCorrect = true;
				}
			}
			
			if(textField1.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(itProject.this,
				String.format("It is EMPTY! You BOBO!", event.getActionCommand()));
			}
			else if(idEntered.charAt(0) == '6' && idCorrect == true && passwordCorrect == true)
			{	
				accountManagement accMng = new accountManagement();
				accMng.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				accMng.setSize(680, 420);
				accMng.setVisible(true);
			}
			
			else if(idEntered.charAt(0) == '1' && idCorrect == true && passwordCorrect == true)
			{
				JOptionPane.showMessageDialog(itProject.this, String.format("Hiii Staff", event.getActionCommand()));
			}
			
			else
			{
				JOptionPane.showMessageDialog(itProject.this, String.format("Wrong ID/ Password Entered. Please Try" +
																			" again", event.getActionCommand()));
			}
		}
	}
	
	public static void main(String [] args)
	{
		ReadTxtFile rtf = new ReadTxtFile();
		rtf.openFile();
		rtf.readRecords();
		rtf.closeFile();
		
		itProject jc = new itProject();
		jc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jc.setSize(680, 350);
		jc.setVisible(true);
	}
}