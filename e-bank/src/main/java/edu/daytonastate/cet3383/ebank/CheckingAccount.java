package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class CheckingAccount extends DebitAccount {

	public CheckingAccount(Id id, Id customerId, Double balance) {
		super(id, customerId, CHECKING, balance);
	}

}
