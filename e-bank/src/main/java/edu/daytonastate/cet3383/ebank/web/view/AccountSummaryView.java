package edu.daytonastate.cet3383.ebank.web.view;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.AccountType;

public class AccountSummaryView {
	
	private String id;
	private AccountType type;
	private Double balance;
	private Double limit;
	
	public AccountSummaryView() {}

	public AccountSummaryView(Account account) {
		if (account != null) {
			id = account.id().toString();
			type = account.type();
			balance = account.currentBalance();
			limit = account.balanceLimit();
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}
	
}