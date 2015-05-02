package edu.daytonastate.cet3383;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.daytonastate.cet3383.ebank.Customer;
import edu.daytonastate.cet3383.ebank.CustomerName;
import edu.daytonastate.cet3383.ebank.Id;
import edu.daytonastate.cet3383.ebank.Policy;
import edu.daytonastate.cet3383.ebank.PolicyType;
import edu.daytonastate.cet3383.ebank.factory.IdFactory;
import edu.daytonastate.cet3383.ebank.repository.CustomerRepository;
import edu.daytonastate.cet3383.ebank.repository.PolicyRepository;

@SpringBootApplication
public class EBankApplication {

    public static void main(String... args) {
        SpringApplication.run(EBankApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, PolicyRepository policyRepository, IdFactory idFactory) {
    	return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				System.out.println("Creating test data");
				CustomerName juanFiallo = new CustomerName("Fiallo", "Juan", 'M');
				CustomerName danWilliams = new CustomerName("Williams", "Dan");
				CustomerName markMonk = new CustomerName("Monk", "Mark");
				CustomerName jamesBond = new CustomerName("Bond", "James"); //Mock user for integration tests
				List<CustomerName> customerNames = Arrays.asList(juanFiallo, danWilliams, markMonk, jamesBond);
				
				for (CustomerName customerName : customerNames) {
					String userName = customerName.firstName().toLowerCase() + "." + customerName.lastName().toLowerCase();
					Id id = new Id(userName);
					initCustomer(id, customerName, customerRepository, policyRepository);
				}
				
				//Test user with card number for the professor
				CustomerName bahmanMotlagh = new CustomerName("Motlagh", "Bahman");
				String cardNumber = "1234567812345678";
				Id id = new Id(cardNumber);
				initCustomer(id, bahmanMotlagh, customerRepository, policyRepository);
			}

			private void initCustomer(Id id, CustomerName customerName,
					CustomerRepository customerRepository,
					PolicyRepository policyRepository) {
				Customer customer = new Customer(id, customerName);
				customerRepository.save(customer);
				boolean active = true;
				Policy transactionDailyLimitPolicy = new Policy(PolicyType.TRANSACTION_DAILY_LIMIT, active);
				policyRepository.save(id, transactionDailyLimitPolicy);
				Policy accountCreationPolicy = new Policy(PolicyType.ACCOUNT_CREATION, active);
				policyRepository.save(id, accountCreationPolicy);
				System.out.println("Created Customer " + customerName + " with Id " + id);
			}
		};
    }
    
}