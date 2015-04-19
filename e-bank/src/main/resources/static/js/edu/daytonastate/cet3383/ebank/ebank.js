angular.module("ebank", ["ebank.customer",
                         "ngRoute"])
.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : "template/home.html",
		controller : "customerController"})
	.otherwise("/");
});