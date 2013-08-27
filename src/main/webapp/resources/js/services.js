'use strict';

/* Services */
angular.module('messageServices', ['ngResource']).factory('Messages', function($resource){
	return $resource('/'+appName+'/api/receivedMessage');
}).factory('Message', function($resource){
	return $resource('/'+appName+'/api/receivedMessage/:id');
}).factory("Subscribe",function($resource){
	return $resource('/'+appName+'/admin/eventReceivedMessage/countSubscribe');
}).factory("TopActive",function($resource){
	return $resource('/'+appName+'/admin/receivedMessage/topActive');
});