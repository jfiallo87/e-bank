package edu.daytonastate.cet3383;

import static edu.daytonastate.cet3383.TestFixtures.JAMES_BOND;
import static org.junit.Assert.assertTrue;

import java.util.List;

import edu.daytonastate.cet3383.ebank.Account;

public abstract class AbstractLoanAccountIntegrationTests extends CreditCardAccountIntegrationTests {
	
	@Override
	public void criticalPath() {
		Account account = openLoanAccount();
		Account checkingAccount = openDebitAccount();
		checkingAccount = deposit(checkingAccount, 1000.00);
		List<Account> accounts = payment(account, checkingAccount, 250.00);
		checkingAccount = accounts.get(accounts.size() - 2);
		account = accounts.get(accounts.size() - 1);
	}
	
	private Account openLoanAccount() {
		openLoanAccountFor(JAMES_BOND);
		Account account = lastAccount();
		assertTrue(account.currentBalance().equals(-loanAmount()));
		return account;
	}
	
	protected abstract void openLoanAccountFor(String customerId);

	protected abstract double loanAmount();
	
}