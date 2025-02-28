package com.oracle.soapexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


import com.oracle.soapexample.data.BackendRequest;
import com.oracle.soapexample.data.BackendResponse;

@Service
public class BackendService {
	public static Logger logger = LoggerFactory.getLogger(BackendService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${rest.post.url}")
	private String url;

	//@RequestMapping(value = "/post", method = RequestMethod.POST, produces = "application/json")
	public BackendResponse response(@RequestBody BackendRequest request) {
		Long startTimemilis = System.currentTimeMillis();
		BackendResponse response = new BackendResponse();
		
		response = restTemplate.postForObject(url, request, BackendResponse.class);
		logger.info("Detail (" + response + ")");
		logger.info("ASMTicket: [Service] End ASMTicketResponse " + (System.currentTimeMillis() - startTimemilis) + " ms");
    	
		return response;
	}
}
