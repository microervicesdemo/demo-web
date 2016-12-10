package com.disney.poc.microservices.hystrix;

import junit.framework.TestCase;

import org.junit.Test;

public class GetAccountInfoCommandTest extends TestCase {
	
	@Test
	public void test_getFallback(){
		GetAccountInfoCommand command= new GetAccountInfoCommand(null, null);
		command.getFallback();
	}

}
