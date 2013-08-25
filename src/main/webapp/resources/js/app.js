'use strict';


angular.module('main', ['navCtrls','controllers']).config(function($routeProvider){
	$routeProvider.
	when('/message', {templateUrl: '/'+appName+'/template/message', controller: 'MessageCtrl'}).
	when('/user', {templateUrl: '/'+appName+'/template/user', controller: 'UserCtrl'}).
    when('/statistics', {templateUrl: '/'+appName+'/template/statistics', controller: 'AccountViewCtrl'});
});
