package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;

import me.tehbeard.BeardStat.containers.PlayerStatManager;
import me.tehbeard.utils.Cuboid;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
/**
 * Checks if a players is in a cuboid
 * @author James
 *
 */
public class CuboidCheckTrigger extends Trigger {

	String world = null;

	Cuboid c = new Cuboid();

	public static ITrigger getInstance(String config) {
		CuboidCheckTrigger n =new CuboidCheckTrigger();

			n.c.setCuboid(config);
		
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		//if(player.getWorld().getName().equals(world)){

		return c.isInside(player.getLocation());
	}
	
	public ArrayList<String> getCache(){
		return c.getChunks();
	}

}
