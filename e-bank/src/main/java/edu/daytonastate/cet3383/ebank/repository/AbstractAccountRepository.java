package edu.daytonastate.cet3383.ebank.repository;

import java.util.HashMap;
import java.util.Map;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.AccountType;
import edu.daytonastate.cet3383.ebank.BoatLoanAccount;
import edu.daytonastate.cet3383.ebank.CarLoanAccount;
import edu.daytonastate.cet3383.ebank.CheckingAccount;
import edu.daytonastate.cet3383.ebank.CreditCardAccount;
import edu.daytonastate.cet3383.ebank.Id;
import edu.daytonastate.cet3383.ebank.MortgageAccount;
import edu.daytonastate.cet3383.ebank.SavingsAccount;

public abstract class AbstractAccountRepository {
	
	protected static final String ACCOUNT_CUSTOMER_ID = "customerId";
	protected static final String ACCOUNT_BALANCE_LIMIT = "balanceLimit";
	protected static final String ACCOUNT_BALANCE = "balance";
	protected static final String ACCOUNT_TYPE = "type";
	
	protected Map<String, Object> accountRecord(Account account) {
		Map<String, Object> record = new HashMap<>();
		record.put(ACCOUNT_CUSTOMER_ID, account.customerId());
		record.put(ACCOUNT_TYPE, account.type());
		record.put(ACCOUNT_BALANCE, account.currentBalance());
		record.put(ACCOUNT_BALANCE_LIMIT, account.balanceLimit());
		
		return record;
	}
	
	protected Account account(Id accountId, Map<String, Object> record) {
		Account account = null;
		
		if (record != null) {
			Id customerId = (Id) record.get(ACCOUNT_CUSTOMER_ID);
			AccountType type = (AccountType) record.get(ACCOUNT_TYPE);
			Double balance = (Double) record.get(ACCOUNT_BALANCE);
			Double balanceLimit = (Double) record.get(ACCOUNT_BALANCE_LIMIT);
			
			switch (type) {
				case BOAT_LOAN: {
					account = new BoatLoanAccount(accountId, customerId, balance);
					break;
				}
				case CAR_LOAN: {
					account = new CarLoanAccount(accountId, customerId, balance);
					break;
				}
				case CHECKING: {
					account = new CheckingAccount(accountId, customerId, balance);
					break;
				}
				case CREDIT_CARD: {
					account = new CreditCardAccount(accountId, customerId, balance, balanceLimit);
					break;
				}
				case MORTGAGE: {
					account = new MortgageAccount(accountId, customerId, balance);
					break;
				}
				case SAVINGS: {
					account = new SavingsAccount(accountId, customerId, balance);
					break;
				}
				default: {
					break;
				}
			}
		}
		
		return account;
	}
	
}