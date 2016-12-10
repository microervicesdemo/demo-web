package com.disney.poc.microservices.hystrix;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.disney.poc.microservices.web.Account;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetAccountInfoCommand extends HystrixCommand<Account> {

	protected RestTemplate restTemplate;
	protected String serviceUrl;

	public GetAccountInfoCommand(RestTemplate restTemplate, String serviceUrl) {
		super(HystrixCommandGroupKey.Factory.asKey("ACCOUNT"));

		this.restTemplate = restTemplate;
		this.serviceUrl = serviceUrl;
	}

	@Override
	protected Account run() throws Exception {
		return restTemplate.getForObject(serviceUrl, Account.class);
	}

	@Override
	protected Account getFallback() {
		return new Account(1l, "987654321", "Test Owner", BigDecimal.ZERO);
	}

}
