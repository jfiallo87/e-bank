package edu.daytonastate.cet3383.ebank.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.daytonastate.cet3383.ebank.CustomerService;

@RestController
@RequestMapping(value="/{customerId}")
public class CustomerRestController {
	
	CustomerService customerService;
	
	@Autowired
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(method=GET)
	public CustomerView get(@PathVariable(value="customerId") Long customerId) {
		return new CustomerView(customerService.info(customerId));
	}

}