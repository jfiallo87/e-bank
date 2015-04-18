package edu.daytonastate.cet3383.ebank;

import java.util.Date;

public class ReadOnlyTransaction extends Transaction {

	public ReadOnlyTransaction(Date date, TransactionType type, Double amount, Account... accounts) {
		super(date, type, amount, accounts);
	}

}