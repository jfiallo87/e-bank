package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class MortgageAccountTests extends AbstractCreditAccountTests {

	@Override
	protected AccountType accountType() {
		return MORTGAGE;
	}

	@Override
	protected Account account() {
		return new MortgageAccount(id, customerId, balance);
	}
	
}