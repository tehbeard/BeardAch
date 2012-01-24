package me.tehbeard.BeardAch.achievement.triggers;


import java.util.List;

import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

import org.bukkit.entity.Player;

/**
 * Checks if a player does not have an achievement
 * @author James
 *
 */
public class NoAchCheckTrigger extends Trigger {

	String ach;

	public static ITrigger getInstance(String config) {
		NoAchCheckTrigger n =new NoAchCheckTrigger();
			n.ach = config;
			
			
		
		return n;
	}

	@Override
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
