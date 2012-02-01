package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

@Configurable(tag="promote")
public class DroxTrackReward implements IReward{

	String track = "";
	public void configure(String config) {
		
		track = config;
	
	}

	
	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.promotePlayer(player.getName(), track);
			BeardAch.printDebugCon("Played Promoted");
		}
	}

}
