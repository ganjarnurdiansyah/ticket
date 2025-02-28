package com.oracle.soapexample.FaultException;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{" + AppIDException.NAMESPACE_URI + "}custom_fault", faultStringOrReason = "@faultString")
public class AppIDException extends Exception{
	private static final long serialVersionID = 1L;
	public static final String NAMESPACE_URI = "http://www.telkomsel.com/eai/AmdocsSM/SecurityManagement/v1.0";
	
	public AppIDException(String message) {
		super(message);
		
	}
}
