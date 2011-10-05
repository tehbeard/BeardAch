package me.tehbeard.BeardAch.achievement.triggers;

import org.bukkit.entity.Player;

public abstract class Trigger implements ITrigger {
	
	/**
	 * Create a new instance of this trigger
	 * @param config config string to use
	 * @return
	 */
	public static ITrigger getInstance(String config){
		
		return null;
	}
	
	public abstract boolean checkAchievement(Player player);
}
