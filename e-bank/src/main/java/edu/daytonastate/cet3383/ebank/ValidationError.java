package edu.daytonastate.cet3383.ebank;

public enum ValidationError {
	
	INVALID_AMOUNT("Invalid amount"),
	INVALID_ACCOUNT_TYPE("Invalid account type"),
	INVALID_BALANCE("Invalid balance"),
	TRANSACTIONAL_DAILY_LIMIT_EXCEEDED("Transaction daily limit exceeded");
	
	private String message;
	
	private ValidationError(String message) {
		this.message = message;
	}
	
	public String message() {
		return message;
	}

}
