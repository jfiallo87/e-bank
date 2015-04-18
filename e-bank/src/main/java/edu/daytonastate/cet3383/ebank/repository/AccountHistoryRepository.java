package edu.daytonastate.cet3383.ebank.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Id;

@Repository
public class AccountHistoryRepository extends AbstractAccountRepository {
	
	private static Map<Date, Map<Id, Map<String, Object>>> accountHistory = new HashMap<>();
	
	public void save(Date transactionDate, Account account) {
		if (account != null) {
			if (!accountHistory.containsKey(transactionDate)) {
				accountHistory.put(transactionDate, new HashMap<Id, Map<String, Object>>());
			}
			
			Map<String, Object> accountHistoryRecord = accountRecord(account);
			
			accountHistory.get(transactionDate).put(account.id(), accountHistoryRecord);
		}
	}
	
	public List<Account> findByTransactionDate(Date transactionDate) {
		List<Account> accounts = new ArrayList<>();
		
		if (accountHistory.containsKey(transactionDate)) {
			Map<Id, Map<String, Object>> accountRecords = accountHistory.get(transactionDate);
			
			for (Id accountId : accountRecords.keySet()) {
				Map<String, Object> accountRecord = accountRecords.get(accountId);
				
				Account account = account(accountId, accountRecord);
				
				if (account != null) {
					accounts.add(account);
				}
			}
		}
		
		return accounts;
	}
	
}