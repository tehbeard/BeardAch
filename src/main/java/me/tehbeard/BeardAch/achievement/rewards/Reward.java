package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.entity.Player;

public abstract class Reward implements IReward{

	/**
	 * Create a new instance of this reward
	 * @param config config string to use
	 * @return
	 */
	public static IReward newInstance(String config){
		return null;
	}
	
	public abstract void giveReward(Player player);
}