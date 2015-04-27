package edu.daytonastate.cet3383.ebank.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.daytonastate.cet3383.ebank.Id;

public class InMemoryDb {
	
	public static final Map<Date, Map<Id, Map<String, Object>>> accountHistory = new HashMap<>();
	public static final Map<Id, Map<String, Object>> accounts = new HashMap<>();
	public static final Map<Id, Map<String, Object>> customers = new HashMap<>();
	public static final Map<Id, List<Map<String, Object>>> transactions = new LinkedHashMap<>();
	
}
