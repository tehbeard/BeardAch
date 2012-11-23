package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

@Configurable(tag="promote")
@Usage(arguments = {@Argument(name="track",desc="The track to promote the player along")}, packageName = "base",dependencies="DroxPerms")
public class DroxTrackReward implements IReward{

	String track = "";
	public void configure(Achievement Ach,String config) {
		
		track = config;
	
	}

	
	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.promotePlayer(player.getName(), track);
			BeardAch.printDebugCon("Played Promoted");
		}
	}

}
