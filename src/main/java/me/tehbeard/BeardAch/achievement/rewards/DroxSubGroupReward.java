package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DroxSubGroupReward extends Reward{

	String subgroup = "";
	public static IReward getInstance(String config) {
		DroxSubGroupReward n = new DroxSubGroupReward();
		n.subgroup = config;
		return n;
	}

	@Override
	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.addPlayerSubgroup(player.getName(), subgroup);
			BeardAch.printDebugCon("Played added to subgroup");
		}
	}

}
