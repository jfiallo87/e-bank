package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;

public class CarLoanAccountTests extends AbstractCreditAccountTests {

	@Override
	protected AccountType accountType() {
		return CAR_LOAN;
	}

	@Override
	protected Account account() {
		return new CarLoanAccount(id, customerId, balance);
	}
	
}