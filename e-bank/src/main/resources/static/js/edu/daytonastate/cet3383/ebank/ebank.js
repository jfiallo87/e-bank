angular.module("ebank", ["ebank.customer",
                         "ebank.account",
                         "ngRoute"])
.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl: "template/home.html",
		controller: "customerController"})
	.when("/account/:accountId", {
		templateUrl: "template/account.html",
		controller: "accountController"})
	.otherwise({
		redirectTo: "/"
	});
});