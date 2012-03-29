package me.tehbeard.BeardAch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.tehbeard.BeardAch.achievement.*;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.*;
import me.tehbeard.BeardAch.achievement.rewards.*;
import me.tehbeard.BeardAch.commands.*;
import me.tehbeard.BeardAch.dataSource.*;
import me.tehbeard.BeardAch.dataSource.configurable.IConfigurable;
import me.tehbeard.BeardStat.BeardStat;
import me.tehbeard.BeardStat.containers.PlayerStatManager;
import me.tehbeard.utils.addons.AddonLoader;
import me.tehbeard.utils.factory.ConfigurableFactory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import de.hydrox.bukkit.DroxPerms.DroxPerms;
import de.hydrox.bukkit.DroxPerms.DroxPermsAPI;

public class BeardAch extends JavaPlugin {

    public static BeardAch self;
    private PlayerStatManager stats = null;
    private AchievementManager achievementManager;
    private AddonLoader<IConfigurable> addonLoader;
    
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
        else
        {
            printCon("[PANIC] BeardStat not installed! stat and statwithin triggers will not function!");
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


        
        printCon("Loading Data Adapters");
        ConfigurableFactory<IDataSource,DataSourceDescriptor> dataSourceFactory = new ConfigurableFactory<IDataSource, DataSourceDescriptor>(DataSourceDescriptor.class) {
            
            @Override
            public String getTag(DataSourceDescriptor annotation) {
                return annotation.tag();
            }
        };
        dataSourceFactory.addProduct(YamlDataSource.class);
        dataSourceFactory.addProduct(SqlDataSource.class);
        dataSourceFactory.addProduct(NullDataSource.class);

        achievementManager.database = dataSourceFactory.getProduct(getConfig().getString("ach.database.type",""));

        if(achievementManager.database == null){
            printCon("[ERROR] NO SUITABLE DATABASE SELECTED!!");
            printCon("[ERROR] DISABLING PLUGIN!!");

            //onDisable();
            setEnabled(false);
            return;
        }

        printCon("Installing default triggers");
        //Load installed triggers
        addTrigger(AchCheckTrigger.class);
        addTrigger(AchCountTrigger.class);
        addTrigger(CuboidCheckTrigger.class);
        addTrigger(LockedTrigger.class);
        addTrigger(NoAchCheckTrigger.class);
        addTrigger(PermCheckTrigger.class);
        addTrigger(StatCheckTrigger.class);
        addTrigger(StatWithinTrigger.class);
        addTrigger(EconomyTrigger.class);
        addTrigger(SpeedRunTrigger.class);
        
        printCon("Installing default rewards");
        //load installed rewards
        addReward(CommandReward.class);
        addReward(CounterReward.class);
        addReward(DroxSubGroupReward.class);
        addReward(DroxTrackReward.class);
        addReward(EconomyReward.class);

        //Load built in extras
        InputStream bundle = getResource("bundle.txt");
        if(bundle!=null){
            printCon("Loading bundled addons");
            BufferedReader reader = new BufferedReader(new InputStreamReader(bundle));
            try {
                while(reader.ready()){
                    Class<?> c = getClassLoader().loadClass(reader.readLine());
                    if(c!=null){
                        if(ITrigger.class.isAssignableFrom(c)){
                           addTrigger((Class<? extends ITrigger>) c);
                        }else if(IReward.class.isAssignableFrom(c)){
                            addReward((Class<? extends IReward>) c);
                        }
                    }
                }
            } catch (IOException e) {
                printCon("[PANIC] An error occured trying to read the bundle file (bundle.txt)");
            } catch (ClassNotFoundException e) {
                printCon("[PANIC] Could not load a class listed in the bundle file");
            }
        }        
        

        printCon("Preparing to load addons");
        //Create addon dir if it doesn't exist
        File addonDir = (new File(getDataFolder(),"addons"));
        if(!addonDir.exists()){
            addonDir.mkdir();
        }

        //create the addon loader
        addonLoader = new BeardAchAddonLoader(addonDir);

        printCon("Loading addons");
        addonLoader.loadAddons();
        printCon("Loading Achievements");
        achievementManager.loadAchievements();

        printCon("Starting achievement checker");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

            public void run() {
                achievementManager.checkPlayers();
            }

        }, 600L,600L);

        //setup events
        getServer().getPluginManager().registerEvents(new AchListener(achievementManager),this);

        printCon("Loading commands");
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
                e.printStackTrace();
            }
        }
    }

    public void addTrigger(Class<? extends ITrigger > trigger){
        AbstractDataSource.triggerFactory.addProduct(trigger);
    }
    public void addReward(Class<? extends IReward >  reward){
        AbstractDataSource.rewardFactory.addProduct(reward);
    }

    /**
     * return the achievement manager
     * @return
     */
    public AchievementManager getAchievementManager(){
        return achievementManager;

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

}
