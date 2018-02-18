package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidFormFieldData;
import main.CRMMain;
import models.CRMModel;
import models.ClientModel;
import models.OpportunityModel;
import swingViews.ContactSwingView;
import swingViews.OpportunitySwingView;
import swingViews.SwingView;
import views.ContactTCRMView;
import views.OpportunityTCRMView;


public class OpportunityController extends CRMController {

	public OpportunityController(SwingView opportunityView, CRMModel opportunityModel, CRMModel clientModel) {
		super(opportunityView, opportunityModel);

		OpportunitySwingView cv = (OpportunitySwingView) opportunityView;
		//ContactModel cm = (ContactModel) contactModel;
		ClientModel clientModel2 = (ClientModel) clientModel;
		cv.setSelectClientItems(clientModel2.getAllBeans());
		
		cv.clearFieldErrors();
		cv.setSelectClientListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Client Combo Box Selected");
			}
		});
	}
	
	public void doLeft() {
		System.out.println("OpportunityController.doLeft()");
		super.doLeft();
	}

	public void doRight() {
		System.out.println("OpportunityController.doRight()");
		super.doRight();
	}

	public void doEdit() {
		System.out.println("OpportunityController.doEdit()");
		super.doEdit();
		refreshDropdowns();
	}

	public void doAdd() {
		System.out.println("OpportunityController.doAdd()");
		super.doAdd();
	}

	public void doDelete() {
		System.out.println("OpportunityController.doDelete()");
		super.doDelete();
	}

	public void doSave() {
		System.out.println("OpportunityController.doSave()");
		super.doSave();
	}

	public void doSelectClient() {
		this.refreshView();
	}
	
	public void validateForm() throws InvalidFormFieldData {
		getValidationErrors().clear();
		validateDate();
		validateDescription();
//		validateStatus();
		validateValue();
		if (getValidationErrors().size() > 0)
			throw new InvalidFormFieldData ("Invalid Form");
	}

	public void validateDate() {
		OpportunityTCRMView view = (OpportunityTCRMView) getView();
		String datePattern="[0-9]{1,2}(/|-)?[0-9]{1,2}(/|-)?[0-9]{4}";
		if (view.getTextDate().trim().length() == 0) {
			addValidationError("Date", "Empty Date. Required Field.");
		}
		if(!view.getTextDate().trim().matches(datePattern))
			addValidationError("Date", "Invalid Date. Must match MM/DD/YYYY.");
		else {
			Pattern number=Pattern.compile("[0-9]");
			Matcher match=number.matcher(view.getTextDate());
			String date="";
			while(match.find()) {
				date+=match.group();
			}
			view.setTextDate(date.substring(0, 2)+"/"+date.substring(2,4)+"/"+date.substring(4));
		}
	}
	
	public void validateDescription() {
		OpportunityTCRMView view = (OpportunityTCRMView) getView();
		String descriptionPattern="[A-Za-z0-9.\\s]+";
		if (view.getTextDescription().trim().length() == 0) {
			addValidationError("Description", "Empty Description. Required Field.");
		}
		if(!view.getTextDescription().trim().matches(descriptionPattern))
			addValidationError("Description", "Invalid Description. Must a phrase or sentence.");

	}
	
	/*
	 * Commented out validate status since it chooses from a combo box and default is pending,
	 * there's no need for validation. It's either a chosen option or pending, no in between and no editing.
	 */
	
//	public void validateStatus() {
//		OpportunityTCRMView view = (OpportunityTCRMView) getView();
//		if (view.getTextStatus().trim().length() == 0) {
//			addValidationError("Status", "Empty Status. Required Field.");
//		}
//	}
	
	public void validateValue() {
		OpportunityTCRMView view = (OpportunityTCRMView) getView();
		String numberRegex="[0-9]+(\\.[0-9]+)?";
		if (view.getTextValue().trim().length() == 0) {
			addValidationError("Value", "Empty Value. Required Field.");
		}
		if(!view.getTextValue().trim().matches(numberRegex))
			addValidationError("Value", "Invalid Value. Only numbers Permitted.");

	}
	
	protected void refreshView() {
		super.refreshView();
		String errorString = "";
		OpportunitySwingView ov = (OpportunitySwingView) getView();
		ov.clearFieldErrors();
		Map<String, String> validationErrors = getValidationErrors();
		if (validationErrors.size() > 0) {
			errorString = "Fields in red are invalid";
			if (validationErrors.containsKey("Description")) { ov.setErrorDescription(validationErrors.get("Description")); }
//			if (validationErrors.containsKey("Status")) { ov.setErrorStatus(validationErrors.get("Status")); }
			if (validationErrors.containsKey("Value")) { ov.setErrorValue(validationErrors.get("Value")); }
			if (validationErrors.containsKey("Date")) { ov.setErrorDate(validationErrors.get("Date")); }
		}
		ov.setMessagesText(errorString);
	}

	public void refreshDropdowns() {
		OpportunityTCRMView cv = (OpportunityTCRMView) getView();
		cv.setSelectClientItems(CRMMain.clientModel.getAllBeans());
	}	
}
