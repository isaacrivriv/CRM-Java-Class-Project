package swingViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import beans.CRMBean;
import beans.ClientBean;


public class ClientOpportunitySwingView extends ClientSwingView{
	
	String[] opportunityTableColumnNames = {"Opportunity ID: ", "Description: ", "Value: "
			 , "Date: ", "Status: " };
	String[][] opportunityData;
	int opportunityXPos;
	int MAX_DEFAULT_OPPORTUNITY_Y_POSITION=6;
	String[] contactColumnNames = {"Contact ID: ", "First Name: ", "Last Name: "
			 , "Company: ", "Number: ","Email: ","Facebook: " };
	String[][] contactData;
	int contactXPos;
	int MAX_DEFAULT_CONTACTS_Y_POSITION=7;
	JLabel numberOfOpportunites=new JLabel();
	JLabel numberOfContacts=new JLabel();
	JScrollPane opportunitiesPane=new JScrollPane();
	JPanel opportunitiespanel=new JPanel();
	JScrollPane contactsPane=new JScrollPane();
	JPanel contactspanel=new JPanel();

	
	public ClientOpportunitySwingView() {
		super();
		
		JLabel opportunities=new JLabel("Opportunities Associated");
		GridBagConstraints gbc_opportunitiesText = new GridBagConstraints();
		gbc_opportunitiesText.anchor = GridBagConstraints.WEST;
		gbc_opportunitiesText.insets = new Insets(0, 0, 5, 0);
		gbc_opportunitiesText.gridx = 0;
		gbc_opportunitiesText.gridy = 12;
		this.centerGrid.add(opportunities,gbc_opportunitiesText);
		
		opportunitiesPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		opportunitiesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		opportunitiesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		opportunitiesPane.setPreferredSize(new Dimension(400,200));
		GridBagConstraints gbc_oppPanel = new GridBagConstraints();
		gbc_oppPanel.anchor = GridBagConstraints.WEST;
		gbc_oppPanel.insets = new Insets(0, 0, 5, 0);
		gbc_oppPanel.gridx = 1;
		gbc_oppPanel.gridy = 13;
		this.centerGrid.add(opportunitiesPane,gbc_oppPanel);
		opportunitiesPane.setViewportView(opportunitiespanel);
		GridBagLayout gbl_centerOppGrid = new GridBagLayout();
		gbl_centerOppGrid.columnWidths = new int[]{100, 10, 0};
		gbl_centerOppGrid.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerOppGrid.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerOppGrid.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		opportunitiespanel.setLayout(gbl_centerOppGrid);
		
		JLabel contacts=new JLabel("Contacts Associated");
		GridBagConstraints gbc_contactsText = new GridBagConstraints();
		gbc_contactsText.anchor = GridBagConstraints.WEST;
		gbc_contactsText.insets = new Insets(0, 0, 5, 0);
		gbc_contactsText.gridx = 0;
		gbc_contactsText.gridy = 14;
		this.centerGrid.add(contacts,gbc_contactsText);
		
		contactsPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		contactsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contactsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contactsPane.setPreferredSize(new Dimension(400,200));

		GridBagConstraints gbc_contPanel = new GridBagConstraints();
		gbc_contPanel.anchor = GridBagConstraints.WEST;
		gbc_contPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contPanel.gridx = 1;
		gbc_contPanel.gridy = 15;
		this.centerGrid.add(contactsPane,gbc_contPanel);
		contactsPane.setViewportView(contactspanel);
		GridBagLayout gbl_centerContGrid = new GridBagLayout();
		gbl_centerContGrid.columnWidths = new int[]{100, 10, 0};
		gbl_centerContGrid.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerContGrid.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerContGrid.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contactspanel.setLayout(gbl_centerContGrid);
		
		
	}
	
	@Override
	public void beanToForm(CRMBean bean) {
		super.beanToForm(bean);
		ClientBean cb = (ClientBean) bean;
		//createTables(String.valueOf(cb.getId()));
		addOpportunities(String.valueOf(cb.getId()));
		addContacts(String.valueOf(cb.getId()));
	}
	
	
	public void addOpportunities(String id) {
		opportunitiespanel.removeAll();		
		opportunityData=new String[getNumberOfOpportunities(id)][MAX_DEFAULT_OPPORTUNITY_Y_POSITION];
		getOpportunities(id);
		if(getNumberOfOpportunities(id)==0) 
			opportunitiespanel.add(new JLabel("No Opportunities"));
		else {
					
			for(int x=0;x<opportunityData.length;x++) {
				String s="";
				s+=" "+opportunityTableColumnNames[0]+opportunityData[x][0];
				s+="    "+opportunityTableColumnNames[1]+opportunityData[x][1];
				s+="    "+opportunityTableColumnNames[2]+"$"+opportunityData[x][2];
				s+="    "+opportunityTableColumnNames[3]+opportunityData[x][3];
				s+="    "+opportunityTableColumnNames[4]+opportunityData[x][4];
			
				GridBagConstraints gbc_jLabels = new GridBagConstraints();
				gbc_jLabels.anchor = GridBagConstraints.WEST;
				gbc_jLabels.insets = new Insets(0, 0, 5, 5);
				gbc_jLabels.gridx = 0;
				gbc_jLabels.gridy = x;
				opportunitiespanel.add(new JLabel(s),gbc_jLabels);
				}
			
		}
	}
	
	public void addContacts(String id) {
		contactspanel.removeAll();		
		contactData=new String[getNumberOfContacts(id)][MAX_DEFAULT_CONTACTS_Y_POSITION];
		getContacts(id);
		if(getNumberOfContacts(id)==0) 
			contactspanel.add(new JLabel("No Contacts"));
		else {
				for(int x=0;x<contactData.length;x++) {
					String s="";
					s+=" "+contactColumnNames[0]+contactData[x][0];
					s+="    "+contactColumnNames[1]+contactData[x][1];
					s+="    "+contactColumnNames[2]+contactData[x][2];
					s+="    "+contactColumnNames[3]+contactData[x][3];
					s+="    "+contactColumnNames[4]+contactData[x][4];
					s+="    "+contactColumnNames[5]+contactData[x][5];
					s+="    "+contactColumnNames[6]+contactData[x][6];
					
					GridBagConstraints gbc_jLabels = new GridBagConstraints();
					gbc_jLabels.anchor = GridBagConstraints.WEST;
					gbc_jLabels.insets = new Insets(0, 0, 5, 5);
					gbc_jLabels.gridx = 0;
					gbc_jLabels.gridy = x;
					contactspanel.add(new JLabel(s),gbc_jLabels);
				
				}
				
		}
	}
	
	//Returns the number of opportunities that match the given client id
	public int getNumberOfOpportunities(String clientName) {
		File inputFile = new File("data/opportunities.tsv");

		Scanner inputScanner;
		Pattern p=Pattern.compile("[0-9]+");//added pattern and matcher so that it doesn't take the \n at the end of the line
		int count=0;
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			while(inputScanner.hasNextLine()) {
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				String company=inputScanner.next();
				
				Matcher m=p.matcher(company);
				m.find();
				company=m.group();
				if (company.equals(clientName))
					count++;
				inputScanner.nextLine();  // Skip over anything left in line
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//Returns the number of contacts that match the given client id
	public int getNumberOfContacts(String clientName) {
		File inputFile = new File("data/contacts.tsv");

		Scanner inputScanner;
		int count=0;
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			int numberOfRuns=0;
			while(inputScanner.hasNextLine()) {
				
				if(numberOfRuns!=0)
					//System.out.println(inputScanner.next());
					inputScanner.next();
				numberOfRuns++;
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				String company=inputScanner.next();
				//System.out.println(company);
				if (company.equals(clientName))
					count++;
				inputScanner.nextLine();  // Skip over anything left in line
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//Method that is called to fill the opportunities data array with actualized values
	public void getOpportunities(String clientName) {
		File inputFile = new File("data/opportunities.tsv");
		Scanner inputScanner;
		Pattern p=Pattern.compile("[0-9]+");//added pattern and matcher so that it doesn't take the \n at the end of the line
		opportunityXPos=0;
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			while(inputScanner.hasNextLine()) {
				String id=inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				String company=inputScanner.next();
				
				Matcher m=p.matcher(company);
				m.find();
				company=m.group();
				
				if (company.equals(clientName)) 
					fillOpportunitiesTableData(id);
				
				inputScanner.nextLine();  // Skip over anything left in line
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Method that fills the double array which contains the information of the opportunities that matches the passed id
	public void fillOpportunitiesTableData(String id) {
		File inputFile = new File("data/opportunities.tsv");

		Scanner inputScanner;
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			while(inputScanner.hasNextLine()) {
				String index=inputScanner.next();
				if(index.equals(id)) {
					opportunityData[opportunityXPos][0]=index;
					for(int y=1;y<MAX_DEFAULT_OPPORTUNITY_Y_POSITION;y++) {
						opportunityData[opportunityXPos][y]=inputScanner.next();
					}
					opportunityXPos++;
				}
				inputScanner.nextLine();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method that is called to fill the contacts data array with actualized values
	public void getContacts(String clientName) {
		File inputFile = new File("data/contacts.tsv");
		Scanner inputScanner;
		contactXPos=0;
		try {
			int numberOfRuns=0;
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			while(inputScanner.hasNextLine()) {
				if(numberOfRuns!=0)
					inputScanner.next();
				String id=inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				inputScanner.next();
				numberOfRuns++;
				String client=inputScanner.next();
				if (client.equals(clientName)) 
					fillContactsTableData(id);
				
				inputScanner.nextLine();  // Skip over anything left in line
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Method that fills the double array which contains the information of the contacts that matches the passed id
	public void fillContactsTableData(String id) {
		File inputFile = new File("data/contacts.tsv");

		Scanner inputScanner;
		
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			inputScanner.useDelimiter("[\t\n]");
			while(inputScanner.hasNextLine()) {
				if(contactXPos!=0)
					inputScanner.next();
				String index=inputScanner.next();
				if(index.equals(id)) {
					contactData[contactXPos][0]=index;
					for(int y=1;y<MAX_DEFAULT_CONTACTS_Y_POSITION;y++) {
						if(y==4)
							inputScanner.next();
						contactData[contactXPos][y]=inputScanner.next();
					}
					contactXPos++;
				}
				inputScanner.nextLine();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
