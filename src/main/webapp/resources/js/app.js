'use strict';


angular.module('main', ['navCtrls','messageCtrls']).config(function($routeProvider){
	$routeProvider.
	when('/message', {templateUrl: '/'+appName+'/template/message', controller: 'MessageCtrl'}).
	when('/user', {templateUrl: '/'+appName+'/template/user', controller: 'AccountEditCtrl'}).
    when('/statistics', {templateUrl: '/'+appName+'/template/statistics', controller: 'AccountViewCtrl'});
});
