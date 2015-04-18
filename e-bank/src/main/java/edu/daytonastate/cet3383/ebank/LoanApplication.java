package edu.daytonastate.cet3383.ebank;

public class LoanApplication {
	
	private Double balance;
	
	public LoanApplication(Double loanAmount) {
		if (loanAmount == null || loanAmount <= 0.00) {
			throw new UnsupportedOperationException();
		}
		
		balance = -loanAmount;
	}
	
	public Double balance() {
		return balance;
	}
	
}
