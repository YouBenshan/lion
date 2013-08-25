'use strict';

/* Controllers */

angular.module('messageCtrls', ['messageServices']).controller('MessageCtrl', function($scope, Messages) {
	$scope.filter={};
	$scope.pageable={"page":1,"sort":"createTime","createTime.dir":"desc"};
	
	var lastMessages=Messages.get($scope.pageable);
	$scope.lastMessages=lastMessages;
	$scope.messages=lastMessages;
	
	$scope.updateMessages=function(){
		$scope.messages=Messages.get($scope.pageable);
	};
  });
 

angular.module('navCtrls',[]).controller('NavCtrl', function($scope,$location) {
	$scope.navs=[{url: "message", name: "消息"},{url: "user", name: "用户"},{url: "statistics", name: "统计"}];
	$scope.location=$location;
});