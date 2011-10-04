package me.tehbeard.BeardAch;

import java.io.File;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.rewards.CommandReward;
import me.tehbeard.BeardAch.achievement.triggers.CuboidCheckTrigger;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.achievement.triggers.statCheckTrigger;
import me.tehbeard.BeardAch.listener.BeardAchPlayerListener;
import me.tehbeard.BeardStat.BeardStat;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BeardAch extends JavaPlugin {

	public static Configuration config;
	

	private static final String PERM_PREFIX = "ach";

	public static boolean hasPermission(Player player,String node){

		return (player.hasPermission(PERM_PREFIX + "." + node) || player.isOp());


	}
	public static void printCon(String line){
		System.out.println("[BeardAch] " + line);
	}

	public static void printDebugCon(String line){
		//if(config!=null){
			//if(config.getBoolean("general.debug", false)){
				System.out.println("[BeardAch][DEBUG] " + line);

		//	}
		//}
	}

	public void onDisable() {
		// TODO Auto-generated method stub

	}
	
	public static boolean checkBeardStat(){
		BeardStat stats = (BeardStat) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
		return (stats!=null && stats.isEnabled());
		
	}

	public void onEnable() {
		// TODO Auto-generated method stub
		BeardStat stats = (BeardStat)getServer().getPluginManager().getPlugin("BeardStat");
		if(!checkBeardStat()){
			printCon("BeardStat NOT FOUND, DISABLING PLUGIN!");
			onDisable();
			return;
		}

		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, new BeardAchPlayerListener(), Priority.Highest, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, new BeardAchPlayerListener(), Priority.Highest, this);
		

		
		//TEST ACHIEVEMENTS, DELETE ONCE DATASOURCE'S COMPLETE
		

		Achievement ach  = new Achievement("BleepBloop", "got a bleep bloop");
		ach.addTrigger(statCheckTrigger.newInstance("stats:playedfor:2000"));
		ach.addTrigger(CuboidCheckTrigger.newInstance("world:0:0:0:128:128:128"));
		ach.addReward(CommandReward.newInstance("me is proud of <PLAYER>"));
		AchievementManager.addAchievement(ach);
		
		ach  = new Achievement("test", "test descrip");
		ach.addTrigger(statCheckTrigger.newInstance("stats:notthere:2000"));
		ach.addTrigger(CuboidCheckTrigger.newInstance("world:0:0:0:128:128:128"));
		ach.addReward(CommandReward.newInstance("cp promote <PLAYER> veteran"));
		AchievementManager.addAchievement(ach);
		
		
		//Load config
		printCon("Starting BeardStat");
		if(!(new File(getDataFolder(),"BeardStat.yml")).exists()){
			initalConfig();
		}
		config = new Configuration(new File(getDataFolder(),"BeardStat.yml"));
		config.load();
		
		
		
		
		}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

			AchievementManager.checkPlayers();
			AchievementManager.unloadOfflinePlayers();
		
		return true;
	}
}
