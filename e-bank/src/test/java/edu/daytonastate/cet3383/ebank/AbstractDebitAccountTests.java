package edu.daytonastate.cet3383.ebank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractDebitAccountTests extends AbstractAccountTests {
	
	protected DebitAccount debitAccount;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		debitAccount = (DebitAccount) account;
	}

	@Test
	public void deposit() {
		Double expectedBalance = debitAccount.currentBalance() + amount;
		debitAccount.deposit(amount);
		assertEquals(expectedBalance, debitAccount.currentBalance());
	}
	
	@Test
	public void withdraw() {
		debitAccount.deposit(amount);
		Double withdrawalAmount = amount / 4;
		Double expectedBalance = debitAccount.currentBalance() - withdrawalAmount;
		debitAccount.withdraw(withdrawalAmount);
		assertEquals(expectedBalance, debitAccount.currentBalance());
	}
	
	@Test
	public void cash() {
		debitAccount.deposit(amount);
		Double cashAmount = amount / 4;
		Double expectedBalance = debitAccount.currentBalance() - cashAmount;
		debitAccount.cash(cashAmount);
		assertEquals(expectedBalance, debitAccount.currentBalance());
	}
	
	@Override
	protected Double accountBalance() {
		return 0.00;
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
