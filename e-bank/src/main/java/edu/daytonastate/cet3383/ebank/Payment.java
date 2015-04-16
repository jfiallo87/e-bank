package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.TransactionType.*;

public class Payment extends Transaction {

	public Payment(Double amount, CreditAccount forAccount, DebitAccount fromAccount) {
		super(PAYMENT, amount, forAccount);
		
		fromAccount.withdraw(amount);
		forAccount.submitPayment(amount);
	}

}