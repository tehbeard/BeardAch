package me.tehbeard.BeardAch;

import me.tehbeard.BeardStat.BeardStat;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BeardAch extends JavaPlugin {

	public static Configuration config;
	BeardStat stats = null;
	
	private static final String PERM_PREFIX = "stat";

	public static boolean hasPermission(Player player,String node){

		return (player.hasPermission(PERM_PREFIX + "." + node) || player.isOp());


	}
	public static void printCon(String line){
		System.out.println("[BeardAch] " + line);
	}

	public static void printDebugCon(String line){
		if(config!=null){
			if(config.getBoolean("general.debug", false)){
				System.out.println("[BeardAch][DEBUG] " + line);

			}
		}
	}
	
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	public void onEnable() {
		// TODO Auto-generated method stub
		stats = (BeardStat)getServer().getPluginManager().getPlugin("BeardStat");
		if(stats==null){
			
		}
	}

}
