"use strict";
var achHelp = angular.module('achHelp',[]);
achHelp.provider('achHelpDB',function(){
	var _data = {};

	this.setData = function(data){
		_data = data;
	};


	this.$get = function(){
		return {
			rewardHelp: {},
			triggerHelp: {},
			selectedItem: false
		};  
	};

	
});