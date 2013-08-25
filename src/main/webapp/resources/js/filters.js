'use strict';

/* Filters */

angular.module('filters', []).
  filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
  }]).
  filter('pageRange', function() {
	return function(input, page) {
		var num=5;
		var start=0;
		if(page.total>num && current>num/2){
			start=current-num/2;
		}
		var end=page.total;
		if(page.total>num && current<page.total-num/2){
			end=current+num/2;
		}

		for (var i=start; i<end; i++){
			input.push(i);
		}
		return input;
	};
});;
