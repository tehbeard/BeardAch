package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.IConfigurable;

import org.bukkit.entity.Player;

/**
 * Interface for rewards for a player
 * @author James
 *
 */
public interface IReward extends IConfigurable{
	public void giveReward(Player player);
	
}
