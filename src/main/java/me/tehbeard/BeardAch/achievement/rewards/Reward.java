package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.entity.Player;

public abstract class Reward {

	/**
	 * Create a new instance of this reward
	 * @param config config string to use
	 * @return
	 */
	public abstract IReward newInstance(String config);
	
	public abstract void giveReward(Player player);
}