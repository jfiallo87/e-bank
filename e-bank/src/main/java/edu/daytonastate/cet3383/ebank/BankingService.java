package edu.daytonastate.cet3383.ebank;

import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class BankingService {
	
	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	
	private IdFactory idFactory;
	private TransactionFactory transactionFactory;
	
	@Autowired
	public BankingService(AccountRepository accountRepository,
			TransactionRepository transactionRepository, IdFactory idFactory,
			TransactionFactory transactionFactory) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.idFactory = idFactory;
		this.transactionFactory = transactionFactory;
	}

	public void cashWithdrawal(Long customerId, Long accountId, Double amount) {
		Account account = findAccountById(accountId);
		
		if (validAccountForCustomer(customerId, account)) {
			Transaction cashWithdrawal = null;
			
			if (account instanceof DebitAccount) {
				DebitAccount debitAccount = (DebitAccount) account;
				
				cashWithdrawal = transactionFactory.cashWithdrawal(amount, debitAccount);
			}
			
			if (account instanceof CreditCardAccount) {
				CreditCardAccount creditCardAccount = (CreditCardAccount) account;
				
				cashWithdrawal = transactionFactory.cashWithdrawal(amount, creditCardAccount);
			}
			
			saveTransaction(cashWithdrawal);
		}
	}

	public void deposit(Long customerId, Long accountId, Double amount) {
		Account account = findAccountById(accountId);
		
		if (validAccountForCustomer(customerId, account) && account instanceof DebitAccount) {
			DebitAccount debitAccount = (DebitAccount) account;
			Transaction deposit = transactionFactory.deposit(amount, debitAccount);
			
			saveTransaction(deposit);
		}
	}
	
	public void payment(Long customerId, Long forAccountId, Long fromAccountId, Double amount) {
		Account forAccount = findAccountById(forAccountId);
		Account fromAccount = findAccountById(fromAccountId);
		
		if (validAccountsForCustomer(customerId, forAccount, fromAccount) && forAccount instanceof CreditAccount && fromAccount instanceof DebitAccount) {
			CreditAccount forCreditAccount = (CreditAccount) forAccount;
			DebitAccount fromDebitAccount = (DebitAccount) fromAccount;
			Transaction payment = transactionFactory.payment(amount, forCreditAccount, fromDebitAccount);
			
			saveTransaction(payment);
		}
	}
	
	public void transfer(Long customerId, Long toAccountId, Long fromAccountId, Double amount) {
		Account toAccount = findAccountById(toAccountId);
		Account fromAccount = findAccountById(fromAccountId);
		
		if (validAccountsForCustomer(customerId, toAccount, fromAccount) && toAccount instanceof DebitAccount && fromAccount instanceof DebitAccount) {
			DebitAccount toDebitAccount = (DebitAccount) toAccount;
			DebitAccount fromDebitAccount = (DebitAccount) fromAccount;
			Transaction transfer = transactionFactory.transfer(amount, toDebitAccount, fromDebitAccount);
			
			saveTransaction(transfer);
		}
	}

	private Account findAccountById(Long accountId) {
		return accountRepository.findById(idFactory.account(accountId));
	}
	
	private boolean validAccountsForCustomer(Long customerId, Account... accounts) {
		boolean validAccountsForCustomer = true;
		
		if (accounts != null) {
			for (Account account : accounts) {
				if (!validAccountForCustomer(customerId, account)) {
					validAccountsForCustomer = false;
					break;
				}
			}
		}
		
		return validAccountsForCustomer;
	}
	
	private boolean validAccountForCustomer(Long customerId, Account account) {
		return (account != null && account.accessibleBy(idFactory.customer(customerId)));
	}
	
	private void saveTransaction(Transaction transaction) {
		if (transaction != null) {
			transactionRepository.save(transaction);
		}
	}
	
}