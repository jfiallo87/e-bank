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
import edu.daytonastate.cet3383.ebank.web.view.AccountDetailView;
import edu.daytonastate.cet3383.ebank.web.view.CashWidthdrawalView;
import edu.daytonastate.cet3383.ebank.web.view.DepositView;
import edu.daytonastate.cet3383.ebank.web.view.PaymentView;
import edu.daytonastate.cet3383.ebank.web.view.TransferView;

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
	public AccountDetailView cashWithdrawal(Principal principal, @PathVariable(value=ACCOUNT_ID) String accountId, @RequestBody CashWidthdrawalView cashWidthdrawal) {
		String customerId = principal.getName();
		
		bankingService.cashWithdrawal(customerId, accountId, cashWidthdrawal.getAmount());
		
		return accountDetailView(accountId, customerId);
	}
	
	@RequestMapping(method=POST, value=DEPOSIT)
	public AccountDetailView deposit(Principal principal, @PathVariable(value=ACCOUNT_ID) String accountId, @RequestBody DepositView depositView) {
		String customerId = principal.getName();
		
		bankingService.deposit(customerId, accountId, depositView.getAmount());
		
		return accountDetailView(accountId, customerId);
	}
	
	@RequestMapping(method=POST, value=PAYMENT)
	public AccountDetailView payment(Principal principal, @PathVariable(value=ACCOUNT_ID) String forAccountId, @RequestBody PaymentView paymentView) {
		String customerId = principal.getName();
		
		bankingService.payment(customerId, forAccountId, paymentView.getFromAccount(), paymentView.getAmount());
		
		return accountDetailView(forAccountId, customerId);
	}
	
	@RequestMapping(method=POST, value=TRANSFER)
	public AccountDetailView transfer(Principal principal, @PathVariable(value=ACCOUNT_ID) String toAccountId, @RequestBody TransferView transferView) {
		String customerId = principal.getName();
		
		bankingService.transfer(customerId, toAccountId, transferView.getFromAccount(), transferView.getAmount());
		
		return accountDetailView(toAccountId, customerId);
	}

	private AccountDetailView accountDetailView(String accountId, String customerId) {
		Account account = bankingService.info(customerId, accountId);
		List<Transaction> transactions = bankingService.transactions(customerId, accountId);
		
		return new AccountDetailView(account, transactions);
	}
	
}