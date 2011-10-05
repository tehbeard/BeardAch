package me.tehbeard.BeardAch.achievement.triggers;


import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
public class PermCheckTrigger extends Trigger {

	String perm;

	public static ITrigger getInstance(String config) {
		PermCheckTrigger n =new PermCheckTrigger();
			n.perm = config;
			
			
		
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		return player.hasPermission(perm);
	}

}
