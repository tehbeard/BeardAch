package com.tehbeard.beardach.achievement.rewards;

import com.tehbeard.beardach.datasource.configurable.IConfigurable;

import org.bukkit.entity.Player;

/**
 * Interface for rewards for a player
 * @author James
 *
 */
public interface IReward extends IConfigurable{
	public void giveReward(Player player);
	
}
