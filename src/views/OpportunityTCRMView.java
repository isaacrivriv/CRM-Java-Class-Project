package views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import beans.CRMBean;

public interface OpportunityTCRMView extends TCRMView {

	String getTextId();
	String getTextDate();
	String getTextDescription();
	String getTextValue();
	String getTextStatus();
	
	void setTextId(String textId);
	void setTextDate(String textDate);
	void setTextDescription(String textDescription);
	void setTextValue(String textValue);
	
	// TODO: Verify MVC compliance of these methods
	int getSelectedClientIndex();
	void setSelectedClientIndex(int index);
	void setSelectClientItems(ArrayList<CRMBean> list);
	void setSelectClientListener(ActionListener listener);
	
	String getErrorId();
	String getErrorDate();
	String getErrorDescription();
	String getErrorValue();
	String getErrorStatus();
	
	void setErrorId(String errorId);
	void setErrorDate(String errorDate);
	void setErrorDescription(String errorDescription);
	void setErrorValue(String errorValue);
	void setErrorStatus(String errorStatus);
}