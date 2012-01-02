package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;


import me.tehbeard.utils.Cuboid;

import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
/**
 * Checks if a players is in a worldguard region
 * @author James
 *
 */
public class worldGuardRegionCheckTrigger extends Trigger {




	public static ITrigger getInstance(String config) {
		WorldGuardPlugin wgp  = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("worldguard");
		if(wgp!=null){


			CuboidCheckTrigger n =new CuboidCheckTrigger();
			String[] opt = config.split("\\:");
			if(opt.length==2){
				ProtectedRegion r = wgp.getRegionManager(Bukkit.getWorld(opt[0])).getRegion(opt[1]);
				n.c.setCuboid(
						new Vector(
								r.getMinimumPoint().getBlockX(),
								r.getMinimumPoint().getBlockY(),
								r.getMinimumPoint().getBlockZ()
								),
						new Vector(
								r.getMaximumPoint().getBlockX(),
								r.getMaximumPoint().getBlockY(),
								r.getMaximumPoint().getBlockZ()
								),
						opt[0]);


			}


			return n;
		}
		return null;
	}
	@Override
	public boolean checkAchievement(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
}