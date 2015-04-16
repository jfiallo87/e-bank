package edu.daytonastate.cet3383.ebank;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Range;

public abstract class CreditApplication {
	
	private Double creditLimit;
	
	protected abstract Range<Integer> creditLimitRange();
	
	public CreditApplication() {
		Range<Integer> creditLimitRange = creditLimitRange();
		int minimumCredit = creditLimitRange.getMinimum();
		int maximumCredit = creditLimitRange.getMaximum();
		
		creditLimit = (double) RandomUtils.nextInt(minimumCredit, maximumCredit);
	}
	
	public Double creditLimit() {
		return creditLimit;
	}
}
