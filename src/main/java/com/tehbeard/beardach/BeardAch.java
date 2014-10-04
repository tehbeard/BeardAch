package com.tehbeard.beardach;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementManager;
import com.tehbeard.beardach.achievement.BeardAchAddonLoader;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.achievement.triggers.player.IsGamemodeTrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;
import com.tehbeard.beardach.commands.AchCommand;
import com.tehbeard.beardach.commands.AchFancyCommand;
import com.tehbeard.beardach.commands.AchReloadCommand;
import com.tehbeard.beardach.commands.ExportEditorCommand;
import com.tehbeard.beardach.commands.TestAchCommand;
import com.tehbeard.beardach.datasource.AchievementLoader;
import com.tehbeard.beardach.datasource.GSONDataSource;
import com.tehbeard.beardach.datasource.IDataSource;
import com.tehbeard.beardach.datasource.JDBCAchievementDataSource;
import com.tehbeard.beardach.datasource.NullDataSource;
import com.tehbeard.beardach.datasource.configurable.IConfigurable;
import com.tehbeard.beardach.datasource.json.editor.EditorJSON;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardstat.bukkit.BukkitPlugin;
import com.tehbeard.beardstat.manager.EntityStatManager;
import com.tehbeard.utils.addons.AddonLoader;
import com.tehbeard.utils.factory.ConfigurableFactory;

import de.hydrox.bukkit.DroxPerms.DroxPerms;
import de.hydrox.bukkit.DroxPerms.DroxPermsAPI;

public class BeardAch extends JavaPlugin {

    private static BeardAch self;

    public static BeardAch instance() {
        if (self == null)
            throw new IllegalStateException("No referenece to BeardAch found");
        return self;
    }

    private AddonLoader<IConfigurable> addonLoader;
    private Metrics metrics;

    public static int triggersMetric = 0;
    public static int rewardsMetric = 0;

    public static DroxPermsAPI droxAPI = null;
    private WorldGuardPlugin worldGuard;
    private AchievementManager achievementManager;

    private EditorJSON jsonEditorSettings = new EditorJSON();

    private BeardAchListener cuboidListener = new BeardAchListener();
    private EntityStatManager stats;

    private static boolean allowExecRewards = false;

    public static boolean isAllowExecRewards() {
        return allowExecRewards;
    }

    /**
     * Load BeardAch
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onEnable() {
        
        self = this;
        
        
        // Load config
        
        getLogger().info("Starting BeardAch");
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        // Hopefully this pleases Diemex
        Level level = Level.parse(getConfig().getString("general.loglevel", "INFO"));
        getLogger().setLevel(level);
        for (Handler handler : getLogger().getHandlers()) {
            handler.setLevel(level);
        }

        allowExecRewards = getConfig().getBoolean("ach.allow-exec-shell-reward", false);
        if (allowExecRewards) {
            getLogger().info("ALERT, SHELL EXEC REWARD ACTIVE, BE CAREFUL WHO YOU GIVE ACCESS TO ACHIEVEMENT DEFINITIONS");
        }

        EnableBeardStat();

        Bukkit.getPluginManager().registerEvents(cuboidListener, this);

        // check DroxPerms
        DroxPerms droxPerms = (DroxPerms) this.getServer().getPluginManager().getPlugin("DroxPerms");
        if (droxPerms != null) {
            droxAPI = droxPerms.getAPI();
        }

        // check WorldGuard
        worldGuard = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

        getLogger().info("Loading Data Adapters");
        ConfigurableFactory<IDataSource, DataSourceDescriptor> dataSourceFactory = new ConfigurableFactory<IDataSource, DataSourceDescriptor>(DataSourceDescriptor.class) {

            @Override
            public String getTag(DataSourceDescriptor annotation) {
                return annotation.tag();
            }
        };

        //dataSourceFactory.addProduct(SqlDataSource.class);
        dataSourceFactory.addProduct(NullDataSource.class);
        dataSourceFactory.addProduct(GSONDataSource.class);
        dataSourceFactory.addProduct(JDBCAchievementDataSource.class);

        IDataSource db = dataSourceFactory.getProduct(getConfig().getString("ach.database.type", ""));

        if (db == null) {
            self.getLogger().severe("NO SUITABLE DATABASE SELECTED!!");
            self.getLogger().severe("[DISABLING PLUGIN!!");

            // onDisable();
            setEnabled(false);
            return;
        }
        
        //Do db migration if needed.
        achievementManager = new AchievementManager(db);
        
        getLogger().info("Installing default triggers and rewards");

        Scanner s = new Scanner(getResource("components.txt"));
        while (s.hasNextLine()) {
            try {
                
                Class<?> c = Class.forName(s.nextLine());
                if (ITrigger.class.isAssignableFrom(c)) {
                    addTrigger((Class<? extends ITrigger>) c);
                }

                if (IReward.class.isAssignableFrom(c)) {
                    addReward((Class<? extends IReward>) c);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        s.close();

        getLogger().info("Preparing to load addons");
        // Create addon dir if it doesn't exist
        File addonDir = new File(getDataFolder(), "addons");
        if (!addonDir.exists()) {
            addonDir.mkdir();
        }

        // create the addon loader
        addonLoader = new BeardAchAddonLoader(addonDir);

        getLogger().info("Loading addons");
        addonLoader.loadAddons();

        getLogger().info("Loading Achievements");

        achievementManager.loadAchievements();

        // metrics code
        if (!getConfig().getBoolean("general.plugin-stats-opt-out", false)) {
            try {
                metrics = new Metrics(this);

                // set up custom plotters for custom triggers and rewards
                SimplePlotter ct = new SimplePlotter("Custom Triggers");
                SimplePlotter cr = new SimplePlotter("Custom Rewards");
                ct.set(triggersMetric);
                cr.set(rewardsMetric);
                final String bsInstalled = "BeardStat " + (getStats() == null ? "not " : "") + "found";
                metrics.createGraph("BeardStat found").addPlotter(new Plotter(bsInstalled) {

                    @Override
                    public int getValue() {
                        return 1;
                    }

                });
                metrics.createGraph("Custom triggers").addPlotter(ct);
                metrics.createGraph("Custom rewards").addPlotter(cr);

                // total achievements on server
                SimplePlotter totalAchievments = new SimplePlotter("Total Achievements");
                totalAchievments.set(achievementManager.getAchievementsList().size());

                // Triggers per achievement
                Graph triggersGraph = metrics.createGraph("triggers");
                for (final String trig : AchievementLoader.triggerFactory.getTags()) {
                    SimplePlotter p = new SimplePlotter(trig + " Trigger");

                    for (Achievement a : achievementManager.getAchievementsList()) {
                        for (ITrigger t : a.getTrigs()) {
                            Configurable c = t.getClass().getAnnotation(Configurable.class);
                            if (c != null) {
                                if (trig.equals(c.tag())) {
                                    p.increment();
                                }
                            }
                        }
                    }

                    triggersGraph.addPlotter(p);

                }

                // Rewards per achievement
                Graph rewardsGraph = metrics.createGraph("rewards");
                for (final String reward : AchievementLoader.rewardFactory.getTags()) {
                    SimplePlotter p = new SimplePlotter(reward + " Reward");

                    for (Achievement a : achievementManager.getAchievementsList()) {
                        for (IReward r : a.getRewards()) {
                            Configurable c = r.getClass().getAnnotation(Configurable.class);
                            if (c != null) {
                                if (reward.equals(c.tag())) {
                                    p.increment();
                                }
                            }
                        }
                    }

                    rewardsGraph.addPlotter(p);
                }

                DataSourceDescriptor c = db.getClass().getAnnotation(DataSourceDescriptor.class);
                Graph g = metrics.createGraph("storage system");
                g.addPlotter(new Plotter(c.tag() + " storage") {

                    @Override
                    public int getValue() {
                        return 1;
                    }
                });

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
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                achievementManager.checkPlayers();
            }

        }, 200L, 200L);
        
        exportEditor();

        // setup events
        getServer().getPluginManager().registerEvents(getListener(), this);
        
        if(getConfig().getBoolean("ach.add-no-creative-trigger",false)){
            getLogger().info("Adding no creative trigger to all achievements");
            
            for(Achievement ach : achievementManager.getAchievementsList()){
                ach.addTrigger(new IsGamemodeTrigger(GameMode.CREATIVE, true));
            }
        }

        getLogger().info("Loading commands");
        // commands

        getCommand("ach-reload").setExecutor(new AchReloadCommand());
        getCommand("ach").setExecutor(new AchCommand());
        getCommand("ach-fancy").setExecutor(new AchFancyCommand());
        getCommand("ach-export").setExecutor(new ExportEditorCommand());
        getCommand("testach").setExecutor(new TestAchCommand(achievementManager));
        getLogger().info("Loaded Version:" + getDescription().getVersion());

    }

    /**
     * Unload BeardAch
     */
    @Override
    public void onDisable() {

        achievementManager.flush();

    }

    /**
     * Handle unfinished commands
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("COMMAND NOT IMPLEMENTED");
        return true;
    }

    
    private boolean hasDependencies(Class<?> c,String type){
        ComponentHelpDescription desc = c.getAnnotation(ComponentHelpDescription.class);
        Configurable config = c.getAnnotation(Configurable.class);
        if(desc != null){
            for(String pluginName : desc.dependencies()){
                if(Bukkit.getPluginManager().getPlugin(pluginName) == null){
                    getLogger().warning("[" + config.tag() + "] " + config.name() + " depends on " + pluginName + " which is not found, this " + type + " has been disabled. This may cause errors if an achievement is built using this.");
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Add a trigger
     * 
     * @param trigger
     */
    public void addTrigger(Class<? extends ITrigger> trigger) {
        if(!hasDependencies(trigger,"trigger")){return;}
        AchievementLoader.triggerFactory.addProduct(trigger);
        jsonEditorSettings.addTrigger(trigger);
    }

    /**
     * Add a reward
     * 
     * @param reward
     */
    public void addReward(Class<? extends IReward> reward) {
        if(!hasDependencies(reward,"reward")){return;}
        AchievementLoader.rewardFactory.addProduct(reward);
        jsonEditorSettings.addReward(reward);
    }

    /**
     * Colorises strings containing &0-f
     * 
     * @param msg
     * @return
     */
    public static String colorise(String msg) {

        for (int i = 0; i <= 9; i++) {
            msg = msg.replaceAll("&" + i, ChatColor.getByChar("" + i).toString());
        }
        for (char i = 'a'; i <= 'f'; i++) {
            msg = msg.replaceAll("&" + i, ChatColor.getByChar(i).toString());
        }
        return msg;
    }

    /**
     * Print error message with an exception
     * 
     * @param errMsg
     * @param e
     */
    public static void printError(String errMsg, Exception e) {
        self.getLogger().severe("[ERROR] " + errMsg);
        self.getLogger().severe("[ERROR] ==Stack trace dump==");
        e.printStackTrace();
        self.getLogger().severe("[ERROR] ==Stack trace dump==");
    }

    /**
     * return the achievement manager
     * 
     * @return
     */
    public AchievementManager getAchievementManager() {
        return achievementManager;
    }

    /**
     * Try to load BeardStat
     */
    private void EnableBeardStat() {
        BukkitPlugin bs = (BukkitPlugin) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
        if (bs != null && bs.isEnabled()) {
            stats = bs.getStatManager();
        } else {
            self.getLogger().severe("[ERROR] " + "BeardStat not installed! stat and statwithin triggers will not function!");
        }
    }

    /**
     * Return WorldGuard instance
     * 
     * @return
     */
    public WorldGuardPlugin getWorldGuard() {
        return worldGuard;
    }

    /**
     * Returns BeardStat instance
     * 
     * @return
     */
    public EntityStatManager getStats() {
        return stats;
    }

    public static final int BUFFER_SIZE = 8192;

    public void exportEditor() {
        
        getLogger().info("Writing editor settings");
        new File(getDataFolder(), "editor").mkdirs();
        try {
            File settingsFile = new File(getDataFolder(), "editor/settings.js");
            if (settingsFile.exists()) {
                settingsFile.delete();
            }
            jsonEditorSettings.write(settingsFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try {
            ZipInputStream zis = new ZipInputStream(getResource("editor.zip"));
            ZipEntry entry = null;

            File outputDir = new File(getDataFolder(), "editor");

            while ((entry = zis.getNextEntry()) != null) {
                File outputFile = new File(outputDir, entry.getName());

                if (entry.isDirectory()) {
                    outputFile.mkdirs();
                    continue;
                }
                int count;

                byte data[] = new byte[BUFFER_SIZE];

                // write the file to the disk
                FileOutputStream fos = new FileOutputStream(outputFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE);

                while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
                    dest.write(data, 0, count);
                }

                // close the output streams
                dest.flush();
                dest.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public BeardAchListener getListener() {
        return cuboidListener;
    }

}
