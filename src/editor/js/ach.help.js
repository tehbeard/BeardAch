achHelp = angular.module('achHelp',[]);
achHelp.factory('achHelpDB',function(){
	h = {
		rewardHelp: [],
		triggerHelp: [],
		selectedTrigger: false,
		selectedReward: false,
		initial: function(){
			console.log(h.rewardHelp);
			for(x in h.rewardHelp){
				console.log("Loaded help " + x);
 				h.selectedReward = h.rewardHelp[x];
				return;
			}
		}
	};
	return h;
});