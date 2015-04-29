package edu.daytonastate.cet3383.ebank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractCreditAccountTests extends AbstractAccountTests {
	
	protected CreditAccount creditAccount;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		creditAccount = (CreditAccount) account;
	}
	
	@Test
	public void submitPayment() {
		Double expectedBalance = creditAccount.currentBalance() + amount;
		creditAccount.submitPayment(amount);
		assertEquals(expectedBalance, creditAccount.currentBalance());
	}

	@Override
	protected Double accountBalance() {
		return -1000.00;
	}

	@Override
	protected Double accountBalanceLimit() {
		return Double.MAX_VALUE;
	}

	@Override
	protected Double accountAmount() {
		return 100.00;
	}
	
}