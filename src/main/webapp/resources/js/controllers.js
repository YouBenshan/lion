'use strict';

/* Controllers */

angular.module('messageCtrls', ['messageServices']).controller('MessageCtrl', function($scope, Messages) {
	$scope.lastMessages=Messages.get({"sort":"createTime","createTime.dir":"desc"});
	  
  });
 



angular.module('navCtrls',[]).controller('NavCtrl', function($scope,$location) {
	$scope.navs=[{url: "message", name: "消息"},{url: "user", name: "用户"},{url: "statistics", name: "统计"}];
	$scope.location=$location;
});