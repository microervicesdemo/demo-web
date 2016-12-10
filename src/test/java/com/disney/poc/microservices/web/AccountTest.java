package com.disney.poc.microservices.web;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Test;

public class AccountTest extends TestCase {
	
	@Test
	public void test_setter_getter_methods(){
		Account account = new Account();
		
		account.setBalance(BigDecimal.TEN);
		account.setId(1);
		account.setNumber("123456789");
		account.setOwner("Pushpa Kumar");
		
		account.getBalance();
		account.getId();
		account.getNumber();
		account.getOwner();
		
		account = new Account(1l, "987654321", "Kumar", BigDecimal.ONE);
		account.toString();
	}

}
