package edu.daytonastate.cet3383.ebank.web;

import static edu.daytonastate.cet3383.ebank.web.UrlDictionary.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Transaction;
import edu.daytonastate.cet3383.ebank.service.BankingService;
import edu.daytonastate.cet3383.ebank.web.form.CashWidthdrawalForm;
import edu.daytonastate.cet3383.ebank.web.form.DepositForm;
import edu.daytonastate.cet3383.ebank.web.form.PaymentForm;
import edu.daytonastate.cet3383.ebank.web.form.TransferForm;
import edu.daytonastate.cet3383.ebank.web.view.AccountDetailView;

@RestController
@RequestMapping(value=ACCOUNT_ACCOUNT_ID)
public class BankingRestController {
	
	private BankingService bankingService;
	
	@Autowired
	public BankingRestController(BankingService bankingService) {
		this.bankingService = bankingService;
	}

	@RequestMapping(method=GET)
	public AccountDetailView get(Principal principal, @PathVariable(value=ACCOUNT_ID) String accountId) {
		String customerId = principal.getName();
		
		return accountDetailView(accountId, customerId);
	}
	
	@RequestMapping(method=POST, value=WITHDRAWAL)
	public AccountDetailView cashWithdrawal(Principal principal, @PathVariable(value=ACCOUNT_ID) String accountId, @RequestBody CashWidthdrawalForm cashWidthdrawalForm) {
		String customerId = principal.getName();
		
		bankingService.cashWithdrawal(customerId, accountId, cashWidthdrawalForm.getAmount());
		
		return accountDetailView(accountId, customerId);
	}
	
	@RequestMapping(method=POST, value=DEPOSIT)
	public AccountDetailView deposit(Principal principal, @PathVariable(value=ACCOUNT_ID) String accountId, @RequestBody DepositForm depositForm) {
		String customerId = principal.getName();
		
		bankingService.deposit(customerId, accountId, depositForm.getAmount());
		
		return accountDetailView(accountId, customerId);
	}
	
	@RequestMapping(method=POST, value=PAYMENT)
	public AccountDetailView payment(Principal principal, @PathVariable(value=ACCOUNT_ID) String forAccountId, @RequestBody PaymentForm paymentForm) {
		String customerId = principal.getName();
		
		bankingService.payment(customerId, forAccountId, paymentForm.getFromAccount(), paymentForm.getAmount());
		
		return accountDetailView(forAccountId, customerId);
	}
	
	@RequestMapping(method=POST, value=TRANSFER)
	public AccountDetailView transfer(Principal principal, @PathVariable(value=ACCOUNT_ID) String toAccountId, @RequestBody TransferForm transferForm) {
		String customerId = principal.getName();
		
		bankingService.transfer(customerId, toAccountId, transferForm.getFromAccount(), transferForm.getAmount());
		
		return accountDetailView(toAccountId, customerId);
	}

	private AccountDetailView accountDetailView(String accountId, String customerId) {
		Account account = bankingService.info(customerId, accountId);
		List<Transaction> transactions = bankingService.transactions(customerId, accountId);
		
		return new AccountDetailView(account, transactions);
	}
	
}