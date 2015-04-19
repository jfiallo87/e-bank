angular.module("ebank.customer", [])
.controller("customerController", function($scope, $http) {
	$scope.customer = {};
	$scope.loanApplication = {type: "car",
							  data: {amount: 0.00}};
	
	$scope.loadSummary = function() {
		$http.get("/api")
		.success(function(data) {
			$scope.customer = data;
		});
	};
	
	var openAccount = function(type, data) {
		$http.post("/api/account/open/" + type, data)
		.success(function(data) {
			$scope.customer = data;
		});
	}
	
	$scope.openCheckingAccount = function() {
		openAccount("checking");
	};
	
	$scope.openSavingsAccount = function() {
		openAccount("savings");
	};
	
	$scope.openCreditCardAccount = function() {
		openAccount("creditcard");
	};
	
	$scope.loanApplication = function() {
		openAccount("loan/" + $scope.loanApplication.type, $scope.loanApplication.data);
	}
	
	$scope.loadSummary();
});