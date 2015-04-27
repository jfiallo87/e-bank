package edu.daytonastate.cet3383.ebank.web.form;

public abstract class TransactionForm {
	
	protected Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
