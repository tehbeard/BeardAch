package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;

import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.utils.Cuboid;


import org.bukkit.entity.Player;

/**
 * Checks if a players is in a cuboid
 * @author James
 *
 */
@Configurable(tag="cuboid")
public class CuboidCheckTrigger implements ITrigger {

	String world = null;

	Cuboid c = new Cuboid();

	public void configure(String config) {
			c.setCuboid(config);
	}

	public boolean checkAchievement(Player player) {
		//if player has stat
		//if(player.getWorld().getName().equals(world)){

		return c.isInside(player.getLocation());
	}
	
	public ArrayList<String> getCache(){
		return c.getChunks();
	}

}
