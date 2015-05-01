package edu.daytonastate.cet3383.ebank;

public class Policy {
	
	private final PolicyType policy;
	private boolean active;
	
	public Policy(PolicyType policy, boolean active) {
		this.policy = policy;
		this.active = active;
	}
	
	public PolicyType type() {
		return policy;
	}

	public boolean isActive() {
		return active;
	}
	
	public void activate() {
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
	
}