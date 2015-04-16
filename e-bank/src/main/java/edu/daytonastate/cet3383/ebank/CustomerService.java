package edu.daytonastate.cet3383.ebank;

import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class CustomerService {
	
	private IdFactory idFactory;
	
	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	
	@Autowired
	public CustomerService(IdFactory idFactory,
			CustomerRepository customerRepository,
			AccountRepository accountRepository) {
		this.idFactory = idFactory;
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
	}

	public Customer info(Long customerId) {
		Id customer = idFactory.customer(customerId);
		
		return customerRepository.findById(customer);
	}
	
	public void openCheckingAccount(Long customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		Double balance = 0.00;
		
		Account account = new CheckingAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openSavingsAccount(Long customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		Double balance = 0.00;
		
		Account account = new SavingsAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openCreditCardAccount(Long customerId) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		CreditCardApplication creditCardApplication = new CreditCardApplication();
		Double balanceLimit = creditCardApplication.creditLimit();
		Double balance = 0.00;
		
		Account account = new CreditCardAccount(uniqueId, customer, balance, balanceLimit);
		
		accountRepository.save(account);
	}
	
	public void openCarLoanAccount(Long customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new CarLoanAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openBoatLoanAccount(Long customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new BoatLoanAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
	public void openMortgageAccount(Long customerId, Double loanAmount) {
		Id uniqueId = idFactory.uniqueId();
		Id customer = idFactory.customer(customerId);
		LoanApplication loanApplication = new LoanApplication(loanAmount);
		Double balance = loanApplication.balance();
		
		Account account = new MortgageAccount(uniqueId, customer, balance);
		
		accountRepository.save(account);
	}
	
}