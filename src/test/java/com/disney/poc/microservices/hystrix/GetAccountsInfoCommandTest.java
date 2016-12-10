package com.disney.poc.microservices.hystrix;

import junit.framework.TestCase;

import org.junit.Test;

public class GetAccountsInfoCommandTest extends TestCase {
	
	@Test
	public void test_getFallback(){
		GetAccountsInfoCommand command= new GetAccountsInfoCommand(null, null);
		command.getFallback();
	}

}
