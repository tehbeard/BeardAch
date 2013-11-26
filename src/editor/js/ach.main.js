angular.module('achMain',['achDirectives']).controller('achList',function($scope,$templateCache,$filter){
  console.log("Loading achievement list");
  $scope.achievements = [];

  $scope.ui = {};
  $scope.ui.deleteIndex = -1;
  $scope.ui.editIndex   = -1;
  $scope.ui.pageStart   = 1;
  $scope.ui.perPage   = 10;
  $scope.ui.search      = "";
  $scope.ui.triggerSelected = "";
  $scope.ui.rewardSelected = "";

  $scope.testFunc = function(){
    alert("TEST");
  }

  $scope.ui.addTrigger = function(){
    $scope.achievements[$scope.ui.editIndex].triggers.push({_type:$scope.ui.triggerSelected});
  }

  $scope.ui.addReward = function(){
    $scope.achievements[$scope.ui.editIndex].rewards.push({_type:$scope.ui.rewardSelected});
  }

  $scope.remItem = function(list,idx){
    if(confirm("Really delete this item?")){
      list.splice(idx,1);
    }
  }


  $scope.ui.pages = function(){
  	return Math.ceil($filter('achsearch')($scope.achievements,$scope.ui.search).length / $scope.ui.perPage);
  }

  $scope.ui.inc = function(){
    if($scope.achievements.length == 0){return;}
    $scope.ui.pageStart ++;

    max = $scope.ui.pages();
    if($scope.ui.pageStart >= max){
    	$scope.ui.pageStart = max;
    }
  }

  $scope.ui.dec = function(){
    if($scope.achievements.length == 0){return;}
    $scope.ui.pageStart --;
    if($scope.ui.pageStart < 1){
    	$scope.ui.pageStart = 1;
    }
  }

  $scope.newAch = function(){
  	$scope.achievements.unshift(JSON.parse($templateCache.get('newAchJSON')));
  	$scope.achievements[0].slug = "ach_" + $scope.achievements.length;
    $scope.ui.editIndex = 0;
  }

  /*
  Show the remove modal
  */
  $scope.remove = function(ach){
  	$scope.ui.deleteIndex = $scope.achievements.indexOf(ach);
  	$("#modalConfirmDelete").modal('show');
  }

  $scope.edit = function(ach){
    $scope.ui.editIndex = $scope.achievements.indexOf(ach);
  	$("#modalEdit").modal('show');
  }

  $scope.load = function(){
  	$("#modalLoad").modal('show');
  }

  $scope.save = function(){
  	$('#modalSave').modal('show');
  }
  $scope.saveAsFile = function(){
  	window.saveAs(new Blob([angular.toJson($scope.achievements,"  ")]),"ach.json");
  }

  /*
  Actually delete from the model.
  */
  $scope._actualDelete = function(){
  	if($scope.ui.deleteIndex != -1){
  	  $scope.achievements.splice($scope.ui.deleteIndex,1);
  	  $scope.ui.deleteIndex = -1;
  	  $("#modalConfirmDelete").modal('hide');
    }
  }

  $scope.loadFromFile = function(selector){
    fr = new FileReader();
    fr.onload = function(){
      $scope.achievements = JSON.parse(fr.result);
       $scope.$apply();
      $("#modalLoad").modal('hide');
    }
    fr.readAsText($(selector)[0].files[0]);
  }
})
.filter('startat',function(){
	return function(input,start){
		na = [];
		for(x = start;x<input.length;x++){
			na.push(input[x]);
		}
		return na;
	}
}).filter('achsearch',function(){
	return function(input,key){
		na = [];
		for(x = 0;x<input.length;x++){
		  if(input[x].slug.indexOf(key) != -1 || input[x].name.indexOf(key) != -1 ){
			na.push(input[x]);
		  }
		}
		return na;
	}
});