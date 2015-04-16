package edu.daytonastate.cet3383.ebank;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {
	
	private static final String TRANSACTION_TYPE = "type";
	private static final String TRANSACTION_BALANCE = "balance";
	private static final String TRANSACTION_AMOUNT = "amount";
	private static final String TRANSACTION_DATE = "date";
	
	private static Map<Id, List<Map<String, Object>>> transactions = new LinkedHashMap<>();
	
	private AccountRepository accountRepository;
	
	@Autowired
	public TransactionRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void save(Transaction transaction) {
		synchronized (transactions) {
			for (Account account : transaction.accounts()) {
				if (!transactions.containsKey(account.id())) {
					transactions.put(account.id(), new ArrayList<>());
				}
				
				Map<String, Object> row = new HashMap<>();
				row.put(TRANSACTION_DATE, transaction.date());
				row.put(TRANSACTION_TYPE, transaction.type());
				row.put(TRANSACTION_AMOUNT, transaction.amount());
				row.put(TRANSACTION_BALANCE, account.currentBalance());
				
				transactions.get(account.id()).add(row);
			}
		}
	}
	
	public List<Transaction> findByAccountId(Id accountId) {
		List<Transaction> transactionsByAccountId = new ArrayList<>();
		
		List<Map<String, Object>> recordsByAccountId = transactions.getOrDefault(accountId, new ArrayList<>());
		
		for (Map<String, Object> recordByAccountId : recordsByAccountId) {
			Date date = (Date) recordByAccountId.get(TRANSACTION_DATE);
			TransactionType type = (TransactionType) recordByAccountId.get(TRANSACTION_TYPE);
			Double amount = (Double) recordByAccountId.get(TRANSACTION_AMOUNT);
			Account account = accountRepository.findById(accountId);
			
			transactionsByAccountId.add(new ReadOnlyTransaction(date, type, amount, account));
		}
		
		return transactionsByAccountId;
	}
	
	public List<Transaction> findByAccountIdAndDate(Id accountId, Date transactionDate) {
		List<Transaction> transactionsForAccountIdAndDate = new ArrayList<>();
		
		List<Transaction> transactionsForAccountId = findByAccountId(accountId);
		
		DateFormat dateFormatter = DateFormat.getDateInstance();
		String formattedTransactionDate = dateFormatter.format(transactionDate);
		
		for (Transaction transactionForAccountId : transactionsForAccountId) {
			String formattedTransactionForAccountIdDate = dateFormatter.format(transactionForAccountId.date());
			
			if (formattedTransactionDate.equals(formattedTransactionForAccountIdDate)) {
				transactionsForAccountIdAndDate.add(transactionForAccountId);
			}
		}
		
		return transactionsForAccountIdAndDate;
	}
	
}