package edu.daytonastate.cet3383.ebank;

public enum PolicyType {
	
	TRANSACTION_DAILY_LIMIT("Transaction Daily Limit"),
	ACCOUNT_CREATION("Account Creation");
	
	private String description;
	
	private PolicyType(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}
	
}