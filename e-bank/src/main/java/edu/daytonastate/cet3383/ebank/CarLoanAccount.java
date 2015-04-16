package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class CarLoanAccount extends CreditAccount {

	public CarLoanAccount(Id id, Id customerId, Double balance) {
		super(id, customerId, CAR_LOAN, balance);
	}

}