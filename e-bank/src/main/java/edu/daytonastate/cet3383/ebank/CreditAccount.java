package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

import java.util.Arrays;
import java.util.List;

public abstract class CreditAccount extends Account {
	
	private List<AccountType> allowedTypes = Arrays.asList(CREDIT_CARD, CAR_LOAN, BOAT_LOAN, MORTGAGE);
	
	public CreditAccount(Id id, Id customerId, AccountType type, Double balance) {
		super(id, customerId, type, balance);
		
		if (!allowedTypes.contains(type)) {
			throw new UnsupportedOperationException();
		}
	}
	
	public Double submitPayment(Double amount) {
		if (amount < 0.0) {
			throw new UnsupportedOperationException();
		}
		
		if (amount > -currentBalance()) {
			throw new UnsupportedOperationException();
		}
		
		return changeBalance(amount);
	}

}