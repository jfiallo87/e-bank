package edu.daytonastate.cet3383.ebank.web;

public final class UrlDictionary {
	
	public static final String API = "/api";
	public static final String ACCOUNT_ID = "accountId";
	public static final String ACCOUNT = "/account/";
	public static final String ACCOUNT_OPEN = ACCOUNT + "open/";
	public static final String ACCOUNT_OPEN_CHECKING = ACCOUNT_OPEN + "checking";
	public static final String ACCOUNT_OPEN_SAVINGS = ACCOUNT_OPEN + "savings";
	public static final String ACCOUNT_OPEN_CREDIT_CARD = ACCOUNT_OPEN + "creditcard";
	public static final String ACCOUNT_OPEN_LOAN = ACCOUNT_OPEN + "loan/";
	public static final String ACCOUNT_OPEN_LOAN_CAR = ACCOUNT_OPEN_LOAN + "car";
	public static final String ACCOUNT_OPEN_LOAN_BOAT = ACCOUNT_OPEN_LOAN + "boat";
	public static final String ACCOUNT_OPEN_LOAN_MORTGAGE = ACCOUNT_OPEN_LOAN + "mortgage";
	public static final String ACCOUNT_ACCOUNT_ID = API + ACCOUNT + "{" + ACCOUNT_ID + "}";
	public static final String WITHDRAWAL = "/withdrawal";
	public static final String DEPOSIT = "/deposit";
	public static final String PAYMENT = "/payment";
	public static final String TRANSFER = "/transfer";
	public static final String POLICY = "/policy";
	public static final String POLICY_ACTIVATE = POLICY + "/activate";
	public static final String POLICY_DEACTIVATE = POLICY + "/deactivate";
	
	private UrlDictionary() {}
	
}
