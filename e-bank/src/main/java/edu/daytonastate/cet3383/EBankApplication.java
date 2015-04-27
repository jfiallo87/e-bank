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
import edu.daytonastate.cet3383.ebank.factory.IdFactory;
import edu.daytonastate.cet3383.ebank.repository.CustomerRepository;

@SpringBootApplication
public class EBankApplication {

    public static void main(String... args) {
        SpringApplication.run(EBankApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, IdFactory idFactory) {
    	return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				System.out.println("Creating test data");
				
				CustomerName juanFiallo = new CustomerName("Fiallo", "Juan", 'M');
				CustomerName danWilliams = new CustomerName("Williams", "Dan");
				CustomerName markMonk = new CustomerName("Monk", "Mark");
				CustomerName bahmanMotlagh = new CustomerName("Motlagh", "Bahman");
				
				CustomerName jamesBond = new CustomerName("Bond", "James"); //Mock user for integration tests
				
				List<CustomerName> customerNames = Arrays.asList(juanFiallo, danWilliams, markMonk, bahmanMotlagh, jamesBond);
				
				for (CustomerName customerName : customerNames) {
					String userName = customerName.firstName().toLowerCase() + "." + customerName.lastName().toLowerCase();
					Id id = new Id(userName);
					Customer customer = new Customer(id, customerName);
					
					customerRepository.save(customer);
					
					System.out.println("Created Customer " + customerName + " with Id " + id);
				}
			}
		};
    }
    
}