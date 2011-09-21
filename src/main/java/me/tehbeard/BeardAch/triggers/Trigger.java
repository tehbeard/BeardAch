package me.tehbeard.BeardAch.triggers;

import org.bukkit.entity.Player;

public abstract class Trigger implements ITrigger {
	
	/**
	 * Create a new instance of this trigger
	 * @param config config string to use
	 * @return
	 */
	public abstract ITrigger newInstance(String config);
	
	public abstract boolean checkAchievement(Player player);
}
