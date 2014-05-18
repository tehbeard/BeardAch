"use strict";
var _app = angular.module('achDirectives',[]);
/**
* Defines the achReward  directive, that dynamically compiles based on the type of reward
*/
_app.directive('achReward', ['$compile','$templateCache', function($compile,$templateCache) {
  var linker = function(scope, element, attrs) {

    var html = $templateCache.get('reward.' + scope.entry._type);
    element.html(html);
    element.replaceWith($compile(element.html())(scope));

  }

  return {
    restrict: 'E',
    scope: {
        entry:'='
    },
    link: linker
  };
}]);
/**
* Defines the achTrigger directive, that dynamically compiles based on the type of trigger
*/
_app.directive('achTrigger', ['$compile','$templateCache', function($compile,$templateCache) {
  var linker = function(scope, element, attrs) {

    var html = $templateCache.get('trigger.' + scope.entry._type);
    element.html(html);
    element.replaceWith($compile(element.html())(scope));

  }

  return {
    restrict: 'E',
    scope: {
        entry:'='
    },
    link: linker
  };
}]);
/**
* Defines a list of Triggers, used by Meta triggers.
*/
_app.directive('achTriggerList', ['$compile', function($compile) {
  return {
    templateUrl: 'achListTriggerTemplate',
    restrict: 'E',
    scope: {
        list:'='       
    },
    controller: function($scope){
      $scope.ui = {
        addTrigger: function(){if($scope.list == null){$scope.list = [];};if($scope.ui.selected){$scope.list.push({_type:$scope.ui.selected});}},
        remItem: function(list,index){if(confirm("Really delete this item?")){list.splice(index,1);}}
      }
      $scope.getTriggers = function(){
          return angular.element("body").scope().ui.opt.triggers;
        }
  }
  };
}]);