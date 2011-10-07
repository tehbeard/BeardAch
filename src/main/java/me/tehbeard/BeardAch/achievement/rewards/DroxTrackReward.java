package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DroxTrackReward extends Reward{

	String track = "";
	public static IReward getInstance(String config) {
		DroxTrackReward n = new DroxTrackReward();
		n.track = config;
		return n;
	}

	@Override
	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.promotePlayer(player.getName(), track);
			BeardAch.printDebugCon("Played Promoted");
		}
	}

}
