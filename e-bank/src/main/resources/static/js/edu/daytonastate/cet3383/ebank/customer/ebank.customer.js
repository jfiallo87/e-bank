angular.module("ebank.customer", [])
.controller("customerController", function($scope, $http) {
	$scope.success = false;
	$scope.error = false;
	$scope.successMessage = "";
	$scope.errorMessage = "";
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
		$scope.success = false;
		$scope.error = false;
		$scope.successMessage = null;
		$scope.errorMessage = null;
		$http.post("/api/account/open/" + type, data)
		.success(function(data) {
			$scope.customer = data;
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