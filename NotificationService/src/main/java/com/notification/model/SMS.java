package com.notification.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	@JsonProperty("Body")
	private String body;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "SMS [from=" + from + ", to=" + to + ", body=" + body + "]";
	}
	
}
