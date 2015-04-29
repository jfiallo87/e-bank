package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.AccountType.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreditCardAccountTests extends AbstractCreditAccountTests {
	
	protected CreditCardAccount creditCardAccount;
	protected Double cash;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		creditCardAccount = (CreditCardAccount) creditAccount;
		cash = 100.00;
	}
	
	@Test
	public void cash() {
		Double expectedBalance = creditCardAccount.currentBalance() - cash;
		creditCardAccount.cash(cash);
		assertEquals(expectedBalance, creditCardAccount.currentBalance());
	}

	@Override
	protected AccountType accountType() {
		return CREDIT_CARD;
	}

	@Override
	protected Account account() {
		return new CreditCardAccount(id, customerId, balance, balanceLimit);
	}
	
}
