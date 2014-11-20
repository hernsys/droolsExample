package com.plugtree.dslExample;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;


import com.plugtree.dslExample.entities.Customer;



public class DrlExampleTestCase extends BaseTest {
	
	/**
	 * Customer: Karen 
	 * Expense greater than 1000
	 * registered = false
	 * 
	 * Discount:10%
	 * 
	 */
	@Test
	public void testFirstCase(){
		boolean registered = false;
		Double expense = 1800D;
		
		StatelessKieSession session = TestUtil.createStatelessKieSession(DRL_PATH);
		Customer karen = new Customer(KAREN, registered, expense);
        session.execute(Arrays.asList(karen));

        Assert.assertNotNull(karen.getDiscount());
        Assert.assertEquals(180D, karen.getDiscount());
	}
	
	/**
	 * Customer: Mac 
	 * Expense lesser than 1000
	 * registered = false
	 * 
	 * Discount:0%
	 * 
	 */
	@Test
	public void testSecondCase(){
		boolean registered = false;
		Double expense = 180D;
		
		StatelessKieSession session = TestUtil.createStatelessKieSession(DRL_PATH);
		Customer mac = new Customer(MAC, registered, expense);
        session.execute(Arrays.asList(mac));

        Assert.assertNull(mac.getDiscount());
	}
	
	/**
	 * Customer: John 
	 * Expense greater than 1000
	 * registered = true
	 * 
	 * Discount:15%
	 * 
	 */
	@Test
	public void testThirdCase(){
		boolean registered = true;
		Double expense = 4000D;
		
		StatelessKieSession session = TestUtil.createStatelessKieSession(DRL_PATH);
		Customer john = new Customer(JOHN, registered, expense);
        session.execute(Arrays.asList(john));

        Assert.assertNotNull(john.getDiscount());
        Assert.assertEquals(600D, john.getDiscount());
	}

}
