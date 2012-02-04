package me.tehbeard.BeardAch;

import java.io.File;
import java.io.IOException;

import me.tehbeard.BeardAch.achievement.*;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.*;
import me.tehbeard.BeardAch.achievement.rewards.*;
import me.tehbeard.BeardAch.commands.*;
import me.tehbeard.BeardAch.dataSource.*;
import me.tehbeard.BeardAch.listener.BeardAchPlayerListener;
import me.tehbeard.BeardStat.BeardStat;
import me.tehbeard.BeardStat.containers.PlayerStatManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.YamlConfiguration;


import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import de.hydrox.bukkit.DroxPerms.DroxPerms;
import de.hydrox.bukkit.DroxPerms.DroxPermsAPI;

public class BeardAch extends JavaPlugin {

	public static BeardAch self;
	private PlayerStatManager stats = null;
	private AchievementManager achievementManager;

	public PlayerStatManager getStats(){
		return stats;

	}
	public static DroxPermsAPI droxAPI = null;
	private static final String PERM_PREFIX = "ach";

	public static boolean hasPermission(Permissible player,String node){

		return (player.hasPermission(PERM_PREFIX + "." + node) || player.isOp());


	}
	public static void printCon(String line){
		System.out.println("[BeardAch] " + line);
	}

	public static void printDebugCon(String line){
		if(self.getConfig().getBoolean("general.debug")){
			System.out.println("[BeardAch][DEBUG] " + line);
		}
	}

	public void onDisable() {

		achievementManager.database.flush();

	}

	private void EnableBeardStat(){
		BeardStat bs = (BeardStat) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
		if(bs!=null && bs.isEnabled()){
			stats = bs.getStatManager();
		}

	}

	public void onEnable() {
		self = this;
		achievementManager = new AchievementManager();
		//Load config
		printCon("Starting BeardAch");
		if(!getConfig().getKeys(false).contains("achievements")){
			getConfig().options().copyDefaults(true);
		}
		saveConfig();
		reloadConfig();
		updateConfig();
		reloadConfig();

		EnableBeardStat();


		//check DroxPerms
		DroxPerms droxPerms = ((DroxPerms) this.getServer().getPluginManager().getPlugin("DroxPerms"));
		if (droxPerms != null) {
			droxAPI = droxPerms.getAPI();
		}

		//setup events
		Listener pl = new BeardAchPlayerListener();
		getServer().getPluginManager().registerEvents(pl, this);


		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("mysql")){
			achievementManager.database = new SqlDataSource();
		}
		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("null")){

			achievementManager.database = new NullDataSource();	
		}
		if(getConfig().getString("ach.database.type","").equalsIgnoreCase("file")){

			achievementManager.database = new YamlDataSource(this);	
		}

		if(achievementManager.database == null){
			printCon("NO SUITABLE DATABASE SELECTED!!");

			onDisable();
			return;
		}

		//Load installed triggers
		addTrigger(AchCheckTrigger.class);
		addTrigger(CuboidCheckTrigger.class);
		addTrigger(LockedTrigger.class);
		addTrigger(NoAchCheckTrigger.class);
		addTrigger(PermCheckTrigger.class);
		addTrigger(StatCheckTrigger.class);
		addTrigger(StatWithinTrigger.class);
		addTrigger(EconomyTrigger.class);

		//load installed rewards
		addReward(CommandReward.class);
		addReward(CounterReward.class);
		addReward(DroxSubGroupReward.class);
		addReward(DroxTrackReward.class);
		addReward(EconomyReward.class);


		//TODO, HERE IS WHERE TO IMPLEMENT ADDON LOADING



		achievementManager.loadAchievements();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			public void run() {
				achievementManager.checkPlayers();
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

	public void addTrigger(Class<? extends ITrigger > trigger){
		AbstractDataSource.triggerFactory.addPart(trigger);
	}
	public void addReward(Class<? extends IReward >  reward){
		AbstractDataSource.rewardFactory.addPart(reward);
	}

	public AchievementManager getAchievementManager(){
		return achievementManager;

	}
	
	public static String colorise(String msg){
		
		for(int i = 0;i<=9;i++){
			msg = msg.replaceAll("&" + i, ChatColor.getByChar(""+i).toString());
		}
		for(char i = 'a';i<='f';i++){
			msg = msg.replaceAll("&" + i, ChatColor.getByChar(i).toString());
		}
		return msg;
	}
}
