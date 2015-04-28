package edu.daytonastate.cet3383;

import static edu.daytonastate.cet3383.TestFixtures.JAMES_BOND;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.daytonastate.cet3383.ebank.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EBankApplication.class)
@WebAppConfiguration
public class CreditCardAccountIntegrationTests extends CheckingAccountIntegrationTests {
	
	@Override
	@Test
	public void criticalPath() {
		Account account = openCreditCardAccount();
		account = cashWithdrawal(account, 250.00);
		Account checkingAccount = openDebitAccount();
		checkingAccount = deposit(checkingAccount, 1000.00);
		List<Account> accounts = payment(account, checkingAccount, 250.00);
		checkingAccount = accounts.get(accounts.size() - 2);
		account = accounts.get(accounts.size() - 1);
	}

	protected List<Account> payment(Account toAccount, Account fromAccount, Double amount) {
		Double previousToBalance = toAccount.currentBalance();
		Double expectedToBalance = previousToBalance + amount;
		Double previousFromBalance = fromAccount.currentBalance();
		Double expectedFromBalance = previousFromBalance - amount;
		String toAccountId = toAccount.id().toString();
		String fromAccountId = fromAccount.id().toString();
		bankingService.payment(JAMES_BOND, toAccountId, fromAccountId, amount);
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
	
	private Account openCreditCardAccount() {
		openCreditCardAccountFor(JAMES_BOND);
		Account account = lastAccount();
		assertTrue(account.currentBalance().equals(0.00));
		return account;
	}
	
	private void openCreditCardAccountFor(String customerId) {
		customerService.openCreditCardAccount(customerId);
	}
	
}