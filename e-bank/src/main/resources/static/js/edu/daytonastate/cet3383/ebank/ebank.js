angular.module("ebank", ["ebank.customer",
                         "ebank.account",
                         "ngRoute"])
.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl: "templates/home.html",
		controller: "customerController"})
	.when("/account/:accountId", {
		templateUrl: "templates/account.html",
		controller: "accountController"})
	.otherwise({
		redirectTo: "/"
	});
});