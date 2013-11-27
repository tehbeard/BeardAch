package com.tehbeard.beardach;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.tehbeard.beardach.achievement.*;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.commands.*;
import com.tehbeard.beardach.dataSource.*;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.configurable.IConfigurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorJSON;

import com.tehbeard.utils.addons.AddonLoader;
import com.tehbeard.utils.factory.ConfigurableFactory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.tehbeard.beardstat.BeardStat;
import com.tehbeard.beardstat.manager.EntityStatManager;


import de.hydrox.bukkit.DroxPerms.DroxPerms;
import de.hydrox.bukkit.DroxPerms.DroxPermsAPI;
import java.util.logging.Handler;
import java.util.logging.Level;

public class BeardAch extends JavaPlugin {

    private static BeardAch self;
    
    public static BeardAch instance(){
        if(self == null){throw new IllegalStateException("No referenece to BeardAch found");}
        return self;
    }

    private AddonLoader<IConfigurable> addonLoader;
    private Metrics metrics;

    public static int triggersMetric = 0;
    public static int rewardsMetric = 0;

    public static DroxPermsAPI droxAPI = null;
    private WorldGuardPlugin worldGuard;
    private AchievementManager achievementManager;

    
    private EditorJSON json = new EditorJSON();
    
    private BeardAchCuboidListener cuboidListener = new BeardAchCuboidListener();
    private EntityStatManager stats;
    
    private static boolean allowExecRewards = false;
    public static boolean isAllowExecRewards(){
        return allowExecRewards;
    }
    
    /**
     * Load BeardAch
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onEnable() {
        self = this;
        achievementManager = new AchievementManager();
        //Load config
        getLogger().info("Starting BeardAch");
        /*if(!getConfig().getKeys(false).contains("achievements")){
            
        }*/
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        
        //Hopefully this pleases Diemex
        Level level = Level.parse(getConfig().getString("general.loglevel","INFO"));
        getLogger().setLevel(level);
        for(Handler handler : getLogger().getHandlers()){
            handler.setLevel(level);
        }

        allowExecRewards = getConfig().getBoolean("ach.allow-exec-shell-reward",false);
        if(allowExecRewards){
            getLogger().info("ALERT, SHELL EXEC REWARD ACTIVE, BE CAREFUL WHO YOU GIVE ACCESS TO ACHIEVEMENT DEFINITIONS");
        }
            
        EnableBeardStat();

        Bukkit.getPluginManager().registerEvents(cuboidListener, this);

        //check DroxPerms
        DroxPerms droxPerms = ((DroxPerms) this.getServer().getPluginManager().getPlugin("DroxPerms"));
        if (droxPerms != null) {
            droxAPI = droxPerms.getAPI();
        }

        //check WorldGuard
        worldGuard = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

        getLogger().info("Loading Data Adapters");
        ConfigurableFactory<IDataSource,DataSourceDescriptor> dataSourceFactory = new ConfigurableFactory<IDataSource, DataSourceDescriptor>(DataSourceDescriptor.class) {

            @Override
            public String getTag(DataSourceDescriptor annotation) {
                return annotation.tag();
            }
        };
        
        dataSourceFactory.addProduct(YamlDataSource.class);
        dataSourceFactory.addProduct(SqlDataSource.class);
        dataSourceFactory.addProduct(NullDataSource.class);
        dataSourceFactory.addProduct(GSONDataSource.class);
        dataSourceFactory.addProduct(JDBCAchievementDataSource.class);

        achievementManager.database = dataSourceFactory.getProduct(getConfig().getString("ach.database.type",""));

        if(achievementManager.database == null){
            self.getLogger().severe("[ERROR] " + "NO SUITABLE DATABASE SELECTED!!");
            self.getLogger().severe("[ERROR] " + "DISABLING PLUGIN!!");

            //onDisable();
            setEnabled(false);
            return;
        }
        getLogger().info("Installing default triggers and rewards");
        
        Scanner s = new Scanner(getResource("components.txt"));
        while(s.hasNextLine()){
        	try {
				Class<?> c = Class.forName(s.nextLine());
				if(ITrigger.class.isAssignableFrom(c)){
					addTrigger((Class<? extends ITrigger>) c);
				}
				
				if(IReward.class.isAssignableFrom(c)){
					addReward((Class<? extends IReward>) c);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }

  
        


        //Load built in extras
        InputStream bundle = getResource("bundle.properties");

        if(bundle!=null){
            try{
                getLogger().info("Loading bundled addons");

                Scanner scanner;

                scanner = new Scanner(bundle);
                while(scanner.hasNext()){
                    String ln = scanner.nextLine();
                    String[] l = ln.split("=");
                    if(l[0].equalsIgnoreCase("name")){
                        getLogger().info("Loading bundled addon " + l[1]);
                    }else if(l[0].equalsIgnoreCase("class")){
                        Class<?> c = getClassLoader().loadClass(l[1]);
                        if(c!=null){
                            if(ITrigger.class.isAssignableFrom(c)){
                                triggersMetric ++;
                                addTrigger((Class<? extends ITrigger>) c);
                            }else if(IReward.class.isAssignableFrom(c)){
                                rewardsMetric ++;
                                addReward((Class<? extends IReward>) c);
                            }
                        }
                    }


                }
                scanner.close();
            } catch (ClassNotFoundException e) {
                getLogger().info("[PANIC] Could not load a class listed in the bundle file");
            }
        }        


        getLogger().info("Preparing to load addons");
        //Create addon dir if it doesn't exist
        File addonDir = (new File(getDataFolder(),"addons"));
        if(!addonDir.exists()){
            addonDir.mkdir();
        }

        //create the addon loader
        addonLoader = new BeardAchAddonLoader(addonDir);

        getLogger().info("Loading addons");
        addonLoader.loadAddons();

        getLogger().info("Writing editor settings");
        new File(getDataFolder(),"editor").mkdirs();
        try {
            File settingsFile = new File(getDataFolder(),"editor/settings.js");
            if(settingsFile.exists()){settingsFile.delete();}
			json.write(settingsFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        exportEditor();

        getLogger().info("Loading Achievements");

        achievementManager.loadAchievements();


        //metrics code
        if(!getConfig().getBoolean("general.plugin-stats-opt-out",false)){
            try {
                metrics = new Metrics(this);


                //set up custom plotters for custom triggers and rewards
                SimplePlotter ct = new SimplePlotter("Custom Triggers");
                SimplePlotter cr = new SimplePlotter("Custom Rewards");
                ct.set(triggersMetric);
                cr.set(rewardsMetric);
                final String bsInstalled = "BeardStat " + (getStats()==null ? "not " : "") + "found";
                    metrics.createGraph("BeardStat found").addPlotter(new Plotter(bsInstalled) {

                        public int getValue() {
                            return 1;
                        }
                        
                        
                    });
                metrics.createGraph("Custom triggers").addPlotter(ct);
                metrics.createGraph("Custom rewards").addPlotter(cr);

                //total achievements on server
                SimplePlotter totalAchievments = new SimplePlotter("Total Achievements");
                totalAchievments.set(achievementManager.getAchievementsList().size());

                //Triggers per achievement
                Graph triggersGraph = metrics.createGraph("triggers");
                for(final String trig : AchievementLoader.triggerFactory.getTags()){
                    SimplePlotter p = new SimplePlotter(trig + " Trigger");

                    for(Achievement a : achievementManager.getAchievementsList()){
                        for(ITrigger t : a.getTrigs()){
                            Configurable c = t.getClass().getAnnotation(Configurable.class);
                            if(c!=null){
                                if(trig.equals(c.tag())){
                                    p.increment();
                                }
                            }
                        }
                    }

                    triggersGraph.addPlotter(p);

                }


                //Rewards per achievement
                Graph rewardsGraph = metrics.createGraph("rewards");
                for(final String reward : AchievementLoader.rewardFactory.getTags()){
                    SimplePlotter p = new SimplePlotter(reward + " Reward");

                    for(Achievement a : achievementManager.getAchievementsList()){
                        for(IReward r : a.getRewards()){
                            Configurable c = r.getClass().getAnnotation(Configurable.class);
                            if(c!=null){
                                if(reward.equals(c.tag())){
                                    p.increment();
                                }
                            }
                        }
                    }

                    rewardsGraph.addPlotter(p);
                }

                DataSourceDescriptor c = achievementManager.database.getClass().getAnnotation(DataSourceDescriptor.class);
                Graph g = metrics.createGraph("storage system");
                g.addPlotter(new Plotter(c.tag() + " storage"){

                    @Override
                    public int getValue() {
                        return 1;
                    }}
                        );

                metrics.start();

            } catch (Exception e) {
                getLogger().info("Could not load metrics :(");
                getLogger().info("Please send the following stack trace to Tehbeard");
                getLogger().info("=======================");
                e.printStackTrace();
                getLogger().info("=======================");
            }

        }

        getLogger().info("Starting achievement checker");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

            public void run() {
                achievementManager.checkPlayers();
            }

        }, 200L,200L);

        //setup events
        getServer().getPluginManager().registerEvents(achievementManager,this);

        getLogger().info("Loading commands");
        //commands

        getCommand("ach-reload").setExecutor(new AchReloadCommand());
        getCommand("ach").setExecutor(new AchCommand());
        getCommand("ach-fancy").setExecutor(new AchFancyCommand());
        getLogger().info("Loaded Version:" + getDescription().getVersion());


    }

    /**
     * Unload BeardAch
     */
    @Override
    public void onDisable() {

        achievementManager.database.flush();

    }

    /**
     * Handle unfinished commands
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args) {

        sender.sendMessage("COMMAND NOT IMPLEMENTED");
        return true;
    }

    

    /**
     * Add a trigger
     * @param trigger
     */
    public void addTrigger(Class<? extends ITrigger > trigger){
      
        AchievementLoader.triggerFactory.addProduct(trigger);
        json.addTrigger(trigger);
    }
    /**
     * Add a reward
     * @param reward
     */
    public void addReward(Class<? extends IReward >  reward){
        AchievementLoader.rewardFactory.addProduct(reward);
        json.addReward(reward);
    }

    /**
     * Colorises strings containing &0-f
     * @param msg
     * @return
     */
    public static String colorise(String msg){

        for(int i = 0;i<=9;i++){
            msg = msg.replaceAll("&" + i, ChatColor.getByChar(""+i).toString());
        }
        for(char i = 'a';i<='f';i++){
            msg = msg.replaceAll("&" + i, ChatColor.getByChar(i).toString());
        }
        return msg;
    }
    /**
     * Print error message with an exception
     * @param errMsg
     * @param e
     */
    public static void printError(String errMsg,Exception e){
        self.getLogger().severe("[ERROR] " + errMsg);
        self.getLogger().severe("[ERROR] ==Stack trace dump==");
        e.printStackTrace();
        self.getLogger().severe("[ERROR] ==Stack trace dump==");
    }


    /**
     * return the achievement manager
     * @return
     */
    public AchievementManager getAchievementManager(){
        return achievementManager;
    }


    /**
     * Try to load BeardStat
     */
    private void EnableBeardStat(){
        BeardStat bs = (BeardStat) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
        if(bs!=null && bs.isEnabled()){
            stats = bs.getStatManager();
        }
        else
        {
            self.getLogger().severe("[ERROR] " + "BeardStat not installed! stat and statwithin triggers will not function!");
        }
    }

    /**
     * Return WorldGuard instance
     * @return
     */
    public WorldGuardPlugin getWorldGuard() {
        return worldGuard;
    }

    /**
     * Returns BeardStat instance
     * @return
     */
    public EntityStatManager getStats(){
        return stats;
    }


    public static final int BUFFER_SIZE = 8192;
    
    private void exportEditor(){
    	try {
    	ZipInputStream zis = new ZipInputStream(getResource("editor.zip"));
    	ZipEntry entry = null;

    	File outputDir = new File(getDataFolder(),"editor");
        
			while((entry = zis.getNextEntry()) != null)
			{
				File outputFile = new File(outputDir,entry.getName());
				
				if(entry.isDirectory()){
					outputFile.mkdirs();
					continue;
				}
				int count;

                byte data[] = new byte[BUFFER_SIZE];

                //write the file to the disk
                FileOutputStream fos = new FileOutputStream(outputFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE);

                while((count = zis.read(data, 0, BUFFER_SIZE)) != -1)
                {
                    dest.write(data, 0, count);
                }

                //close the output streams
                dest.flush();
                dest.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
    }

    public BeardAchCuboidListener getCuboidListener() {
        return cuboidListener;
    }


}
