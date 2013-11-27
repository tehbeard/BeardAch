package com.tehbeard.beardach.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.Achievement.Display;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.datasource.json.ClassCatalogue;
import com.tehbeard.beardach.datasource.json.LocationJSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.util.logging.Level;
import com.tehbeard.beardach.datasource.json.ClassBasedParser;
import com.tehbeard.beardach.datasource.json.CuboidJSONParser;
import com.tehbeard.utils.cuboid.Cuboid;


/**
 * Loads achievements from an external Gson file
 * @author James
 *
 */
public class AchievementLoader {

	public static final ClassCatalogue<ITrigger> triggerFactory = new ClassCatalogue<ITrigger>();
	public static final ClassCatalogue<IReward> rewardFactory = new ClassCatalogue<IReward>();

	/**
	 * Create prime Gson object, 
	 * Only export annotated fields
	 * Pretty print for human debugging.
	 * Also adds type adapters for trigger, reward and location
	 */
	private static Gson gson = new GsonBuilder().
			excludeFieldsWithoutExposeAnnotation().
			setPrettyPrinting().
			registerTypeAdapter(ITrigger.class, new ClassBasedParser<ITrigger>(triggerFactory)).
			registerTypeAdapter(IReward.class, new ClassBasedParser<IReward>(rewardFactory)).
                        registerTypeAdapter(Cuboid.class,new CuboidJSONParser()).
                        registerTypeAdapter(Location.class,new LocationJSONParser()).
			create();

	private static List<Achievement> loadAchievementsFromJSONFile(File file){
	    
        try {
            return gson.fromJson(new FileReader(file), new TypeToken<List<Achievement>>(){}.getType());
        } catch (JsonIOException e) {
            BeardAch.printError("An error occured reading " + file.toString(),e);
        } catch (JsonSyntaxException e) {
            BeardAch.printError("There is a problem with the syntax of " + file.toString(),e);
        } catch (FileNotFoundException e) {
            BeardAch.printError(file.toString() + " not found",e);
        }
        return null;
	}
	
	public static void loadAchievements(){

		try {
			//Load and create file
			File file = new File(BeardAch.instance().getDataFolder(),"ach.json");
			file.createNewFile();
			List<Achievement> achievements = loadAchievementsFromJSONFile(file);
			processAchievements(achievements);
			
			File achDir = new File(BeardAch.instance().getDataFolder(),"config");
			if(achDir.isDirectory() && achDir.exists()){
			    for(String f : achDir.list(new FilenameFilter() {
                    
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".json");
                    }
                })){
			        achievements = loadAchievementsFromJSONFile(new File(achDir,f));
			        processAchievements(achievements);
			    }
			}





			//TODO: Kill in 0.6
			//old method to load achievements
			List<Achievement> l = loadOldConfigAchievements();
			boolean tripped = false;
			for(Achievement a:l){
				tripped = true;
				BeardAch.instance().getLogger().log(Level.CONFIG, "Loading achievement {0}", a.getName());
				BeardAch.instance().getAchievementManager().addAchievement(a);
			}
			//convert old to new json awesomeness
			if(tripped){

				JsonWriter jw = new JsonWriter(new FileWriter(file));
				jw.setIndent("  ");
				gson.toJson(
						BeardAch.instance().getAchievementManager().getLoadedAchievements(),
						new TypeToken<List<Achievement>>(){}.getType(), 
						jw
						);
				jw.flush();
				jw.close();
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[BEARDACH] CONVERTED ACHIEVEMENTS TO JSON, PLEASE CHECK CONVERSION WORKED AND REMOVE ACHIEVEMENTS ENTRY FROM config.yml");
			}


		} catch (JsonIOException e) {
			BeardAch.printError("An error occured reading ach.json",e);

		} catch (JsonSyntaxException e) {
			BeardAch.printError("There is a problem with the syntax of ach.json",e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			BeardAch.printError("ach.json not found",e);
		} catch (IOException e) {
			BeardAch.printError("An error occured reading ach.json",e);
			e.printStackTrace();
		}
	}
	
	//Cycle each Achievement and postload as needed
	private static void processAchievements(List<Achievement> achievements) {
		if(achievements!=null){
			//Run postLoad() on all achievements and add them to manager if successful 
			for(Achievement a : achievements){
				if(a.postLoad()){
					BeardAch.instance().getLogger().log(Level.CONFIG, "Starting achievement {0}", a.getName());
					BeardAch.instance().getAchievementManager().addAchievement(a);
				}
				else
				{
					BeardAch.instance().getLogger().warning("Could not load " + a.getName());
				}
			}
		}
	}


	//TODO: KILL THIS WITH FIRE IN 0.6
	@SuppressWarnings("deprecation")
	public static List<Achievement> loadOldConfigAchievements(){

		List<Achievement> a = new ArrayList<Achievement>();

		BeardAch.instance().getLogger().config("Loading Achievement Data");
		BeardAch.instance().reloadConfig();
		if(BeardAch.instance().getConfig().isConfigurationSection("achievements")){
			BeardAch.instance().getLogger().config("[PANIC] OLD ACHIEVEMENTS CONFIG FOUND, CONVERSION WILL BE DONE");
		}
		else
		{
			return a;
		}

		Set<String> achs = BeardAch.instance().getConfig().getConfigurationSection("achievements").getKeys(false);

		for(String slug : achs){
			ConfigurationSection e = BeardAch.instance().getConfig().getConfigurationSection("achievements").getConfigurationSection(slug);
			if(e==null){
				continue;
			}
			//load information
			String name = e.getString("name");
			String descrip = e.getString("descrip");
			Display broadcast = Achievement.Display.valueOf(e.getString("broadcast",BeardAch.instance().getConfig().getString("ach.msg.send","NONE")));
			slug = e.getString("alias",slug);
			boolean hidden = e.getBoolean("hidden",false);
			BeardAch.instance().getLogger().config("Loading achievement " + name);


			Achievement ach = new Achievement(slug,name, descrip,broadcast,hidden);

			//load triggers
			try{
				List<String> triggers = e.getStringList("triggers");

				for(String trig: triggers){
					String[] part = trig.split("\\|");
					if(part.length==2){
						BeardAch.instance().getLogger().log(Level.FINE, "Trigger => {0}", trig);
						ITrigger trigger = triggerFactory.get(part[0]).newInstance();
						if(trigger==null){BeardAch.instance().getLogger().log(Level.WARNING, "[PANIC] TRIGGER {0} NOT FOUND!!! SKIPPING.", part[0]);continue;}
						trigger.configure(ach,part[1]);
						trigger.configure(ach);
						ach.addTrigger(trigger);
					}
					else
					{
						BeardAch.instance().getLogger().log(Level.WARNING, "[PANIC] ERROR! MALFORMED TRIGGER FOR ACHIEVEMENT {0}", name);
					}
				}
				List<String> rewards = e.getStringList("rewards");
				for(String reward: rewards){
					String[] part = reward.split("\\|");
					if(part.length==2){
						BeardAch.instance().getLogger().log(Level.CONFIG, "Reward => {0}", reward); 
						IReward rewardInst = rewardFactory.get(part[0]).newInstance();
						rewardInst.configure(ach,part[1]);
						rewardInst.configure(ach);
						ach.addReward(rewardInst);
					}
					else
					{
						BeardAch.instance().getLogger().log(Level.WARNING, "[PANIC] ERROR! MALFORMED REWARD FOR ACHIEVEMENT {0}", name);
					}
				}

			} catch (InstantiationException e1) {
				BeardAch.printError("Error loading old achievements",e1);
			} catch (IllegalAccessException e1) {
				BeardAch.printError("Error loading old achievements",e1);
			}

			a.add(ach);
		}

		BeardAch.instance().getConfig().set("oldAchievements", BeardAch.instance().getConfig().getConfigurationSection("achievements"));
		BeardAch.instance().getConfig().set("achievements",null);
		BeardAch.instance().saveConfig();

		return a;
	}
}
