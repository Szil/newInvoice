'use strict';

//  AngularJS application

var adminModule = angular.module('adminModule', ['ngRoute', 'ngResource', 'userServices']);

adminModule.config(function($routeProvider) {
	$routeProvider
		.when('/dashboard', {
			templateUrl: 'dashboard/index',
			controller: 'userListCtrl'
			})
		.when('/register', {
			templateUrl: '/register'
			})
		.otherwise({
				redirectTo: '/dashboard'
				});
});

//Controllers
adminModule.controller('userListCtrl', [
  '$scope', 'Users', function($scope, Users) {
    $scope.users = Users.query();
  }
]);

// Services

var userServices = angular.module('userServices', []);

userServices.factory('Users', ['$resource',
    function($resource) {
        return $resource('user/userList', {}, {
            query: { method: 'GET', params: {}, isArray: true }
        });
    }]);
