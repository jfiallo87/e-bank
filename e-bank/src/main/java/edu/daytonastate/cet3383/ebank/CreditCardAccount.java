package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;
import static edu.daytonastate.cet3383.ebank.ValidationError.*;

import org.apache.commons.lang3.Range;

public class CreditCardAccount extends CreditAccount {
	
	public CreditCardAccount(Id id, Id customerId, Double balance, Double balanceLimit) {
		super(id, customerId, CREDIT_CARD, balance);
		
		this.balanceLimit = balanceLimit;
	}
	
	public Double cash(Double amount) {
		Range<Double> allowedCashBack = Range.between(0.00, balanceLimit + currentBalance());
		
		if (!allowedCashBack.contains(amount)) {
			throw new UnsupportedOperationException(INVALID_AMOUNT.message());
		}
		
		return changeBalance(-amount);
	}
	
}