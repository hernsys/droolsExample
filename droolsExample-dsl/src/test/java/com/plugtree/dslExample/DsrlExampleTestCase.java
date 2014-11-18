package com.plugtree.dslExample;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.plugtree.dslExample.entities.Customer;



public class DsrlExampleTestCase {
	
	private static final String DSRL_PATH = "com/resources/dslExample/dslExample.dslr";
	private static final String DSL_PATH = "com/resources/dslExample/dslExample.dsl";

	@Test
	public void testCustomers(){
		StatelessKieSession session = TestUtil.createStatelessKieSession(DSRL_PATH, DSL_PATH);
        // expense less than 1000
		Customer customer1 = new Customer("Mary", false, 800D);
        // expense greater than 1000
		Customer customer2 = new Customer("Mac", false, 1200D);
        // it is a customer registered
        Customer customer3 = new Customer("John", true, 400D);
        session.execute(Arrays.asList(customer1, customer2, customer3));

        Assert.assertNull(customer1.getDiscount());
        Assert.assertEquals(120D, customer2.getDiscount());
        Assert.assertEquals(20D, customer3.getDiscount());

	}

}
