package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;
import java.util.Vector;


import org.bukkit.Bukkit;

import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
/**
 * Checks if a players is in a worldguard region
 * @author James
 *
 */
public class worldGuardRegionCheckTrigger extends CuboidCheckTrigger {

	private ProtectedRegion r = null;

	String world = "";
	public static ITrigger getInstance(String config) {
		WorldGuardPlugin wgp  = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("worldguard");
		if(wgp!=null){


			worldGuardRegionCheckTrigger n =new worldGuardRegionCheckTrigger();
			String[] opt = config.split("\\:");
			if(opt.length==2){
				n.r = wgp.getRegionManager(Bukkit.getWorld(opt[0])).getRegion(opt[1]);
				n.world = opt[0];
			}


			return n;
		}
		return null;
	}

	public worldGuardRegionCheckTrigger(){

	}
	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		if(player.getWorld().getName().equals(world)){
			return r.contains(player.getLocation().getBlockX(),
					player.getLocation().getBlockY(),
					player.getLocation().getBlockZ()
					);
		}
		return false;
	}

	public ArrayList<String> getCache(){
		return c.getChunks();
	}

}
