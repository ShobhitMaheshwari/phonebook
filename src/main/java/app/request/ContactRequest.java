package app.request;

import app.model.ModelBase;

public class ContactRequest extends ModelBase {

	private static final long serialVersionUID = 6624726180748515507L;
	private String first_name;
	private String last_name;

	public ContactRequest() {
		super();
	}

	public ContactRequest(String first_name, String last_name) {
		this.setFirst_name(first_name);
		this.setLast_name(last_name);
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
