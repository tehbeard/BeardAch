package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.entity.Player;

public class DroxSubGroupReward implements IReward{

	String subgroup = "";
	public void configure(String config) {
		subgroup = config;
	}


	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.addPlayerSubgroup(player.getName(), subgroup);
			BeardAch.printDebugCon("Played added to subgroup");
		}
	}

}
