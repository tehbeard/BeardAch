package me.tehbeard.BeardAch.achievement.triggers;

import me.tehbeard.BeardStat.containers.PlayerStatManager;

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
	Vector v1 = null;
	Vector v2 = null;

	
	public static ITrigger getInstance(String config) {
		CuboidCheckTrigger n =new CuboidCheckTrigger();
		
		String[] opt = config.split("\\:");
		if(opt.length==7){
			n.world = opt[0];
			n.v1 = new Vector(Integer.parseInt(opt[1]), Integer.parseInt(opt[2]), Integer.parseInt(opt[3]));
			n.v2 = new Vector(Integer.parseInt(opt[4]), Integer.parseInt(opt[5]), Integer.parseInt(opt[6]));
			
		}
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		//if(player.getWorld().getName().equals(world)){
			if(player.getLocation().toVector().isInAABB(
					Vector.getMinimum(v1, v2),
					Vector.getMaximum(v1, v2))){
				return true;
			}
		//}
		return false;
	}

}
