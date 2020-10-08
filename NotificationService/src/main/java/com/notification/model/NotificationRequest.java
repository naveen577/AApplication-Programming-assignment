package com.notification.model;

import java.io.Serializable;
import java.util.List;

public class NotificationRequest implements Serializable {

	private static final long serialVersionUID = 3912799887020443122L;

	private List<Email> email;
	private List<SMS> sms;

	public List<Email> getEmail() {
		return email;
	}

	public void setEmail(List<Email> email) {
		this.email = email;
	}

	public List<SMS> getSms() {
		return sms;
	}

	public void setSms(List<SMS> sms) {
		this.sms = sms;
	}

	@Override
	public String toString() {
		return "NotificationRequest [email=" + email + ", sms=" + sms + "]";
	}

}
