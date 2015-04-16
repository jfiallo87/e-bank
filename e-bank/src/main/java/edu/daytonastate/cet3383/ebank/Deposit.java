package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.TransactionType.*;

public class Deposit extends Transaction {

	public Deposit(Double amount, DebitAccount account) {
		super(DEPOSIT, amount, account);
		
		account.deposit(amount);
	}

}
