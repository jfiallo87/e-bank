package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class SavingsAccount extends DebitAccount {

	public SavingsAccount(Id id, Id customerId, Double balance) {
		super(id, customerId, SAVINGS, balance);
	}

}