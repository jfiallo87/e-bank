package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.TransactionType.*;

public class Transfer extends Transaction {

	public Transfer(Double amount, DebitAccount toAccount, DebitAccount fromAccount) {
		super(TRANSFER, amount, fromAccount, toAccount);
		
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}

}