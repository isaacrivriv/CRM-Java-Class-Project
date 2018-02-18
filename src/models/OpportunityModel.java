package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.CRMBean;
import beans.ClientBean;
import beans.ContactBean;
import beans.OpportunityBean;

public class OpportunityModel extends CRMModel {

	private static String OPPORTUNITY_FILE = "data/opportunities.tsv";
	
	public OpportunityModel() {
		super();
	}
	
	@Override
	public void doInit() {
		super.doInit();
		ArrayList<CRMBean> beans = parseBeansFromFile(OPPORTUNITY_FILE);
		this.setList(beans);
		setIndex(0);
	}
	
	@Override
	public void doAdd() {
		long id = 1;
		if (this.getCount() > 0) {
			id = ((OpportunityBean) this.getBean(this.getCount()-1)).getId() + 1;
		}
		this.getAllBeans().add(new OpportunityBean(id));
		this.setIndex(this.getCount() - 1);  // New record becomes the current one
	}
	
	@Override
	public void doSave() {
		super.doSave();
		saveBeansToFile(OPPORTUNITY_FILE);
	}

	@Override
	public ArrayList<CRMBean> parseBeansFromFile(String filename) {
		File inputFile = new File(filename);
		try {
			ArrayList<CRMBean> opportunityBeans = new ArrayList<CRMBean>();
			Scanner inputScanner = new Scanner(inputFile);
			inputScanner.nextLine();  // Ignore header line
			int count = 0;
			inputScanner.useDelimiter("[\t\n]");
			while (inputScanner.hasNextLine()) {
				String ID = inputScanner.next();
				int id = Integer.parseInt(ID);
				OpportunityBean newBean = new OpportunityBean(id);
				String description = inputScanner.next();
				newBean.setDescription(description);
				String value = inputScanner.next();
				newBean.setValue(value);
				String date = inputScanner.next();
				newBean.setDate(date);
				String status = inputScanner.next();
				newBean.setStatus(status);
				String client = inputScanner.next();
				Pattern p=Pattern.compile("[0-9]+");//Added pattern and matcher to fix problem with new line \n
				Matcher m=p.matcher(client);
				m.find();
				client=m.group();
				int clientId = Integer.parseInt(client);
				newBean.setClient(clientId);
				inputScanner.nextLine();  // Skip over anything left in line
				opportunityBeans.add(newBean);
				count++;
			}
			inputScanner.close();
			return opportunityBeans;
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException("Fatal Error: Input file not Found");
		}
	}

	@Override
	protected void saveBeansToFile(String filename) {
		ArrayList<CRMBean> opportunityBean = getAllBeans();
		File outputFile = new File(filename);
		try {
			PrintWriter out = new PrintWriter(outputFile);
			// Print Header Line
			out.println("TinyCRM Oportunities data file");
			for (CRMBean bean : opportunityBean) {
				out.println(beanToFileLine(bean));
			}
			out.close();
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException("Fatal Error: Output file not Found");
		}
	}

	public String beanToFileLine(CRMBean bean) {

		String result = "";
		OpportunityBean cb = (OpportunityBean) bean;
		result += cb.getId();
		result += '\t';
		result += cb.getDescription();
		result += '\t';
		result += cb.getValue();
		result += '\t';
		result += cb.getDate();
		result += '\t';
		result += cb.getStatus();
		result += '\t';
		if (cb.getClient() < 0) {
			result += "-1";
		}
		else {
			result += ""+cb.getClient();
		}
		return result;
		
	}
	
}
