package edu.daytonastate.cet3383.ebank;

public enum AccountType {
	
	CHECKING("Checking"),
	SAVINGS("Savings"),
	CREDIT_CARD("Credit"),
	CAR_LOAN("Car Loan"),
	BOAT_LOAN("Boat Loan"),
	MORTGAGE("Mortgage");
	
	private String label;
	
	private AccountType(String label) {
		this.label = label;
	}
	
	public String label() {
		return label;
	}
	
}