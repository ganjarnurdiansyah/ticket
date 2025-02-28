package com.oracle.soapexample.service;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.ws.client.WebServiceFaultException;

import com.oracle.example.testing.soap.ws.soap.Fault;
import com.oracle.example.testing.soap.ws.soap.GetToken;
import com.oracle.example.testing.soap.ws.soap.GetTokenResponse;
import com.oracle.soapexample.config.ServiceAccounts;
import com.oracle.soapexample.FaultException.ServiceFaultException;
import com.oracle.soapexample.data.BackendRequest;
import com.oracle.soapexample.data.BackendResponse;

@Service
public class WebServiceService extends ResponseEntityExceptionHandler {
	@Value("${env.setting:PROD}")
	private String envSetting;

	@Value("${env.xadp.online.username}")
	private String usernameOnl;

	@Value("${env.xadp.online.password}")
	private String passwordOnl;

	@Value("${env.xadp.batch.username}")
	private String usernameBatch;

	@Value("${env.xadp.batch.password}")
	private String passwordBatch;

	public static Logger logger = LoggerFactory.getLogger(WebServiceService.class);

	@Autowired
	BackendService backendService = new BackendService();

	public GetTokenResponse Service(GetToken request)// throws ErrorHandling
	{
		GetTokenResponse response = new GetTokenResponse();
		ServiceAccounts serviceAcc = new ServiceAccounts();
		int index = 0;
		boolean validation = false;

		for (int i = 0; i < serviceAcc.appID.size(); i++) {
			if (request.getApplicationName().equals(serviceAcc.appID.get(i))) {
				validation = true;
				index = i;
				logger.info("val:" + validation + " idx:" + index + " idxApId:" + serviceAcc.appID.size());
			}
		}

		if (request.getApplicationName() == null || request.getApplicationName() == " ") {
			String errorMessage = "Internal Server Error";
			Fault fault = new Fault();
			fault.setMessage("ApplicationName is null or invalid");
			fault.setStatuscode("9999");
			throw new ServiceFaultException(errorMessage, fault);

		} else {
			if (validation) {
				try {
					BackendRequest asmRq = new BackendRequest();

					if ("DEV".equalsIgnoreCase(envSetting)) {
						asmRq.setUserId(serviceAcc.username_dev.get(index));
						asmRq.setPassword(serviceAcc.password_dev.get(index));
					} else if ("PREPROD".equalsIgnoreCase(envSetting)) {
						asmRq.setUserId(serviceAcc.username_preprod.get(index));
						asmRq.setPassword(serviceAcc.password_preprod.get(index));
					} else {
						asmRq.setUserId(serviceAcc.username_prod.get(index));
						asmRq.setPassword(serviceAcc.password_prod.get(index));
					}

					logger.info((" password:" + asmRq.getPassword() + " userid:" + asmRq.getUserId()));

					BackendResponse asmRs = new BackendResponse();
					asmRs = backendService.response(asmRq);

					response.setTicket(asmRs.getTicket());
					response.setUserID(asmRs.getUserId());
				} catch (Exception e) {
					e.printStackTrace();
					String errorMessage = "Internal Server Error";
					Fault fault = new Fault();
					fault.setMessage(e.getMessage());
					fault.setStatuscode("9998");
					throw new ServiceFaultException(errorMessage, fault);
				}
			} else {
				// xAdapter ASMTicket
				if (request.getApplicationName().equalsIgnoreCase("TC-Adp-ON")) {
					try {
						BackendRequest asmRq = new BackendRequest();

						asmRq.setUserId(usernameOnl);
						asmRq.setPassword(passwordOnl);

						logger.info(" password:" + asmRq.getPassword() + " userid:" + asmRq.getUserId());

						BackendResponse asmRs = new BackendResponse();
						asmRs = backendService.response(asmRq);
						response.setTicket(asmRs.getTicket());
						response.setUserID(asmRs.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
						String errorMessage = "Internal Server Error";
						Fault fault = new Fault();
						fault.setMessage(e.getMessage());
						fault.setStatuscode("9998");
						throw new ServiceFaultException(errorMessage, fault);
					}
				} else if (request.getApplicationName().equalsIgnoreCase("TC-Adp-BT")) {
					try {
						BackendRequest asmRq = new BackendRequest();

						asmRq.setUserId(usernameBatch);
						asmRq.setPassword(usernameBatch);

						logger.info(" password:" + asmRq.getPassword() + " userid:" + asmRq.getUserId());

						BackendResponse asmRs = new BackendResponse();
						asmRs = backendService.response(asmRq);
						response.setTicket(asmRs.getTicket());
						response.setUserID(asmRs.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
						String errorMessage = "Internal Server Error";
						Fault fault = new Fault();
						fault.setMessage(e.getMessage());
						fault.setStatuscode("9998");
						throw new ServiceFaultException(errorMessage, fault);
					}
				} else {
					String errorMessage = "Internal Server Error";
					Fault fault = new Fault();
					fault.setMessage("ApplicationName is null or invalid");
					fault.setStatuscode("9999");
					throw new ServiceFaultException(errorMessage, fault);
				}
			}
			return response;
		}
	}
}
