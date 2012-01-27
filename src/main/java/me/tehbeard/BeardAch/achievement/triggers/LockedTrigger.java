package me.tehbeard.BeardAch.achievement.triggers;


import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
@Configurable(tag="locked")
public class LockedTrigger implements ITrigger {


	public void configure(String config) {
	}


	public boolean checkAchievement(Player player) {

		return false;
	}

}
