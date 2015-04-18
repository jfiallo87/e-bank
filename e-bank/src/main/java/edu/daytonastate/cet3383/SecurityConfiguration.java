package edu.daytonastate.cet3383;

import static org.springframework.boot.autoconfigure.security.SecurityProperties.ACCESS_OVERRIDE_ORDER;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String DEFAULT_PASSWORD = "password";
	private static final String CUSTOMER_ROLE = "CUSTOMER";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
		.authorizeRequests().anyRequest().authenticated().and()
		.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		String juanFiallo = "juan.fiallo";
		String danWilliams = "dan.williams";
		String markMonk = "mark.monk";
		String bahmanMotlagh = "bahman.motlagh";
		
		auth.inMemoryAuthentication()
		.withUser(juanFiallo).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(danWilliams).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(markMonk).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE).and()
		.withUser(bahmanMotlagh).password(DEFAULT_PASSWORD).roles(CUSTOMER_ROLE);
	}
	
}
