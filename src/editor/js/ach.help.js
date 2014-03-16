achHelp = angular.module('achHelp',[]);
achHelp.factory('achHelpDB',function(){
	return {
		rewardHelp: [],
		triggerHelp: [],
		selectedItem: false
	};
});