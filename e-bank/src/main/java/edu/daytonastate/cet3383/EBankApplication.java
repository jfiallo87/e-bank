package edu.daytonastate.cet3383;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.daytonastate.cet3383.ebank.Customer;
import edu.daytonastate.cet3383.ebank.CustomerName;
import edu.daytonastate.cet3383.ebank.CustomerRepository;
import edu.daytonastate.cet3383.ebank.Id;
import edu.daytonastate.cet3383.ebank.IdFactory;

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
				
				List<CustomerName> customerNames = Arrays.asList(juanFiallo);
				
				for (CustomerName customerName : customerNames) {
					Id id = idFactory.uniqueId();
					Customer customer = new Customer(id, customerName);
					
					customerRepository.save(customer);
					
					System.out.println("Created Customer " + customerName + " with Id " + id);
				}
			}
		};
    }
    
}