package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class BoatLoanAccountTests extends AbstractCreditAccountTests {

	@Override
	protected AccountType accountType() {
		return BOAT_LOAN;
	}

	@Override
	protected Account account() {
		return new BoatLoanAccount(id, customerId, balance);
	}

}