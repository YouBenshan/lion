'use strict';

/* Services */
angular.module('messageServices', ['ngResource']).factory('Messages', function($resource){
	return $resource('/'+appName+'/api/receivedMessage');
}).factory('Message', function($resource){
	return $resource('/'+appName+'/api/receivedMessage/:id');
});