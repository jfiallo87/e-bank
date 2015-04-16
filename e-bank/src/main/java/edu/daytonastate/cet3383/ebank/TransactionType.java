package edu.daytonastate.cet3383.ebank;

public enum TransactionType {
	
	CASH_WITHDRAWAL("Cash Withdrawal"),
	DEPOSIT("Deposit"),
	PAYMENT("Payment"),
	TRANSFER("Transfer");
	
	private String label;
	
	private TransactionType(String label) {
		this.label = label;
	}
	
	public String label() {
		return label;
	}
	
}
