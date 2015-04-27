package edu.daytonastate.cet3383.ebank.web.view;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Transaction;
import edu.daytonastate.cet3383.ebank.TransactionType;

public class TransactionSummaryView {
	
	private Date date;
	private TransactionType type;
	protected Double amount;
	
	private Set<AccountSummaryView> accounts = new HashSet<>();
	
	public TransactionSummaryView() {}

	public TransactionSummaryView(Transaction transaction) {
		if (transaction != null) {
			date = transaction.date();
			type = transaction.type();
			amount = transaction.amount();
			
			for (Account transactionAccount : transaction.accounts()) {
				accounts.add(new AccountSummaryView(transactionAccount));
			}
		}
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Set<AccountSummaryView> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountSummaryView> accounts) {
		this.accounts = accounts;
	}
	
}