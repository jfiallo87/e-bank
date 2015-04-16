package edu.daytonastate.cet3383.ebank;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Transaction {
	
	private final Date date;
	private final TransactionType type;
	private final Double amount;
	private final Set<Account> accounts;
	
	public Transaction(TransactionType type, Double amount, Account... accounts) {
		this(new Date(), type, amount, accounts);
	}
	
	protected Transaction(Date date, TransactionType type, Double amount, Account... accounts) {
		this.date = date;
		this.type = type;
		this.amount = amount;
		
		this.accounts = new LinkedHashSet<>();
		
		for (Account account : accounts) {
			this.accounts.add(account);
		}
	}
	
	public Date date() {
		return date;
	}
	
	public TransactionType type() {
		return type;
	}
	
	public Double amount() {
		return amount;
	}
	
	public Set<Account> accounts() {
		return Collections.unmodifiableSet(accounts);
	}
	
}