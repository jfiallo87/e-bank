package edu.daytonastate.cet3383.ebank;

public abstract class Account {
	
	private final Id id;
	private final Id customerId;
	private final AccountType type;
	private Double balance;
	
	protected Double balanceLimit = Double.MAX_VALUE;

	public Account(Id id, Id customerId, AccountType type, Double balance) {
		this.id = id;
		this.customerId = customerId;
		this.type = type;
		this.balance = balance;
	};
	
	public Id id() {
		return id;
	}
	
	public Id customerId() {
		return customerId;
	}
	
	public AccountType type() {
		return type;
	}
	
	public Double currentBalance() {
		return balance;
	}
	
	public Double balanceLimit() {
		return balanceLimit;
	}
	
	public boolean accessibleBy(Id accessibleBy) {
		return (customerId.toString().equals(accessibleBy.toString()));
	}
	
	protected Double changeBalance(Double amount) {
		synchronized (balance) {
			balance += amount;
		}
		
		return balance;
	}

}