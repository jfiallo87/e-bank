package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class SavingsAccountTests extends AbstractDebitAccountTests {

	@Override
	protected AccountType accountType() {
		return SAVINGS;
	}

	@Override
	protected Account account() {
		return new SavingsAccount(id, customerId, balance);
	}

}
