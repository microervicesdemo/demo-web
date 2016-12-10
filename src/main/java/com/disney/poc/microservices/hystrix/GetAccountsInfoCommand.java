package com.disney.poc.microservices.hystrix;

import org.springframework.web.client.RestTemplate;

import com.disney.poc.microservices.web.Account;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetAccountsInfoCommand extends HystrixCommand<Account[]> {
	
	protected RestTemplate restTemplate;
	protected String serviceUrl;

	public GetAccountsInfoCommand(RestTemplate restTemplate, String serviceUrl) {
		super(HystrixCommandGroupKey.Factory.asKey("ACCOUNTS"));

		this.restTemplate = restTemplate;
		this.serviceUrl = serviceUrl;
	}

	@Override
	protected Account[] run() throws Exception {
		return restTemplate.getForObject(serviceUrl, Account[].class);
	}

	@Override
	protected Account[] getFallback() {
		return null;
	}

}
