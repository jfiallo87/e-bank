package edu.daytonastate.cet3383.ebank.repository;

import static edu.daytonastate.cet3383.ebank.repository.InMemoryDb.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.daytonastate.cet3383.ebank.Id;
import edu.daytonastate.cet3383.ebank.Policy;
import edu.daytonastate.cet3383.ebank.PolicyType;

@Repository
public class PolicyRepository {
	
	private static final String POLICY_ACTIVE = "active";
	
	public void save(Id customerId, Policy policy) {
		Map<String, Object> policyRecord = new HashMap<>();
		policyRecord.put(POLICY_ACTIVE, policy.isActive());
		Map<PolicyType, Map<String, Object>> customerPolicyRecord = policies.get(customerId);
		
		if (customerPolicyRecord == null) {
			customerPolicyRecord = new HashMap<>();
		}
		
		customerPolicyRecord.put(policy.type(), policyRecord);
		policies.put(customerId, customerPolicyRecord );
	}
	
	public List<Policy> findByCustomerId(Id customerId) {
		List<Policy> customerPolicies = new ArrayList<>();
		Map<PolicyType, Map<String, Object>> customerPolicyRecord = policies.get(customerId);
		
		if (customerPolicyRecord != null) {
			for (PolicyType type : customerPolicyRecord.keySet()) {
				Policy policy = findByCustomerIdAndType(customerId, type);
				
				if (policy != null) {
					customerPolicies.add(policy);
				}
			}
		}
		
		return customerPolicies;
	}
	
	public Policy findByCustomerIdAndType(Id customerId, PolicyType type) {
		Policy policy = null;
		Map<PolicyType, Map<String, Object>> customerPolicyRecord = policies.get(customerId);
		
		if (customerPolicyRecord != null) {
			Map<String, Object> policyRecord = customerPolicyRecord.get(type);
			
			if (policyRecord != null) {
				boolean active = (boolean) policyRecord.get(POLICY_ACTIVE);
				policy = new Policy(type, active);
			}
		}
		
		return policy;
	}
	
}