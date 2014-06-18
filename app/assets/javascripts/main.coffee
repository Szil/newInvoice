# -----------------------------------------------
# MAIN
# -----------------------------------------------
#
#
#
# -----------------------------------------------

# Just a log helper
'use strict'

log = (args...) ->
  console.log.apply console, args if console.log?

# AngularJS application

adminModule = angular.module 'adminModule', ['ngRoute', 'ngResource', 'userServices']

adminModule.config ($routeProvider) ->
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
			})


# Controllers

adminModule.controller 'userListCtrl', [
  '$scope', 'Users', ($scope, Users) ->
    $scope.users = Users.query();
]

# User service

userServices = angular.module 'userServices', [];

userServices.factory 'Users', ['$resource',
    ($resource) -> 
        return $resource 'user/userList', {}, {
            query: { method: 'GET', params: {}, isArray: true }
        }
    ]
