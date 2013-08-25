'use strict';

/* Controllers */

angular.module('controllers', ['messageServices','filters']).controller('MessageCtrl', function($scope, Messages) {
	$scope.newFilter={};
	$scope.filter={};
	$scope.pageable={"page":1,"sort":"createTime","createTime.dir":"desc"};
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
}).controller('UserCtrl', function($scope){
	var merge = function(x,y){
		var r=x;
		for (var key in y) {
			r[key] = y[key];
		}
		return r;
	};
	

	
	$scope.find=function(){
		 var frm = $(document.form);
		 var data = JSON.stringify(frm.serializeArray());
		alert(data);
		$scope.pageable.page=1;
		$scope.current=1;
		$scope.filter=$scope.newFilter;
		$scope.messages=Messages.save($scope.filter);
	};
});
 

angular.module('navCtrls',[]).controller('NavCtrl', function($scope,$location) {
	$scope.navs=[{url: "message", name: "消息"},{url: "user", name: "用户"},{url: "statistics", name: "统计"}];
	$scope.location=$location;
});