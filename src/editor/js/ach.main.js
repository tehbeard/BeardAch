angular.module('achMain',['achDirectives']).controller('achList',function($scope){
  console.log("Loading achievement list");
  $scope.load = function(){
    alert("BORK BORK BORK");
  }
});