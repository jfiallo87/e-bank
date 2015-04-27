package edu.daytonastate.cet3383.ebank.factory;

import java.util.Date;

import edu.daytonastate.cet3383.ebank.Id;

@Factory
public class IdFactory {
	
	public Id customer(String customerId) {
		return new Id(customerId);
	}
	
	public Id account(String accountId) {
		return new Id(accountId);
	}
	
	public synchronized Id uniqueId() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		Date now = new Date();
		String uniqueId = String.valueOf(now.getTime());
		return new Id(uniqueId);
	}
	
}