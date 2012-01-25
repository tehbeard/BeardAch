package me.tehbeard.BeardAch;

import java.io.File;
import java.io.IOException;

import me.tehbeard.BeardAch.achievement.*;
import me.tehbeard.BeardAch.commands.*;
import me.tehbeard.BeardAch.dataSource.*;
import me.tehbeard.BeardAch.listener.BeardAchPlayerListener;
import me.tehbeard.BeardStat.BeardStat;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.file.YamlConfiguration;


import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import de.hydrox.bukkit.DroxPerms.DroxPerms;
import de.hydrox.bukkit.DroxPerms.DroxPermsAPI;

public class BeardAch extends JavaPlugin {

	public static BeardAch self;

	public static DroxPermsAPI droxAPI = null;
	private static final String PERM_PREFIX = "ach";

	public static boolean hasPermission(Permissible player,String node){

		return (player.hasPermission(PERM_PREFIX + "." + node) || player.isOp());


	}
	public static void printCon(String line){
		System.out.println("[BeardAch] " + line);
	}

	public static void printDebugCon(String line){
		//System.out.println("[BeardAch][DEBUG] " + line);
	}

	public void onDisable() {

		AchievementManager.database.flush();

	}

	private static boolean checkBeardStat(){
		BeardStat stats = (BeardStat) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
		return (stats!=null && stats.isEnabled());

	}

	public void onEnable() {
		self = this;

		//Load config
		printCon("Starting BeardAch");
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		updateConfig();
		reloadConfig();
		
		// TODO Auto-generated method stub
		//BeardStat stats = (BeardStat)getServer().getPluginManager().getPlugin("BeardStat");
		if(!checkBeardStat()){
			printCon("BeardStat NOT FOUND, DISABLING PLUGIN!");
			onDisable();
			return;
		}


		//check DroxPerms

		DroxPerms droxPerms = ((DroxPerms) this.getServer().getPluginManager().getPlugin("DroxPerms"));
		if (droxPerms != null) {
			droxAPI = droxPerms.getAPI();
		}

		//setup events
		Listener pl = new BeardAchPlayerListener();
		getServer().getPluginManager().registerEvents(pl, this);


		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("mysql")){
			AchievementManager.database = new SqlDataSource();
		}
		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("null")){

			AchievementManager.database = new NullDataSource();	
		}
		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("file")){

			AchievementManager.database = new YamlDataSource(this);	
		}

		if(AchievementManager.database == null){
			printCon("NO SUITABLE DATABASE SELECTED!!");

			onDisable();
			return;
		}
		AchievementManager.loadAchievements();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			public void run() {
				AchievementManager.checkPlayers();
			}

		}, 600L,600L);


		//commands

		getCommand("ach-reload").setExecutor(new AchReloadCommand());
		getCommand("ach").setExecutor(new AchCommand());
		getCommand("ach-fancy").setExecutor(new AchFancyCommand());
		printCon("Loaded Version:" + getDescription().getVersion());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		sender.sendMessage("COMMAND NOT IMPLEMENTED");
		return true;
	}




	private void updateConfig(){
		File f = new File(getDataFolder(),"BeardAch.yml");
		
		if(f.exists()){
			try {
				YamlConfiguration.loadConfiguration(f).save(new File(getDataFolder(),"config.yml"));
				f.renameTo(new File(getDataFolder(),"BeardAch.yml.old"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
