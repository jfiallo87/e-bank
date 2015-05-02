package edu.daytonastate.cet3383;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String DEFAULT_PASSWORD = "password";
	private static final String CUSTOMER_ROLE = "CUSTOMER";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.csrf()
		.disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		String juanFiallo = "juan.fiallo";
		String danWilliams = "dan.williams";
		String mattMonk = "matt.monk";
		
		//Test user with card number and pin for the professor
		String bahmanMotlaghCardNumber = "1234567812345678";
		String bahmanMotlaghPin = "0987";
		
		auth.inMemoryAuthentication()
		.withUser(juanFiallo).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(danWilliams).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(mattMonk).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(bahmanMotlaghCardNumber).password(bahmanMotlaghPin).roles(CUSTOMER_ROLE);
	}
	
}
