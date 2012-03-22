package me.tehbeard.BeardAch.achievement.triggers;


import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Always false
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
