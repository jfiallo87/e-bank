package edu.daytonastate.cet3383.ebank.web.form;

public class PaymentForm extends TransactionForm {
	
	private String fromAccount;

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	
}
