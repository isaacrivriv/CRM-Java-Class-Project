package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidFormFieldData;
import models.CRMModel;
import swingViews.SwingView;
import views.ClientTCRMView;
import views.ContactTCRMView;

public class CleverTeamNamesContactController extends ContactController{

	public CleverTeamNamesContactController(SwingView contactView, CRMModel contactModel, CRMModel clientModel) {
		super(contactView, contactModel, clientModel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void validateFirstName() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String firstnamePattern="[A-Za-z]+(\\s[A-Za-z]+\\.?)?";
		if (view.getTextFirstName().trim().length() == 0) {
			addValidationError("FirstName", "Empty First Name. Required Field.");
		}
		else if(!view.getTextFirstName().trim().matches(firstnamePattern))
			addValidationError("FirstName", "Invalid First Name. Required Field.");
		else {
			view.setTextFirstName(Character.toUpperCase(view.getTextFirstName().charAt(0))+view.getTextFirstName().substring(1));
		}
	}
	
	@Override
	public void validateLastName() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String lastnamePattern="[A-Za-z]+(\\s[A-Za-z]+)?";
		if (view.getTextLastName().trim().length() == 0) {
			addValidationError("LastName", "Empty Last Name. Required Field.");
		}
		else if(!view.getTextLastName().trim().matches(lastnamePattern))
			addValidationError("LastName", "Invalid Last Name. Required Field.");
		else {
			view.setTextLastName(Character.toUpperCase(view.getTextLastName().charAt(0))+view.getTextLastName().substring(1));
		}
	}
	
	@Override
	public void validateCompany() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String companyNamePattern="[A-Za-z]+(\\s[A-Za-z]+)?";
		if (view.getTextCompany().trim().length() == 0) {
			addValidationError("Company", "Empty Company. Required Field.");
		}
		else if(!view.getTextCompany().trim().matches(companyNamePattern))
			addValidationError("Company", "Invalid Company name. Required Field.");
		else {
			view.setTextCompany(Character.toUpperCase(view.getTextCompany().charAt(0))+view.getTextCompany().substring(1));
		}
	}	
	
	@Override
	public void validateTelephone() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String phonePattern="(([0-9]( |-)?)?\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?[0-9]{3}( |-)?[0-9]{4}";
		if (view.getTextTelephone().trim().length() == 0) {
			addValidationError("Telephone", "Empty Telephone. Required Field.");
		}
		else {
			if(view.getTextTelephone().trim().matches(phonePattern)) {
				Pattern phonePatter=Pattern.compile("[0-9]");
				Matcher phoneMatcher=phonePatter.matcher(view.getTextTelephone());
				String number="";
				while(phoneMatcher.find()) {
					number+=phoneMatcher.group();
				}
				if(number.length()==10)
					view.setTextTelephone("("+number.substring(0, 3)+")-"+number.substring(3,6)+"-"+number.substring(6));
				else if(number.length()==11)
					view.setTextTelephone(number.charAt(0)+"-"+"("+number.substring(1, 4)+")-"+number.substring(4,7)+"-"+number.substring(7));
				else
					addValidationError("Telephone", "Invalid Telephone number. Required Field.");
			}
			else
				addValidationError("Telephone", "Invalid Telephone, must match (XXX)-XXX-XXXX.");
		}
	}
	
	@Override
	public void validateEmail() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String emailPattern="[A-Za-z0-9_.-]+@[A-Za-z0-9]+\\.[A-Za-z]{3}";
		if (view.getTextEmail().trim().length() == 0) {
			addValidationError("Email", "Empty Email. Required Field.");
		}
		else if(!view.getTextEmail().trim().matches(emailPattern))
			addValidationError("Email", "Invalid Email Adress. Required Field.");
			
	}
	
	@Override
	public void validateFacebook() throws InvalidFormFieldData {
		ContactTCRMView view = (ContactTCRMView) getView();
		String facebookPattern="((http://|https://)?w{3}\\.facebook.com/[A-Za-z0-9.]{5,})|[A-Za-z\\s]+";
		if (view.getTextFacebook().trim().length() == 0) {
			addValidationError("Facebook", "Empty Facebook. Required Field.");
		}
		else if(!view.getTextFacebook().trim().matches(facebookPattern))
			addValidationError("Facebook", "Invalid Facebook Address. Required Field.");
	}
	

}
