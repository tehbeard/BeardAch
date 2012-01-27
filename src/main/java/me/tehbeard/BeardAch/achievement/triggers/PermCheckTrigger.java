package me.tehbeard.BeardAch.achievement.triggers;


import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
@Configurable(tag="perm")
public class PermCheckTrigger implements ITrigger {

	String perm;

	public void configure(String config) {
		perm = config;
	}

	
	public boolean checkAchievement(Player player) {
		//if player has stat
		return player.hasPermission(perm);
	}

}
