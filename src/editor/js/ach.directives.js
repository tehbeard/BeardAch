angular.module('achDirectives',[]).directive('achReward', ['$compile','$templateCache', function($compile,$templateCache) {
  var linker = function(scope, element, attrs) {

    var html = $templateCache.get('reward.' + scope.entry._type);
    console.log("ach-reward");
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
}]);