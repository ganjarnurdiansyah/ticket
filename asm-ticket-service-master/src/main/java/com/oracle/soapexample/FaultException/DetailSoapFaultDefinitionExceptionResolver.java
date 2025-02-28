package com.oracle.soapexample.FaultException;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import com.oracle.example.testing.soap.ws.soap.Fault;
import com.oracle.soapexample.FaultException.ServiceFaultException;

public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {
	private static final QName CODE = new QName("statuscode");
	private static final QName MESSAGE = new QName("message");
	
	@Override
	protected void customizeFault(Object endpoint, Exception e, SoapFault soapFault) {
		logger.warn("Exception processed ", e);
		if(e instanceof ServiceFaultException) {
			Fault fault = ((ServiceFaultException) e).getFault();
			SoapFaultDetail detail = soapFault.addFaultDetail();
			detail.addFaultDetailElement(CODE).addText(fault.getStatuscode());
			detail.addFaultDetailElement(MESSAGE).addText(fault.getMessage());
		}
	}
}
