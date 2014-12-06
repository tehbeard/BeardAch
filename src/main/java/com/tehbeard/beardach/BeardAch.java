package com.tehbeard.beardach;

import com.tehbeard.beardach.achievement.AchievementManager;
import com.tehbeard.beardach.achievement.BeardAchAddonLoader;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;
import com.tehbeard.beardach.datasource.AchievementLoader;
import com.tehbeard.beardach.datasource.GSONDataSource;
import com.tehbeard.beardach.datasource.IDataSource;
import com.tehbeard.beardach.datasource.JDBCAchievementDataSource;
import com.tehbeard.beardach.datasource.NullDataSource;
import com.tehbeard.beardach.datasource.configurable.IConfigurable;
import com.tehbeard.beardach.datasource.json.editor.EditorJSON;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.utils.addons.AddonLoader;
import com.tehbeard.utils.factory.ConfigurableFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.util.event.Subscribe;

@Plugin(id = "beardach",name = "BeardAch",version = "1.0.0")
public class BeardAch {
    
    private static AddonLoader<IConfigurable> addonLoader;

    private static AchievementManager achievementManager;

    private static final EditorJSON jsonEditorSettings = new EditorJSON();

    private static final BeardAchListener cuboidListener = new BeardAchListener();

    private static Logger logger;
    public static Logger getLogger(){
        return logger;
    }
    
    private static File configFolder;
    public static File getDataFolder(){
        return configFolder;
    }
    
    private static Game game;
    public static Game getGame(){
        return game;
    }
    
    public static PermissionService getPermissions(){
        return getGame().getServiceManager().provide(PermissionService.class).orNull();
    }
    
    public static InputStream getResource(String path){
        return BeardAch.class.getClassLoader().getResourceAsStream(path);
    }
    
    private static Configuration configuration;
    public static Configuration getConfig(){
        return configuration;
    }
    
    @Subscribe
    public void preBoot(PreInitializationEvent event) {
        logger = event.getPluginLog(); //TODO - Fix when dependencies work
        getLogger().info("Starting BeardAch");
        game = event.getGame();
    }
    @Subscribe
    public void bootup(InitializationEvent event) {
        //Load config
        //Set Logger level
        
        if (configuration.allowExecRewards) {
            getLogger().info("ALERT, SHELL EXEC REWARD ACTIVE, BE CAREFUL WHO YOU GIVE ACCESS TO ACHIEVEMENT DEFINITIONS");
        }


        //Bukkit.getPluginManager().registerEvents(cuboidListener, this);

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

        IDataSource db = dataSourceFactory.getProduct(getConfig().dbType);

        if (db == null) {
            getLogger().error("NO SUITABLE DATABASE SELECTED!!");
            getLogger().error("[DISABLING PLUGIN!!");

            // onDisable();
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
        if (!getConfig().statsOptOut){
        }

        getLogger().info("Starting achievement checker");
        
        game.getScheduler().runRepeatingTask(game.getPluginManager().fromInstance(this).get(), new Runnable() {

            @Override
            public void run() {
                achievementManager.checkPlayers();
            }

        }, 200L);
        
        exportEditor();

        // setup events
        game.getEventManager().register(this,getListener());
        
//        if(getConfig().noCreativeTrigger){
//            getLogger().info("Adding no creative trigger to all achievements");
//            for(Achievement ach : achievementManager.getAchievementsList()){
//                ach.addTrigger(new IsGamemodeTrigger(GameMode.CREATIVE, true));
//            }
//        }

        getLogger().info("Loading commands");
        // commands
        
//        getCommand("ach-reload").setExecutor(new AchReloadCommand());
//        getCommand("ach").setExecutor(new AchCommand());
//        getCommand("ach-fancy").setExecutor(new AchFancyCommand());
//        getCommand("ach-export").setExecutor(new ExportEditorCommand());
//        getCommand("testach").setExecutor(new TestAchCommand(achievementManager));
        

    }

    /**
     * Unload BeardAch
     */
    public void onDisable() {

        achievementManager.flush();

    }

    
    private boolean hasDependencies(Class<?> c,String type){
        ComponentHelpDescription desc = c.getAnnotation(ComponentHelpDescription.class);
        Configurable config = c.getAnnotation(Configurable.class);
        if(desc != null){
            for(String pluginName : desc.dependencies()){
                if(game.getPluginManager().getPlugin(pluginName) == null){
                    getLogger().warn("[" + config.tag() + "] " + config.name() + " depends on " + pluginName + " which is not found, this " + type + " has been disabled. This may cause errors if an achievement is built using this.");
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
    @Deprecated
    public static String colorise(String msg) {

        return msg;
    }

    /**
     * Print error message with an exception
     * 
     * @param errMsg
     * @param e
     */
    public static void printError(String errMsg, Exception e) {
        getLogger().error("[ERROR] " + errMsg);
        getLogger().error("[ERROR] ==Stack trace dump==");
        e.printStackTrace();
        getLogger().error("[ERROR] ==Stack trace dump==");
    }

    /**
     * return the achievement manager
     * 
     * @return
     */
    public static AchievementManager getAchievementManager() {
        return achievementManager;
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
