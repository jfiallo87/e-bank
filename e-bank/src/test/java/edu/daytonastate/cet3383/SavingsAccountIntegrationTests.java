package edu.daytonastate.cet3383;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EBankApplication.class)
@WebAppConfiguration
public class SavingsAccountIntegrationTests extends AbstractDebitAccountIntegrationTests {
	
	@Override
	@Test
	public void criticalPath() {
		super.criticalPath();
	}

	@Override
	protected void openDebitAccountFor(String customerId) {
		customerService.openSavingsAccount(customerId);
	}
	
}