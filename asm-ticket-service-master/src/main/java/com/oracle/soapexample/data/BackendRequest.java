package com.oracle.soapexample.data;


public class BackendRequest {
	
	private String idmToken; 
	private String userId; 
	private String password;
	
	@Override
	public String toString() {
		return "BackendRequest [idmToken=" + idmToken + ", userId=" + userId + ", password=" + password + "]";
	}
	public String getIdmToken() {
		return idmToken;
	}
	public void setIdmToken(String idmToken) {
		this.idmToken = idmToken;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
}
