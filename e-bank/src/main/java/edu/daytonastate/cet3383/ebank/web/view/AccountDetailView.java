package edu.daytonastate.cet3383.ebank.web.view;

import java.util.ArrayList;
import java.util.List;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Transaction;

public class AccountDetailView extends AccountSummaryView {
	
	private List<TransactionSummaryView> transactions = new ArrayList<>();
	
	public AccountDetailView() {}

	public AccountDetailView(Account account, List<Transaction> accountTransactions) {
		super(account);
		
		if (accountTransactions != null) {
			for (Transaction transaction : accountTransactions) {
				TransactionSummaryView transactionView = new TransactionSummaryView(transaction);
				
				transactions.add(transactionView);
			}
		}
	}

	public List<TransactionSummaryView> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionSummaryView> transactions) {
		this.transactions = transactions;
	}

}
