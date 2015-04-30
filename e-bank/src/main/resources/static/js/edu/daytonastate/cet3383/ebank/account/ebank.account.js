angular.module("ebank.account", [])
.controller("accountController", function($scope, $http, $routeParams) {
	$scope.success = false;
	$scope.error = false;
	$scope.successMessage = "";
	$scope.errorMessage = "";
	$scope.account = {};
	$scope.transactionTypes = []
	$scope.debitAccounts = [];
	$scope.accountId = $routeParams.accountId;
	$scope.transaction = {type: null,
						  data: {amount: 0.00,
							  	 fromAccount: null}};
	
	var loadTransactionTypes = function() {
		var withdrawalAllowedTypes = ["CHECKING", "SAVINGS", "CREDIT_CARD"];
		var depositAllowedTypes = ["CHECKING", "SAVINGS"];
		var paymentAllowedTypes = ["CREDIT_CARD", "CAR_LOAN", "BOAT_LOAN", "MORTGAGE"];
		var transferAllowedTypes = ["CHECKING", "SAVINGS"];
		
		if (withdrawalAllowedTypes.indexOf($scope.account.type) != -1) {
			$scope.transactionTypes.push({value: "withdrawal", label: "Cash Withdrawal"});
		}
		
		if (depositAllowedTypes.indexOf($scope.account.type) != -1) {
			$scope.transactionTypes.push({value: "deposit", label: "Deposit"});
		}
		
		if (paymentAllowedTypes.indexOf($scope.account.type) != -1 && $scope.debitAccounts.length > 0) {
			$scope.transactionTypes.push({value: "payment", label: "Payment"});
		}
		
		if (transferAllowedTypes.indexOf($scope.account.type) != -1 && $scope.debitAccounts.length > 0) {
			$scope.transactionTypes.push({value: "transfer", label: "Transfer"});
		}
	}
	
	var loadDebitAccounts = function() {
		$http.get("/api")
		.success(function(data) {
			var customer = data;
			var accounts = customer.accounts;
			
			angular.forEach(accounts, function(account) {
				if ((account.type == "CHECKING" || account.type == "SAVINGS") && ($scope.account.id != account.id)) {
					this.push(account);
				}
			}, $scope.debitAccounts)
			
			loadTransactionTypes();
		});
	}
	
	$scope.loadAccount = function() {
		$http.get("/api/account/" + $scope.accountId)
		.success(function(data) {
			$scope.account = data;
			loadDebitAccounts();
		});
	};
	
	$scope.submitTransaction = function() {
		$scope.success = false;
		$scope.error = false;
		$scope.successMessage = null;
		$scope.errorMessage = null;
		$http.post("/api/account/" + $scope.accountId + "/" + $scope.transaction.type.value, $scope.transaction.data)
		.success(function(data) {
			$scope.account = data;
			$scope.success = true;
			$scope.successMessage = "Success! :)";
		})
		.error(function(data, status, headers, config) {
			$scope.error = true;
			$scope.errorMessage = "Oops! ";
			
			if (data.message != "No message available") {
				$scope.errorMessage = $scope.errorMessage + data.message;
			}
			
			$scope.errorMessage = $scope.errorMessage + " :(";
		});
	};
	
	$scope.formatAccount = function(account) {
		return account.type + ": " + account.id + " (" + account.balance + ")";
	}
	
	$scope.currentAccount = function(transaction) {
		var currentAccount = null;
		
		angular.forEach(transaction.accounts, function(account) {
			if ($scope.account.id == account.id) {
				currentAccount = account;
			}
		})
		
		return currentAccount;
	}
	
	$scope.loadAccount();
});