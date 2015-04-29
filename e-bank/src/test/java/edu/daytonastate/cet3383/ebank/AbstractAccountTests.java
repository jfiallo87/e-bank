package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.TestFixtures.JAMES_BOND;
import static edu.daytonastate.cet3383.TestFixtures.ONE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractAccountTests {
	
	protected Account account;
	protected AccountType type;
	protected Id id;
	protected Id customerId;
	protected Double balance;
	protected Double balanceLimit;
	protected Double amount;
	
	@Before
	public void setUp() throws Exception {
		type = accountType();
		id = new Id(ONE);
		customerId = new Id(JAMES_BOND);
		balance = accountBalance();
		balanceLimit = accountBalanceLimit();
		amount = accountAmount();
		account = account();
	}
	
	@Test
	public void id() {
		assertEquals(id, account.id());
	}
	
	@Test
	public void customerId() {
		assertEquals(customerId, account.customerId());
	}
	
	@Test
	public void type() {
		assertEquals(type, account.type());
	}
	
	@Test
	public void currentBalance() {
		assertEquals(balance, account.currentBalance());
	}
	
	@Test
	public void balanceLimit() {
		assertEquals(balanceLimit, account.balanceLimit());
	}
	
	@Test
	public void accessibleBy() {
		assertTrue(account.accessibleBy(customerId));
	}
	
	@Test
	public void notAccessibleBy() {
		Id anotherCustomerId = mock(Id.class);
		assertFalse(account.accessibleBy(anotherCustomerId));
	}
	
	@Test
	public void changeBalance() {
		Double expectedBalance = account.currentBalance() + amount;
		account.changeBalance(amount);
		assertEquals(expectedBalance, account.currentBalance());
	}
	
	protected abstract AccountType accountType();
	
	protected abstract Double accountBalance();
	
	protected abstract Double accountBalanceLimit();
	
	protected abstract Double accountAmount();
	
	protected abstract Account account();

}