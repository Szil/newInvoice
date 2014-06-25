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
		.when('/', {
			templateUrl: '/dashboard/index',
			controller: 'userListCtrl'
			})
		.when('/register', {
			templateUrl: '/dashboard/registerForm',
			controller: 'createUserCtrl'
			})
		.when('/user/delete', {
			templateUrl: '/user/delete'
			controller: 'deleteUserCtrl'
			})
		.otherwise({
				redirectTo: '/dashboard'
			})


# Controllers

adminModule.controller 'userListCtrl', [
  '$scope', 'Users', 'deleteUser', ($scope, Users) ->
       $scope.users = Users.query()
       $scope.delUser = () ->
        destUser $scope.user
]

adminModule.controller 'createUserCtrl', 
  ($scope, $location, createUser) ->
    $scope.save = () ->
     createUser.save $scope.user
     $scope.users = Users.query()
     $location.path '/'


adminModule.controller 'deleteUserCtrl', [
  '$scope', 'deleteUser', ($scope, $location, deleteUser) ->
        $scope.deleteUser = () ->
            destUser $scope.user
            $location.path '/'
]

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
    	$resource '/user/delete/', {}, {
    		destUser: {method: 'POST', params: {}}
    	}]
