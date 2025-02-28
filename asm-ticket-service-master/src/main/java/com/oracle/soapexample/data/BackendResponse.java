package com.oracle.soapexample.data;

public class BackendResponse {	
	
	private String ticket; 
	private String userId;
	
	
	@Override
	public String toString() {
		return "BackendResponse [ticket=" + ticket + ", userId=" + userId + "]";
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}


