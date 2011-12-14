package me.tehbeard.BeardAch.achievement.triggers;


import java.util.HashSet;
import java.util.List;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;

import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
public class AchCheckTrigger extends Trigger {

	String ach;

	public static ITrigger getInstance(String config) {
		AchCheckTrigger n =new AchCheckTrigger();
			n.ach = config;
			
			
		
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has an acheivement
		List<Achievement> achs = AchievementManager.getAchievements(player.getName());
		if(achs !=null){
			for(Achievement a: achs){
				if(a.getName().equals(ach)){return true;}
			}
			
		}
		return false;
	}

}
