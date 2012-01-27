package me.tehbeard.BeardAch.achievement.triggers;


import java.util.List;

import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Checks if a player does not have an achievement
 * @author James
 *
 */
@Configurable(tag="noach")
public class NoAchCheckTrigger implements ITrigger {

	String ach;

	public void configure(String config) {
		ach = config;
	}

	public boolean checkAchievement(Player player) {
		//if player has an acheivement, return false
		List<AchievementPlayerLink> achs = AchievementManager.getAchievements(player.getName());
		boolean result = true;
		if(achs !=null){
			
			for(AchievementPlayerLink a: achs){
				if(a.getSlug().equals(ach)){result=false;}
			}
			
		}
		return result;
	}

}
