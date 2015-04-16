package edu.daytonastate.cet3383.ebank;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
	
	private static final String ACCOUNT_CUSTOMER_ID = "customerId";
	private static final String ACCOUNT_BALANCE_LIMIT = "balanceLimit";
	private static final String ACCOUNT_BALANCE = "balance";
	private static final String ACCOUNT_TYPE = "type";
	
	private static Map<Id, Map<String, Object>> accounts = new HashMap<>();
	
	public void save(Account account) {
		Map<String, Object> accountRecord = new HashMap<>();
		accountRecord.put(ACCOUNT_CUSTOMER_ID, account.customerId());
		accountRecord.put(ACCOUNT_TYPE, account.type());
		accountRecord.put(ACCOUNT_BALANCE, account.currentBalance());
		accountRecord.put(ACCOUNT_BALANCE_LIMIT, account.balanceLimit());
		
		accounts.put(account.id(), accountRecord);
	}
	
	public Account findById(Id accountId) {
		Account account = null;
		
		Map<String, Object> accountRecord = accounts.get(accountId);
		
		if (accountRecord != null) {
			Id customerId = (Id) accountRecord.get(ACCOUNT_CUSTOMER_ID);
			AccountType type = (AccountType) accountRecord.get(ACCOUNT_TYPE);
			Double balance = (Double) accountRecord.get(ACCOUNT_BALANCE);
			Double balanceLimit = (Double) accountRecord.get(ACCOUNT_BALANCE_LIMIT);
			
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