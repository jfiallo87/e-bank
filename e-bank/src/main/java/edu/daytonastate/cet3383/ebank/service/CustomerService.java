package edu.daytonastate.cet3383.ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.BoatLoanAccount;
import edu.daytonastate.cet3383.ebank.CarLoanAccount;
import edu.daytonastate.cet3383.ebank.CheckingAccount;
import edu.daytonastate.cet3383.ebank.CreditCardAccount;
import edu.daytonastate.cet3383.ebank.CreditCardApplication;
import edu.daytonastate.cet3383.ebank.Customer;
import edu.daytonastate.cet3383.ebank.Id;
import edu.daytonastate.cet3383.ebank.LoanApplication;
import edu.daytonastate.cet3383.ebank.MortgageAccount;
import edu.daytonastate.cet3383.ebank.Policy;
import edu.daytonastate.cet3383.ebank.PolicyType;
import edu.daytonastate.cet3383.ebank.SavingsAccount;
import edu.daytonastate.cet3383.ebank.factory.IdFactory;
import edu.daytonastate.cet3383.ebank.repository.AccountRepository;
import edu.daytonastate.cet3383.ebank.repository.CustomerRepository;
import edu.daytonastate.cet3383.ebank.repository.PolicyRepository;

@Service
public class CustomerService {
	
	private IdFactory idFactory;
	
	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private PolicyRepository policyRepository;
	
	@Autowired
	public CustomerService(IdFactory idFactory,
			CustomerRepository customerRepository,
			AccountRepository accountRepository,
			PolicyRepository policyRepository) {
		this.idFactory = idFactory;
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.policyRepository = policyRepository;
	}

	public Customer info(String customerId) {
		Id customer = idFactory.customer(customerId);
		
		return customerRepository.findById(customer);
	}
	
	public List<Account> accounts(String customerId) {
		Id customer = idFactory.customer(customerId);
		
		return accountRepository.findByCustomerId(customer);
	}
	
	public void openCheckingAccount(String customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		Double balance = 0.00;
		
		Account account = new CheckingAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openSavingsAccount(String customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		Double balance = 0.00;
		
		Account account = new SavingsAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openCreditCardAccount(String customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		CreditCardApplication creditCardApplication = new CreditCardApplication();
		Double balanceLimit = creditCardApplication.creditLimit();
		Double balance = 0.00;
		
		Account account = new CreditCardAccount(uniqueId, customer, balance, balanceLimit);
		
		accountRepository.save(account);
	}
	
	public void openCarLoanAccount(String customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new CarLoanAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openBoatLoanAccount(String customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new BoatLoanAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openMortgageAccount(String customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new MortgageAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public List<Policy> policies(String customerId) {
		Id customer = idFactory.customer(customerId);
		List<Policy> policies = policyRepository.findByCustomerId(customer);
		return policies;
	}
	
	public void activatePolicy(String customerId, PolicyType policyType) {
		updatePolicy(customerId, policyType, true);
	}
	
	public void deactivatePolicy(String customerId, PolicyType policyType) {
		updatePolicy(customerId, policyType, false);
	}

	private void updatePolicy(String customerId, PolicyType policyType, boolean active) {
		Id customer = idFactory.customer(customerId);
		Policy policy = new Policy(policyType, active);
		policyRepository.save(customer, policy);
	}
	
}