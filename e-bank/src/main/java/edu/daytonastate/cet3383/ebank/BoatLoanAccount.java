package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class BoatLoanAccount extends CreditAccount {

	public BoatLoanAccount(Id id, Id customerId, Double balance) {
		super(id, customerId, BOAT_LOAN, balance);
	}

}