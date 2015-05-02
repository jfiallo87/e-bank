package edu.daytonastate.cet3383.ebank.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserRestController {
	
	@RequestMapping(method=GET)
	public Principal get(Principal user) {
		return user;
	}
	
}