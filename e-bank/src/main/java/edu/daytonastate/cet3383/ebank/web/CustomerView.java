package edu.daytonastate.cet3383.ebank.web;

import edu.daytonastate.cet3383.ebank.Customer;

public class CustomerView {
	
	private String lastName;
	private String firstName;
	private Character middleNameInitial;

	public CustomerView() {}
	
	public CustomerView(Customer customer) {
		if (customer != null) {
			lastName = customer.name().lastName();
			firstName = customer.name().firstName();
			middleNameInitial = customer.name().middleNameInitial();
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Character getMiddleNameInitial() {
		return middleNameInitial;
	}

	public void setMiddleNameInitial(Character middleNameInitial) {
		this.middleNameInitial = middleNameInitial;
	}
	
}