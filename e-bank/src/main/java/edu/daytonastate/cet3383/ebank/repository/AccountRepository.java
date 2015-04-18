package edu.daytonastate.cet3383.ebank.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Id;

@Repository
public class AccountRepository extends AbstractAccountRepository {
	
	private static Map<Id, Map<String, Object>> accounts = new HashMap<>();
	
	public void save(Account account) {
		if (account != null) {
			Map<String, Object> accountRecord = accountRecord(account);
			
			accounts.put(account.id(), accountRecord);
		}
	}
	
	public Account findById(Id accountId) {
		Account account = null;
		
		Map<String, Object> accountRecord = accounts.get(accountId);
		
		account = account(accountId, accountRecord);
		
		return account;
	}
	
	public List<Account> findByCustomerId(Id customerId) {
		List<Account> customerAccounts = new ArrayList<>();
		
		for (Id accountId : accounts.keySet()) {
			Map<String, Object> accountRecord = accounts.get(accountId);
			Id accountCustomerId = (Id) accountRecord.get(ACCOUNT_CUSTOMER_ID);
			
			if (accountCustomerId.equals(customerId)) {
				customerAccounts.add(findById(accountId));
			}
		}
		
		return customerAccounts;
	}
	
}