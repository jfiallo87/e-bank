package edu.daytonastate.cet3383.ebank;

public class Customer {
	
	private final Id id;
	private CustomerName name;
	
	public Customer(Id id, CustomerName name) {
		this.id = id;
		this.name = name;
	}
	
	public Id id() {
		return id;
	}
	
	public CustomerName name() {
		return name;
	}
	
}