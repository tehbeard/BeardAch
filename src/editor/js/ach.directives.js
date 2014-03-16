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

    var html = $templateCache.get('achListTriggerTemplate');
    element.html($compile(html)(scope));
  }

  return {
    restrict: 'E',
    scope: {
        list:'='       
    },
    link: linker,
    controller: function($scope){
      $scope.ui = {
        addTrigger: function(){if($scope.list == null){$scope.list = [];};if($scope.ui.selected){$scope.list.push({_type:$scope.ui.selected});}},
        remItem: function(list,index){if(confirm("Really delete this item?")){list.splice(index,1);}}
      }
      $scope.getTriggers = function(){
          return angular.element("body").scope().opttrigger;
        }
  }
  };
}]);