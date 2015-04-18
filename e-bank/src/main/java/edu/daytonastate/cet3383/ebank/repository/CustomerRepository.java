package edu.daytonastate.cet3383.ebank.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.daytonastate.cet3383.ebank.Customer;
import edu.daytonastate.cet3383.ebank.CustomerName;
import edu.daytonastate.cet3383.ebank.Id;

@Repository
public class CustomerRepository {
	
	private static final String CUSTOMER_MIDDLE_NAME_INITIAL = "middleNameInitial";
	private static final String CUSTOMER_FIRST_NAME = "firstName";
	private static final String CUSTOMER_LAST_NAME = "lastName";
	
	private static Map<Id, Map<String, Object>> customers = new HashMap<>();
	
	public void save(Customer customer) {
		Map<String, Object> customerRecord = new HashMap<>();
		customerRecord.put(CUSTOMER_LAST_NAME, customer.name().lastName());
		customerRecord.put(CUSTOMER_FIRST_NAME, customer.name().firstName());
		customerRecord.put(CUSTOMER_MIDDLE_NAME_INITIAL, customer.name().middleNameInitial());
		
		customers.put(customer.id(), customerRecord);
	}
	
	public Customer findById(Id customerId) {
		Customer customer = null;
		
		Map<String, Object> customerRecord = customers.get(customerId);
		
		if (customerRecord != null) {
			String lastName = (String) customerRecord.get(CUSTOMER_LAST_NAME);
			String firstName = (String) customerRecord.get(CUSTOMER_FIRST_NAME);
			Character middleNameInitial = (Character) customerRecord.get(CUSTOMER_MIDDLE_NAME_INITIAL);
			
			CustomerName name = new CustomerName(lastName, firstName, middleNameInitial);
			
			customer = new Customer(customerId, name );
		}
		
		return customer;
	}
	
}
