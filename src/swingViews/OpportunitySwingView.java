package swingViews;

import java.awt.Color;

import java.awt.Font;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.Insets;

import java.awt.event.ActionListener;

import java.util.ArrayList;



import javax.swing.JComboBox;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextField;

import javax.swing.ScrollPaneConstants;

import javax.swing.SwingConstants;



import beans.CRMBean;
import beans.ClientBean;
import beans.OpportunityBean;
import swingViews.ContactSwingView.ClientForComboBox;
import views.OpportunityTCRMView;



public class OpportunitySwingView extends SwingView implements OpportunityTCRMView {

	protected class ClientForComboBox {

		private long id;
		private String description;

		protected long getId() {
			return id;
		}

		protected ClientForComboBox(long id, String description) {
			this.id = id;
			this.description = description;
		}

		protected String getDescription() {
			return description;
		}
		protected void setId(long id) {
			this.id = id;
		}
		protected void setDescription(String description) {
			this.description = description;
		}
		
		// toString() called by JComboBox to obtain display text for item
		public String toString() {
			return description;
		}
		
	}

	private static final long serialVersionUID = 1L;

	private JTextField textId;
	
	private JComboBox<String> comboBoxStatus;

	private JTextField textDescription;

	private JTextField textValue;

	private JTextField textDate;

	/*
	 * Array of strings to fill up status combo box
	 */
	
	private String[] textStatusArray = {"Pending", "Verbal", "Written", "Completed", "Failed"};
	
	private JTextField textStatus;

	private JLabel idLblError;

	private JLabel descriptionLblError;

	private JLabel valueLblError;

	private JLabel dateLblError;

	private JLabel statusLblError;
	
	private JLabel clientLblError;
	
	private JComboBox<ClientForComboBox> comboBoxClient;


	/**

	 * Create the frame.

	 */

	public OpportunitySwingView() {
		super();
		setTitle("Opportunities");

		JScrollPane centerScrollPane = new JScrollPane();
		centerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		centerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setCenterPanel(centerScrollPane);
		
		JPanel centerGrid = new JPanel();
		centerScrollPane.setViewportView(centerGrid);
		GridBagLayout gbl_centerGrid = new GridBagLayout();
		gbl_centerGrid.columnWidths = new int[]{100, 475, 0};
		gbl_centerGrid.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerGrid.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerGrid.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE};
		centerGrid.setLayout(gbl_centerGrid);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		centerGrid.add(lblId, gbc_lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		centerGrid.add(textId, gbc_txtId);
		textId.setColumns(10);
				
		idLblError = new JLabel("New label");
		idLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		idLblError.setForeground(Color.RED);
		GridBagConstraints gbc_IdLblError = new GridBagConstraints();
		gbc_IdLblError.anchor = GridBagConstraints.WEST;
		gbc_IdLblError.insets = new Insets(0, 0, 5, 0);
		gbc_IdLblError.gridx = 1;
		gbc_IdLblError.gridy = 1;
		centerGrid.add(idLblError, gbc_IdLblError);

		JLabel lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 2;
		centerGrid.add(lblDescription, gbc_lblDescription);
		
		textDescription = new JTextField();
		textDescription.setEditable(false);
		GridBagConstraints gbc_textDescription = new GridBagConstraints();
		gbc_textDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDescription.gridx = 1;
		gbc_textDescription.gridy = 2;
		centerGrid.add(textDescription, gbc_textDescription);
		textDescription.setColumns(10);
		
		descriptionLblError = new JLabel("New label");
		descriptionLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		descriptionLblError.setForeground(Color.RED);
		GridBagConstraints gbc_DescriptionLblbError = new GridBagConstraints();
		gbc_DescriptionLblbError.anchor = GridBagConstraints.WEST;
		gbc_DescriptionLblbError.insets = new Insets(0, 0, 5, 0);
		gbc_DescriptionLblbError.gridx = 1;
		gbc_DescriptionLblbError.gridy = 3;
		centerGrid.add(descriptionLblError, gbc_DescriptionLblbError);
		
		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 0;
		gbc_lblValue.gridy = 4;
		centerGrid.add(lblValue, gbc_lblValue);
		
		textValue = new JTextField();
		textValue.setEditable(false);
		GridBagConstraints gbc_textValue = new GridBagConstraints();
		gbc_textValue.insets = new Insets(0, 0, 5, 0);
		gbc_textValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textValue.gridx = 1;
		gbc_textValue.gridy = 4;
		centerGrid.add(textValue, gbc_textValue);
		textValue.setColumns(10);
		
		valueLblError = new JLabel("New label");
		valueLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		valueLblError.setForeground(Color.RED);
		GridBagConstraints gbc_valueLblError = new GridBagConstraints();
		gbc_valueLblError.anchor = GridBagConstraints.WEST;
		gbc_valueLblError.insets = new Insets(0, 0, 5, 0);
		gbc_valueLblError.gridx = 1;
		gbc_valueLblError.gridy = 5;
		centerGrid.add(valueLblError, gbc_valueLblError);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 6;
		centerGrid.add(lblDate, gbc_lblDate);
		
		textDate = new JTextField();
		textDate.setEditable(false);
		textDate.setToolTipText("mm/dd/yyyy");
		GridBagConstraints gbc_textDate = new GridBagConstraints();
		gbc_textDate.insets = new Insets(0, 0, 5, 0);
		gbc_textDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDate.gridx = 1;
		gbc_textDate.gridy = 6;
		centerGrid.add(textDate, gbc_textDate);
		textDate.setColumns(10);
		
		dateLblError = new JLabel("New label");
		dateLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		dateLblError.setForeground(Color.RED);
		GridBagConstraints gbc_dateLblError = new GridBagConstraints();
		gbc_dateLblError.anchor = GridBagConstraints.WEST;
		gbc_dateLblError.insets = new Insets(0, 0, 5, 0);
		gbc_dateLblError.gridx = 1;
		gbc_dateLblError.gridy = 7;
		centerGrid.add(dateLblError, gbc_dateLblError);
		
		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 8;
		centerGrid.add(lblStatus, gbc_lblStatus);
		
		/*
		 * Make a combo box with choices given from string array 
		 * and make a empty text field to hold the selected item in it.
		 * Set visible = false so that it only appears once you are editing the status and not before that.
		 */
		
		comboBoxStatus = new JComboBox<String>(textStatusArray);
		comboBoxStatus.setEditable(false);
		comboBoxStatus.setVisible(false);
		comboBoxStatus.setEnabled(false);
		GridBagConstraints gbc_comboBoxStatus = new GridBagConstraints();
		gbc_comboBoxStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStatus.gridx = 1;
		gbc_comboBoxStatus.gridy = 9;
		centerGrid.add(comboBoxStatus, gbc_comboBoxStatus);

		textStatus = new JTextField();
		textStatus.setEditable(false);
		GridBagConstraints gbc_textStatus = new GridBagConstraints();
		gbc_textStatus.insets = new Insets(0, 0, 5, 0);
		gbc_textStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textStatus.gridx = 1;
		gbc_textStatus.gridy = 8;
		centerGrid.add(textStatus, gbc_textStatus);
		
		statusLblError = new JLabel("New label");
		statusLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		statusLblError.setForeground(Color.RED);
		GridBagConstraints gbc_statusLblError = new GridBagConstraints();
		gbc_statusLblError.anchor = GridBagConstraints.WEST;
		gbc_statusLblError.insets = new Insets(0, 0, 5, 0);
		gbc_statusLblError.gridx = 1;
		gbc_statusLblError.gridy = 10;
		centerGrid.add(statusLblError, gbc_statusLblError);
		
		JLabel lblClient = new JLabel("Client");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.EAST;
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 0;
		gbc_lblClient.gridy = 11;
		centerGrid.add(lblClient, gbc_lblClient);

		comboBoxClient = new JComboBox<ClientForComboBox>();
		comboBoxClient.setEditable(false);
		comboBoxClient.setEnabled(false);
		GridBagConstraints gbc_comboBoxClient = new GridBagConstraints();
		gbc_comboBoxClient.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClient.gridx = 1;
		gbc_comboBoxClient.gridy = 11;
		centerGrid.add(comboBoxClient, gbc_comboBoxClient);

		clientLblError = new JLabel("New label");
		clientLblError.setForeground(Color.RED);
		clientLblError.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_clientLblError = new GridBagConstraints();
		gbc_clientLblError.anchor = GridBagConstraints.WEST;
		gbc_clientLblError.insets = new Insets(0, 0, 5, 0);
		gbc_clientLblError.gridx = 1;
		gbc_clientLblError.gridy = 12;
		centerGrid.add(clientLblError, gbc_clientLblError);
		//comboBoxClient.setColumns(10);
		
		this.setMessagesText("No Opportunities in the CRM");
		
	}

	public String getTextId() { return textId.getText(); }
	public String getTextDate() { return textDate.getText(); }
	public String getTextDescription() { return textDescription.getText(); }
	public String getTextValue() { return textValue.getText(); }
	public String getTextStatus() { return this.textStatus.getText(); }
	
	public void setTextId(String textId) { this.textId.setText(textId); }
	public void setTextDate(String textDate) { this.textDate.setText(textDate); }
	public void setTextDescription(String textDescription) { this.textDescription.setText(textDescription); }
	public void setTextValue(String textValue) { this.textValue.setText(textValue); }
	public void setTextStatus(String textStatus) { this.textStatus.setText(textStatus); }
	
	public String getErrorId() { return idLblError.getText(); }
	public String getErrorDate() { return dateLblError.getText(); }
	public String getErrorDescription() { return descriptionLblError.getText(); }
	public String getErrorValue() { return valueLblError.getText(); }
	public String getErrorStatus() { return statusLblError.getText(); }
	
	public void setErrorId(String errorId) { idLblError.setText(errorId); }
	public void setErrorDate(String errorDate) { dateLblError.setText(errorDate); }
	public void setErrorDescription(String errorDescription) { descriptionLblError.setText(errorDescription); }
	public void setErrorValue(String errorValue) { valueLblError.setText(errorValue); }
	public void setErrorStatus(String errorStatus) { statusLblError.setText(errorStatus); }

	@Override
	public void beanToForm(CRMBean bean) {
		OpportunityBean ob = (OpportunityBean) bean;
		this.setTextId(""+ob.getId());
		this.setTextDate(ob.getDate());
		this.setTextDescription(ob.getDescription());
		this.setTextValue(ob.getValue());
		this.setTextStatus(ob.getStatus());
		for (int i=0; i < comboBoxClient.getItemCount(); i++) {
			ClientForComboBox item = comboBoxClient.getItemAt(i);
			if (item.getId() == ob.getId()) {
				this.setSelectedClientIndex(i);
			}
		}
	}

	@Override
	public void formToBean(CRMBean bean) {
		OpportunityBean ob = (OpportunityBean) bean;
		// cb.setId(Integer.parseInt(textId.getText()));  // Id is never editable
		ob.setDate(textDate.getText());
		ob.setDescription(textDescription.getText());
		ob.setValue(textValue.getText());
		ob.setStatus((String) comboBoxStatus.getSelectedItem());
		ob.setClient(((ClientForComboBox) comboBoxClient.getSelectedItem()).getId());
	}
	
	@Override
	public void enableEditMode() { 
		super.enableEditMode();
		textDescription.setEditable(true);
		textValue.setEditable(true);
		textDate.setEditable(true);
		comboBoxStatus.setVisible(true);
		comboBoxStatus.setEnabled(true);
		comboBoxClient.setEnabled(true);
	}
	
	@Override
	public void disableEditMode() {
		super.disableEditMode();
		textId.setEditable(false);
		textDescription.setEditable(false);
		textValue.setEditable(false);
		textDate.setEditable(false);
		comboBoxStatus.setVisible(false);
		comboBoxStatus.setEnabled(false);
		comboBoxClient.setEnabled(false);
	}

	@Override
	public void clearForm() {
		textId.setText("");
		textDate.setText("");
		textDescription.setText("");		
		textValue.setText("");
		textStatus.setText("");
		if (comboBoxClient.getItemCount() > 0) { comboBoxClient.setSelectedIndex(0); }
		clearFieldErrors();
	}

	@Override
	public void clearFieldErrors() {
		idLblError.setText("");
		dateLblError.setText("");
		descriptionLblError.setText("");
		valueLblError.setText("");
		statusLblError.setText("");
		clientLblError.setText("");
	}

	@Override
	public int getSelectedClientIndex() {
		return comboBoxClient.getSelectedIndex();
	}

	@Override
	public void setSelectedClientIndex(int index) {
		if (index >= 0 && index <= comboBoxClient.getItemCount()) {
			comboBoxClient.setEnabled(false);
			comboBoxClient.setSelectedIndex(index);
			comboBoxClient.setEnabled(true);
		}
	}

	@Override
	public void setSelectClientItems(ArrayList<CRMBean> list) {
		comboBoxClient.removeAllItems();
		for (CRMBean item : list) {
			comboBoxClient.addItem(new ClientForComboBox(item.getId(), item.getDescription()));
		}
	}

	@Override
	public void setSelectClientListener(ActionListener listener) {
		comboBoxClient.addActionListener(listener);
	}

}