	package com.oracle.soapexample.endpoint;

	import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
	import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
	import org.springframework.ws.server.endpoint.annotation.RequestPayload;
	import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
	import com.oracle.example.testing.soap.ws.soap.GetToken;
	import com.oracle.example.testing.soap.ws.soap.GetTokenResponse;
import com.oracle.soapexample.service.WebServiceService;

import jakarta.xml.bind.JAXBElement;

	@Endpoint
	public class ServiceEndpoint {
		private static final String NAMESPACE_URI = "http://www.telkomsel.com/eai/AmdocsSM/SecurityManagementRq/v1.0";
		
		@Autowired
		private WebServiceService webService;
		
		@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetToken")
		@ResponsePayload
		
		public GetTokenResponse Service(@RequestPayload GetToken req)
		{
			GetTokenResponse response = new GetTokenResponse();
			
			response = webService.Service(req);
			return response;
		}

		private <T> JAXBElement<T> createJaxbElement(T object,
				Class<T> clazz) {
			
			return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
		}

		
	}
