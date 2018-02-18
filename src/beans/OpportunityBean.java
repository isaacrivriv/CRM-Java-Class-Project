package beans;

public class OpportunityBean extends CRMBean {

	private String date;
	private String description;
	private String value;
	private String status;
	private long client;
	
	public OpportunityBean(long id) {
		super(id);
		date = "";
		description = "";
		value = "";
		status = "";
		client = -1;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return this.getId() + ": " + this.getDescription();
	}
	
	public boolean equals(Object o) {
		if (o instanceof OpportunityBean) {
			OpportunityBean ob = (OpportunityBean) o;
			return (ob.getId() == this.getId());
		}
		return false;
	}

	public long getClient() {
		return client;
	}
	
	public void setClient(long id) {
		this.client = id;
	}
	
}
