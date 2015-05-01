package edu.daytonastate.cet3383.ebank.web.view;

import edu.daytonastate.cet3383.ebank.Policy;
import edu.daytonastate.cet3383.ebank.PolicyType;

public class PolicyView {
	
	private PolicyType policyType;
	private String description;
	private boolean active;
	
	public PolicyView() {}
	
	public PolicyView(Policy policy) {
		policyType = policy.type();
		description = policy.type().description();
		active = policy.isActive();
	}

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String decription) {
		this.description = decription;
	}
	
}
