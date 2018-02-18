package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.CRMModel;
import swingViews.SwingView;
import views.ClientTCRMView;

public class CleverTeamNamesClientController extends ClientController{
	
	
	

	public CleverTeamNamesClientController(SwingView view, CRMModel model) {
		super(view, model);
	}
	
	@Override
	public void validateCompany() {
		ClientTCRMView view = (ClientTCRMView) getView();
		String wordPattern="[A-Za-z]+(\\s[A-Za-z]+)?";
		if (view.getTextCompany().trim().length() == 0) {
			addValidationError("Company", "Empty Company. Required Field.");
		}
		else {
			if(view.getTextCompany().trim().matches(wordPattern)) {
				view.setTextCompany(Character.toString(Character.toUpperCase(view.getTextCompany().charAt(0)))+
						view.getTextCompany().substring(1));
			}
			else
				addValidationError("Company", "Invalid Company Name. Only Letters permitted.");
		}
	}
	
	@Override
	public void validateTelephone() {
		ClientTCRMView view = (ClientTCRMView) getView();
		String phonePattern="([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?[0-9]{3}( |-)?[0-9]{4}";
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
	public void validateEmail() {
		ClientTCRMView view = (ClientTCRMView) getView();
		String emailPattern="[A-Za-z0-9_.-]+@[A-Za-z0-9]+\\.[A-Za-z]{3}";
		if (view.getTextEmail().trim().length() == 0) {
			addValidationError("Email", "Empty Email. Required Field.");
		}
		else if(!view.getTextEmail().trim().matches(emailPattern))
			addValidationError("Email", "Invalid Email Adress. Required Field.");
			
	}
	
	@Override
	public void validateWebsite() {
		ClientTCRMView view = (ClientTCRMView) getView();
		String websitePattern="(http://|https://)?w{3}\\.\\p{Graph}+\\.[A-Za-z]{3}";
		if (view.getTextWebsite().trim().length() == 0) {
			addValidationError("Website", "Empty Website. Required Field.");
		}
		else if(!view.getTextWebsite().trim().matches(websitePattern))
			addValidationError("Website", "Inavlid Website Adress. Required Field.");
	}
	
	@Override
	public void validateFacebook() {
		ClientTCRMView view = (ClientTCRMView) getView();
		String facebookPattern="((http://|https://)?w{3}\\.facebook.com/[A-Za-z0-9.]{5,})|[A-Za-z\\s]+";
		if (view.getTextFacebook().trim().length() == 0) {
			addValidationError("Facebook", "Empty Facebook. Required Field.");
		}
		else if(!view.getTextFacebook().trim().matches(facebookPattern))
			addValidationError("Facebook", "Invalid Facebook Address. Required Field.");
	}
	
}
