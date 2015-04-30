package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.ValidationError.*;

public class LoanApplication {
	
	private Double balance;
	
	public LoanApplication(Double loanAmount) {
		if (loanAmount == null || loanAmount <= 0.00) {
			throw new UnsupportedOperationException(INVALID_AMOUNT.message());
		}
		
		balance = -loanAmount;
	}
	
	public Double balance() {
		return balance;
	}
	
}
