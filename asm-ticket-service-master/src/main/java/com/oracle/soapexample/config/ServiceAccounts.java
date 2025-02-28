package com.oracle.soapexample.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceAccounts {
	
	public List<String> appID = new ArrayList<>(Arrays.asList("TC", "T-Care"));
	public List<String> username_dev = new ArrayList<>(Arrays.asList("tks_pro", "tcare"));
	public List<String> password_dev = new ArrayList<>(Arrays.asList("Infra_11!", "Unix12"));
	public List<String> username_prod = new ArrayList<>(Arrays.asList("tks_tcare", "tks_tcare"));
	public List<String> password_prod = new ArrayList<>(Arrays.asList("Unix11", "Unix11"));
	public List<String> username_preprod = new ArrayList<>(Arrays.asList("ABPBatchUser", "ABPBatchUser"));
	public List<String> password_preprod = new ArrayList<>(Arrays.asList("Unix11", "Unix11"));
}