package me.tehbeard.BeardAch.dataSource;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.Achievement.Display;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.ClassCatalogue;
import me.tehbeard.BeardAch.dataSource.json.RewardJSONParser;
import me.tehbeard.BeardAch.dataSource.json.TriggerJSONParser;
import me.tehbeard.utils.factory.ConfigurableFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AchievementLoader {
    
    public static final ClassCatalogue<ITrigger> triggerFactory = new ClassCatalogue<ITrigger>();
    public static final ClassCatalogue<IReward> rewardFactory = new ClassCatalogue<IReward>();

    private static Gson gson = new GsonBuilder().
            excludeFieldsWithoutExposeAnnotation().
            registerTypeHierarchyAdapter(ITrigger.class, new TriggerJSONParser()).
            registerTypeHierarchyAdapter(IReward.class, new RewardJSONParser()).
            create();
    
   
    public static void loadAchievements(){
        loadConfigAchievements();
    }
    
    
    //TODO: KILL THIS WITH FIRE
    public static void loadConfigAchievements(){
        BeardAch.printDebugCon("Loading Achievement Data");
        BeardAch.self.reloadConfig();
        Set<String> achs = BeardAch.self.getConfig().getConfigurationSection("achievements").getKeys(false);
        if(achs==null){
            BeardAch.printCon("[PANIC] NO ACHIEVEMENTS FOUND");
            return;
        }
        for(String slug : achs){
            ConfigurationSection e = BeardAch.self.getConfig().getConfigurationSection("achievements").getConfigurationSection(slug);
            if(e==null){
                continue;
            }
            //load information
            String name = e.getString("name");
            String descrip = e.getString("descrip");
            Display broadcast = Achievement.Display.valueOf(e.getString("broadcast",BeardAch.self.getConfig().getString("ach.msg.send","NONE")));
            slug = e.getString("alias",slug);
            boolean hidden = e.getBoolean("hidden",false);
            BeardAch.printDebugCon("Loading achievement " + name);

            Achievement ach = new Achievement(slug,name, descrip,broadcast,hidden);

            //load triggers
            List<String> triggers = e.getStringList("triggers");
            for(String trig: triggers){
                String[] part = trig.split("\\|");
                if(part.length==2){
                    BeardAch.printDebugCon("Trigger => " + trig);
                    ITrigger trigger = triggerFactory.getProduct(part[0]);
                    if(trigger==null){BeardAch.printCon("[PANIC] TRIGGER " + part[0] + " NOT FOUND!!! SKIPPING.");continue;}
                    trigger.configure(ach,part[1]);
                    ach.addTrigger(trigger);
                }
                else
                {
                    BeardAch.printCon("[PANIC] ERROR! MALFORMED TRIGGER FOR ACHIEVEMENT " + name);
                }
            }
            List<String> rewards = e.getStringList("rewards");
            for(String reward: rewards){
                String[] part = reward.split("\\|");
                if(part.length==2){
                    BeardAch.printDebugCon("Reward => " + reward); 
                    IReward rewardInst = rewardFactory.getProduct(part[0]);
                    rewardInst.configure(ach,part[1]);
                    ach.addReward(rewardInst);
                }
                else
                {
                    BeardAch.printCon("[PANIC] ERROR! MALFORMED REWARD FOR ACHIEVEMENT " + name);
                }
            }

            BeardAch.self.getAchievementManager().addAchievement(ach);
            BeardAch.printDebugCon("Loaded achievement " + name);
        }
    }
}
