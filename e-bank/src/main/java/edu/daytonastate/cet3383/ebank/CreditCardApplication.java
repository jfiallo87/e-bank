package edu.daytonastate.cet3383.ebank;

import org.apache.commons.lang3.Range;

public class CreditCardApplication extends CreditApplication {

	private static final int MINIMUM_CREDIT = 1000;
	private static final int MAXIMUM_CREDIT = 10000;
	
	@Override
	protected Range<Integer> creditLimitRange() {
		return Range.between(MINIMUM_CREDIT, MAXIMUM_CREDIT);
	}

}