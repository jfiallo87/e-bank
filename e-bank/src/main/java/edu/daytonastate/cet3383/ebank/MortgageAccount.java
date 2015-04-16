package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class MortgageAccount extends CreditAccount {

	public MortgageAccount(Id id, Id customerId, Double balance) {
		super(id, customerId, MORTGAGE, balance);
	}

}