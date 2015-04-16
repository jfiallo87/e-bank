package edu.daytonastate.cet3383.ebank;

public class CustomerName {
	
	private final String lastName;
	private final String firstName;
	private final Character middleNameInitial;
	
	public CustomerName(String lastName, String firstName, Character middleNameInitial) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleNameInitial = middleNameInitial;
	}

	public CustomerName(String lastName, String firstName) {
		this(lastName, firstName, null);
	}

	public String lastName() {
		return lastName;
	}
	
	public String firstName() {
		return firstName;
	}
	
	public Character middleNameInitial() {
		return middleNameInitial;
	}

	@Override
	public String toString() {
		String toString = lastName + ", " + firstName;
		
		if (middleNameInitial != null) {
			toString += " " + middleNameInitial;
		}
		
		return toString;
	}

}