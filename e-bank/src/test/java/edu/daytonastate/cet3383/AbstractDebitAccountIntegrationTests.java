package edu.daytonastate.cet3383;

import static edu.daytonastate.cet3383.TestFixtures.JAMES_BOND;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.service.BankingService;
import edu.daytonastate.cet3383.ebank.service.CustomerService;

public abstract class AbstractDebitAccountIntegrationTests {
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected BankingService bankingService;

	public void criticalPath() {
		Account account = openDebitAccount();
		Account anotherAccount = openDebitAccount();
		account = deposit(account, 1000.00);
		account = cashWithdrawal(account, 250.00);
		List<Account> accounts = transfer(anotherAccount, account, 250.00);
		anotherAccount = accounts.get(accounts.size() - 2);
		account = accounts.get(accounts.size() - 1);
	}

	protected abstract void openDebitAccountFor(String customerId);
	
	protected Account openDebitAccount() {
		openDebitAccountFor(JAMES_BOND);
		Account account = lastAccount();
		assertTrue(account.currentBalance().equals(0.00));
		return account;
	}

	protected Account lastAccount() {
		List<Account> accounts = customerService.accounts(JAMES_BOND);
		sortAccountsById(accounts);
		Account account = accounts.get(accounts.size() - 1);
		return account;
	}

	protected Account deposit(Account intoAccount, Double amount) {
		Double previousBalance = intoAccount.currentBalance();
		Double expectedBalance = previousBalance + amount;
		String intoAccountId = intoAccount.id().toString();
		bankingService.deposit(JAMES_BOND, intoAccountId, amount);
		Account account = bankingService.info(JAMES_BOND, intoAccountId);
		Double currentBalance = account.currentBalance();
		assertTrue("Expected " + expectedBalance + " Actual " + currentBalance, currentBalance.equals(expectedBalance));
		return account;
	}

	protected Account cashWithdrawal(Account fromAccount, Double amount) {
		Double previousBalance = fromAccount.currentBalance();
		Double expectedBalance = previousBalance - amount;
		String fromAccountId = fromAccount.id().toString();
		bankingService.cashWithdrawal(JAMES_BOND, fromAccountId, amount);
		Account account = bankingService.info(JAMES_BOND, fromAccountId);
		Double currentBalance = account.currentBalance();
		assertTrue("Expected " + expectedBalance + " Actual " + currentBalance, currentBalance.equals(expectedBalance));
		return account;
	}

	private List<Account> transfer(Account toAccount, Account fromAccount, Double amount) {
		Double previousToBalance = toAccount.currentBalance();
		Double expectedToBalance = previousToBalance + amount;
		Double previousFromBalance = fromAccount.currentBalance();
		Double expectedFromBalance = previousFromBalance - amount;
		String toAccountId = toAccount.id().toString();
		String fromAccountId = fromAccount.id().toString();
		bankingService.transfer(JAMES_BOND, toAccountId, fromAccountId, amount);
		Account accountTo = bankingService.info(JAMES_BOND, toAccountId);
		Account accountFrom = bankingService.info(JAMES_BOND, fromAccountId);
		Double accountToCurrentBalance = accountTo.currentBalance();
		
		try {
			assertTrue("Expected " + expectedToBalance + " Actual " + accountToCurrentBalance, accountToCurrentBalance.equals(expectedToBalance));
			Double accountFromCurrentBalance = accountFrom.currentBalance();
			assertTrue("Expected " + expectedFromBalance + " Actual " + accountFromCurrentBalance, accountFromCurrentBalance.equals(expectedFromBalance));
		} catch (Throwable t) {
			throw t;
		}
		
		return Arrays.asList(accountTo, accountFrom);
	}

	private void sortAccountsById(List<Account> accounts) {
		Collections.sort(accounts, new Comparator<Account>() {
	
			@Override
			public int compare(Account a1, Account a2) {
				return Long.valueOf(a1.id().toString()).compareTo(Long.valueOf(a2.id().toString()));
			}
			
		});
	}
	
}