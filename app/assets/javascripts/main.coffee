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
		.when('/dashboard/register', {
			templateUrl: '/dashboard/registerForm',
			controller: 'createUserCtrl'
			})
		.when('/user/delete/:userId', {
			templateUrl: '/user/delete/:userId'
			controller: 'deleteUserCtrl'
			})
		.otherwise({
				redirectTo: '/dashboard'
			})


# Controllers

adminModule.controller 'userListCtrl', [
  '$scope', 'Users', ($scope, Users) ->
    $scope.users = Users.query()
]

adminModule.controller 'createUserCtrl', 
  ($scope, $location, createUser) ->
    $scope.save = () ->
     createUser.save $scope.user
     $location.path '/dashboard'

adminModule.controller 'deleteUserCtrl',
  ($scope, $location, deleteUser) ->
  	$scope.delete = () ->
  	  destroyUser.destroy 
  	  $location.path '/dashboard'
	

# User service

userServices = angular.module 'userServices', []

userServices.factory 'Users', ['$resource',
    ($resource) -> 
        $resource '/user/userList', {}, {
            query: { method: 'GET', params: {}, isArray: true }
        }]

userServices.factory 'createUser', ['$resource',
    ($resource) ->
    	$resource '/user/createUser', {}, {
    		save: {method: 'POST', params: {}}
    	}]

userServices.factory 'deleteUser', ['$resource',
	($resource) ->
    	$resource '/user/delete/:userId', {}, {
    		destroy: {method: 'POST', params: {userId}}
    	}]
