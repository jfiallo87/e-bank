package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class CheckingAccountTests extends AbstractDebitAccountTests {

	@Override
	protected AccountType accountType() {
		return CHECKING;
	}

	@Override
	protected Account account() {
		return new CheckingAccount(id, customerId, balance);
	}

}
