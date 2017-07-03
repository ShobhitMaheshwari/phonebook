package app.request;

import app.model.ModelBase;

public class PhoneRequest extends ModelBase {

	private static final long serialVersionUID = 6624726180748515507L;
	private String phone;
	

	public PhoneRequest() {
		super();
	}

	public PhoneRequest(String phone) {
		this.setPhone(phone);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
