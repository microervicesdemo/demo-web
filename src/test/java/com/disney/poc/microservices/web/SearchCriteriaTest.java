package com.disney.poc.microservices.web;

import junit.framework.TestCase;

import org.junit.Test;

public class SearchCriteriaTest extends TestCase {
	
	@Test
	public void test_setter_getter_methods(){
		SearchCriteria searchCriteria = new SearchCriteria();
		
		searchCriteria.setAccountNumber("123456789");
		searchCriteria.setSearchText(null);
		
		searchCriteria.getAccountNumber();
		searchCriteria.getSearchText();
		
		searchCriteria.isValid();
		searchCriteria.toString();
		
		searchCriteria.setAccountNumber(null);
		searchCriteria.isValid();
		
		searchCriteria.setSearchText(null);
		searchCriteria.isValid();
	}

}
