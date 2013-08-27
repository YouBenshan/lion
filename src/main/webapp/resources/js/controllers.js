'use strict';

/* Controllers */

angular.module('controllers', ['messageServices','filters']).controller('MessageCtrl', function($scope, Messages) {
	$scope.newFilter={};
	$scope.filter={};
	$scope.pageable={"page":1,"sort":"id","id.dir":"desc"};
	$scope.current=1;
	$scope.modal={};
	
	var lastMessages=Messages.get($scope.pageable);
	$scope.lastMessages=lastMessages;
	$scope.messages=lastMessages;

	$scope.updateMessages=function(i){
		if(i>0 && i<=$scope.messages.page.totalPages && i!=$scope.current){
			$scope.pageable.page=i;
			$scope.current=i;
			$scope.messages=Messages.get($scope.pageable);
		}
	};
	
	$scope.getDate=function(second){
		return new Date(1000*second).toLocaleString();
	};
}).controller('UserCtrl', function($scope,Subscribe,TopActive){
	$scope.subscribeToday=Subscribe.get();
	$scope.topActive=TopActive.get();
});
 

angular.module('navCtrls',[]).controller('NavCtrl', function($scope,$location) {
	$scope.navs=[{url: "message", name: "消息"},{url: "user", name: "用户"},{url: "statistics", name: "统计"}];
	$scope.location=$location;
});