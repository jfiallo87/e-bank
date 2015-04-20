angular.module("ebank.customer", [])
.controller("customerController", function($scope, $http) {
	$scope.customer = {};
	$scope.loanTypes = [{value: "car", label: "Car Loan"},
	                    {value: "boat", label: "Boat Loan"},
	                    {value: "mortgage", label: "Mortgage"}]
	$scope.loanApplication = {type: $scope.loanTypes[0],
							  data: {amount: 0.00}};
	
	$scope.loadCustomer = function() {
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
		openAccount("loan/" + $scope.loanApplication.type.value, $scope.loanApplication.data);
	}
	
	$scope.loadCustomer();
});