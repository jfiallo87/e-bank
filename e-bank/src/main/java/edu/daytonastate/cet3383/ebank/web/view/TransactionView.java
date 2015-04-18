package edu.daytonastate.cet3383.ebank.web.view;

public abstract class TransactionView {
	
	protected Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
