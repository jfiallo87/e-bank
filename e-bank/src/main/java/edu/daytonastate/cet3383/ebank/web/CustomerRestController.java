package edu.daytonastate.cet3383.ebank.web;

import static edu.daytonastate.cet3383.ebank.web.UrlDictionary.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Customer;
import edu.daytonastate.cet3383.ebank.service.CustomerService;
import edu.daytonastate.cet3383.ebank.web.view.CustomerView;
import edu.daytonastate.cet3383.ebank.web.view.LoanApplicationView;

@RestController
@RequestMapping(value=API)
public class CustomerRestController {
	
	CustomerService customerService;
	
	@Autowired
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(method=GET)
	public CustomerView get(Principal customer) {
		String customerId = customer.getName();
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}

	@RequestMapping(method=POST, value=ACCOUNT_OPEN_CHECKING)
	public CustomerView openCheckingAccount(Principal customer) {
		String customerId = customer.getName();
		
		customerService.openCheckingAccount(customerId);
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	@RequestMapping(method=POST, value=ACCOUNT_OPEN_SAVINGS)
	public CustomerView openSavingsAccount(Principal customer) {
		String customerId = customer.getName();
		
		customerService.openSavingsAccount(customerId);
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	@RequestMapping(method=POST, value=ACCOUNT_OPEN_CREDIT_CARD)
	public CustomerView openCreditCardAccount(Principal customer) {
		String customerId = customer.getName();
		
		customerService.openCreditCardAccount(customerId);
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	@RequestMapping(method=POST, value=ACCOUNT_OPEN_LOAN_CAR)
	public CustomerView openCarLoanAccount(Principal customer, @RequestBody LoanApplicationView loanApplication) {
		String customerId = customer.getName();
		
		customerService.openCarLoanAccount(customerId, loanApplication.getAmount());
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	@RequestMapping(method=POST, value=ACCOUNT_OPEN_LOAN_BOAT)
	public CustomerView openBoatLoanAccount(Principal customer, @RequestBody LoanApplicationView loanApplication) {
		String customerId = customer.getName();
		
		customerService.openBoatLoanAccount(customerId, loanApplication.getAmount());
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	@RequestMapping(method=POST, value=ACCOUNT_OPEN_LOAN_MORTGAGE)
	public CustomerView openMortgageAccount(Principal customer, @RequestBody LoanApplicationView loanApplication) {
		String customerId = customer.getName();
		
		customerService.openMortgageAccount(customerId, loanApplication.getAmount());
		
		CustomerView customerView = customerView(customerId);
		
		return customerView;
	}
	
	private CustomerView customerView(String customerId) {
		Customer customerInfo = customerService.info(customerId);
		List<Account> accounts = customerService.accounts(customerId);
		
		CustomerView customerView = new CustomerView(customerInfo, accounts);
		
		return customerView;
	}
	
}