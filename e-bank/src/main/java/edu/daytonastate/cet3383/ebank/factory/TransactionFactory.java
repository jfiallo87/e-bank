package edu.daytonastate.cet3383.ebank.factory;

import static edu.daytonastate.cet3383.ebank.ValidationError.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.CashWithdrawal;
import edu.daytonastate.cet3383.ebank.CreditAccount;
import edu.daytonastate.cet3383.ebank.CreditCardAccount;
import edu.daytonastate.cet3383.ebank.DebitAccount;
import edu.daytonastate.cet3383.ebank.Deposit;
import edu.daytonastate.cet3383.ebank.Payment;
import edu.daytonastate.cet3383.ebank.Policy;
import edu.daytonastate.cet3383.ebank.PolicyType;
import edu.daytonastate.cet3383.ebank.Transaction;
import edu.daytonastate.cet3383.ebank.Transfer;
import edu.daytonastate.cet3383.ebank.repository.PolicyRepository;
import edu.daytonastate.cet3383.ebank.repository.TransactionRepository;

@Factory
public class TransactionFactory {
	
	private static final int TRANSACTIONAL_DAILY_LIMIT = 10;
	
	private TransactionRepository transactionRepository;
	private PolicyRepository policyRepository;
	
	@Autowired
	public TransactionFactory(TransactionRepository transactionRepository, PolicyRepository policyRepository) {
		this.transactionRepository = transactionRepository;
		this.policyRepository = policyRepository;
	}

	public Transaction cashWithdrawal(Double amount, DebitAccount account) {
		checkTransactionalDailyLimit(account);
		
		return new CashWithdrawal(amount, account);
	}

	public Transaction cashWithdrawal(Double amount, CreditCardAccount account) {
		checkTransactionalDailyLimit(account);
		
		return new CashWithdrawal(amount, account);
	}
	
	public Transaction deposit(Double amount, DebitAccount account) {
		checkTransactionalDailyLimit(account);
		
		return new Deposit(amount, account);
	}
	
	public Transaction payment(Double amount, CreditAccount forAccount, DebitAccount fromAccount) {
		checkTransactionalDailyLimit(forAccount);
		checkTransactionalDailyLimit(fromAccount);
		
		return new Payment(amount, forAccount, fromAccount);
	}
	
	public Transaction transfer(Double amount, DebitAccount toAccount, DebitAccount fromAccount) {
		checkTransactionalDailyLimit(toAccount);
		checkTransactionalDailyLimit(fromAccount);
		
		return new Transfer(amount, toAccount, fromAccount);
	}
	
	private void checkTransactionalDailyLimit(Account account) {
		Policy transactionDailyLimitPolicy = policyRepository.findByCustomerIdAndType(account.customerId(), PolicyType.TRANSACTION_DAILY_LIMIT);
		
		if (transactionDailyLimitPolicy.isActive() && transactionRepository.findByAccountIdAndDate(account.id(), new Date()).size() >= TRANSACTIONAL_DAILY_LIMIT) {
			throw new UnsupportedOperationException(TRANSACTIONAL_DAILY_LIMIT_EXCEEDED.message());
		}
	}
		
}