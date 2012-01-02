package me.tehbeard.BeardAch.achievement.triggers;


import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
public class LockedTrigger extends Trigger {


	public static ITrigger getInstance(String config) {
		LockedTrigger n =new LockedTrigger();
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {

		return false;
	}

}
