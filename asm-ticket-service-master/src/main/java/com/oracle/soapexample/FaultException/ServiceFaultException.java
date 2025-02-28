package com.oracle.soapexample.FaultException;

import com.oracle.example.testing.soap.ws.soap.Fault;

public class ServiceFaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Fault fault;
	
	
	public ServiceFaultException(String message, Fault fault) {
		super(message);
		this.fault = fault;
	}
	
	public ServiceFaultException(String message,Throwable e, Fault fault) {
		super(message, e);
		this.fault = fault;
	}
	
	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

}
