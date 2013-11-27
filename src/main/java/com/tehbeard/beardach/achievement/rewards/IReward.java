package com.tehbeard.beardach.achievement.rewards;

import org.bukkit.entity.Player;

import com.tehbeard.beardach.datasource.configurable.IConfigurable;

/**
 * Interface for rewards for a player
 * 
 * @author James
 * 
 */
public interface IReward extends IConfigurable {
    public void giveReward(Player player);

}
