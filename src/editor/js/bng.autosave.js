"use strict";
var app = angular.module('bngAutosave',[]);
app.factory("autosave",function(){
	return function(scope, model,key, delay,cb){
		var _modelCallbackId = -1;
		scope.$watch(model, function(value,_){
			clearTimeout(_modelCallbackId);
			_modelCallbackId = setTimeout(function(){
				window.localStorage.setItem(key, angular.toJson(value,"  "));
				!cb || cb();
			},delay);
		},true);
	};

});