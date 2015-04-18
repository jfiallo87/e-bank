package edu.daytonastate.cet3383.ebank.web.view;

import java.util.ArrayList;
import java.util.List;

import edu.daytonastate.cet3383.ebank.Account;
import edu.daytonastate.cet3383.ebank.Customer;

public class CustomerView {
	
	private String lastName;
	private String firstName;
	private Character middleNameInitial;
	
	private List<AccountSummaryView> accounts = new ArrayList<>();

	public CustomerView() {}
	
	public CustomerView(Customer customer, List<Account> customerAccounts) {
		if (customer != null) {
			lastName = customer.name().lastName();
			firstName = customer.name().firstName();
			middleNameInitial = customer.name().middleNameInitial();
		}
		
		if (customerAccounts != null) {
			for (Account account : customerAccounts) {
				AccountSummaryView accountSummaryView = new AccountSummaryView(account);
				
				accounts.add(accountSummaryView);
			}
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Character getMiddleNameInitial() {
		return middleNameInitial;
	}

	public void setMiddleNameInitial(Character middleNameInitial) {
		this.middleNameInitial = middleNameInitial;
	}

	public List<AccountSummaryView> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountSummaryView> accounts) {
		this.accounts = accounts;
	}
	
}