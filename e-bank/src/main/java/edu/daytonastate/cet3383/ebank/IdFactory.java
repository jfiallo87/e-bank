package edu.daytonastate.cet3383.ebank;

import java.util.Date;

@Factory
public class IdFactory {
	
	public Id customer(Long customerId) {
		return new Id(customerId);
	}
	
	public Id account(Long accountId) {
		return new Id(accountId);
	}
	
	public synchronized Id uniqueId() {
		return new Id(new Date().getTime());
	}
	
}