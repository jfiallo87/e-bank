package edu.daytonastate.cet3383.ebank;

import static edu.daytonastate.cet3383.ebank.TransactionType.*;

public class CashWithdrawal extends Transaction {

	public CashWithdrawal(Double amount, DebitAccount account) {
		super(CASH_WITHDRAWAL, amount, account);
		
		account.cash(amount);
	}
	
	public CashWithdrawal(Double amount, CreditCardAccount account) {
		super(CASH_WITHDRAWAL, amount, account);
		
		account.cash(amount);
	}

}
