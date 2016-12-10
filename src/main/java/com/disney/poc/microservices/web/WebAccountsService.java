package com.disney.poc.microservices.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.disney.poc.microservices.hystrix.GetAccountInfoCommand;
import com.disney.poc.microservices.hystrix.GetAccountsInfoCommand;

@Service
public class WebAccountsService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;
	
	@Value("${circuit.breaker.enable.flag}")
	private String circuitBreakerFlag;
	
	protected Logger logger = Logger.getLogger(WebAccountsService.class.getName());

	public WebAccountsService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory());
		
		logger.info("Hystrix Circuit Breaker Flag :: "+circuitBreakerFlag);
	}

	public Account findByNumber(String accountNumber) {
		if("true".equalsIgnoreCase(circuitBreakerFlag)){
			return new GetAccountInfoCommand(restTemplate, serviceUrl + "/accounts/"+accountNumber).execute();
		} else {
			return restTemplate.getForObject(serviceUrl + "/accounts/{number}", Account.class, accountNumber);
		}
	}

	public List<Account> byOwnerContains(String name) {
		Account[] accounts = null;
		
		if("true".equalsIgnoreCase(circuitBreakerFlag)){
			accounts = new GetAccountsInfoCommand(restTemplate, serviceUrl + "/accounts/owner/"+name).execute();
		} else {
			accounts = restTemplate.getForObject(serviceUrl + "/accounts/owner/{name}", Account[].class, name);
		}

		if (accounts == null || accounts.length == 0){
			return null;
		} else {
			return Arrays.asList(accounts);
		}
		
	}
	
}
