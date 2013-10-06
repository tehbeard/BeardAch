angular.module('achDirectives',[]).directive('achReward', ['$compile','$templateCache', function($compile,$templateCache) {
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
}])
.directive('achTrigger', ['$compile','$templateCache', function($compile,$templateCache) {
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
}])
.directive('achTriggerList', ['$compile','$templateCache', function($compile,$templateCache) {
  var linker = function(scope, element, attrs) {

    var html = $templateCache.get('partial.ele.trigger');
    element.html(html);
    element.replaceWith($compile(element.html())(scope));

  }

  return {
    restrict: 'E',
    scope: {
        list:'=',
        
    },
    link: linker,
    controller: function($scope){
      selected = -1;
      $scope.ui = {
        addTrigger: function(){$scope.list.push({_type:$scope.ui.selected});},
        remItem: function(list,index){if(confirm("Really delete this item?")){list.splice(idx,1);}},
      }
  }
    }
  };
}]);