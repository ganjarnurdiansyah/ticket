package com.oracle.soapexample.config;

import java.util.List;
import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import com.oracle.soapexample.FaultException.DetailSoapFaultDefinitionExceptionResolver;
import com.oracle.soapexample.FaultException.ServiceFaultException;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
@Bean
public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
	
	MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	servlet.setApplicationContext(applicationContext);
	servlet.setTransformWsdlLocations(true);
	return new ServletRegistrationBean<>(servlet, "/Service/*");
		
}

@Bean(name = "ASMTicket")
public Wsdl11Definition ASMTicketWsdl11Definition() { 
DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
wsdl11Definition.setPortTypeName("ASMTicketPort");
wsdl11Definition.setLocationUri("/Service");
wsdl11Definition.setTargetNamespace("http://www.telkomsel.com/pro/proxy_service/ASMTicket/v1.0");
wsdl11Definition.setRequestSuffix("Token");
wsdl11Definition.setResponseSuffix("TokenResponse");
wsdl11Definition.setSchemaCollection(ASMTicketSchema());
wsdl11Definition.setFaultSuffix("fault");
return wsdl11Definition;


}
@Bean
public XsdSchemaCollection ASMTicketSchema() {
	CommonsXsdSchemaCollection schema = new CommonsXsdSchemaCollection(
			new ClassPathResource("xsd/MainASMTicket.xsd"));
	schema.setInline(true);
	
	return schema;
}

@Override
public void addInterceptors(List<EndpointInterceptor> interceptors) {
}

@Bean
public SoapFaultMappingExceptionResolver exceptionResolver() {
	SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();
	
	SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
	faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
	exceptionResolver.setDefaultFault(faultDefinition);
	
	Properties errorMappings = new Properties();
	errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
	errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
	exceptionResolver.setExceptionMappings(errorMappings);
	exceptionResolver.setOrder(1);
	return exceptionResolver;
}
}

