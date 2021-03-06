package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;
import static edu.daytonastate.cet3383.ebank.ValidationError.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Range;

public abstract class DebitAccount extends Account {
	
	private List<AccountType> allowedTypes = Arrays.asList(CHECKING, SAVINGS);
	
	public DebitAccount(Id id, Id customerId, AccountType type, Double balance) {
		super(id, customerId, type, balance);
		
		if (!allowedTypes.contains(type)) {
			throw new UnsupportedOperationException(INVALID_ACCOUNT_TYPE.message());
		}
		
		if (balance < 0.0) {
			throw new UnsupportedOperationException(INVALID_BALANCE.message());
		}
	}
	
	public Double deposit(Double amount) {
		if (amount < 0.0) {
			throw new UnsupportedOperationException(INVALID_AMOUNT.message());
		}
		
		return changeBalance(amount);
	}
	
	public Double withdraw(Double amount) {
		double amountToWithdraw = -amount;
		
		if (currentBalance() < amount) {
			throw new UnsupportedOperationException(INVALID_AMOUNT.message());
		}
		
		return changeBalance(amountToWithdraw);
	}
	
	public Double cash(Double amount) {
		Range<Double> allowedCash = Range.between(0.00, 500.00);
		
		if (!allowedCash.contains(amount)) {
			throw new UnsupportedOperationException(INVALID_AMOUNT.message());
		}
		
		return withdraw(amount);
	}
	
}