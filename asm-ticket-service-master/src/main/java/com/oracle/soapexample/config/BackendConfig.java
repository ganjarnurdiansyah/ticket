package com.oracle.soapexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BackendConfig {
	@Value("${rest.timeout}")
	private int timeout;
	
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(timeout);
		factory.setReadTimeout(timeout);
		RestTemplate rest = new RestTemplate();
		return rest;
		
	}
	
}
