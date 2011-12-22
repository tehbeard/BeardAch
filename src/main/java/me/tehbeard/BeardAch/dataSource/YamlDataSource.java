package me.tehbeard.BeardAch.dataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.rewards.CommandReward;
import me.tehbeard.BeardAch.achievement.rewards.DroxSubGroupReward;
import me.tehbeard.BeardAch.achievement.rewards.DroxTrackReward;
import me.tehbeard.BeardAch.achievement.triggers.*;

import java.util.*;
public class YamlDataSource extends AbstractDataSource{


        Map<String, HashSet<String>> cache;
        YamlConfiguration db;
	public YamlDataSource(){
		BeardAch.printCon("Loading Yaml DataSource");
                db = YamlConfiguration.loadConfiguration(new File(BeardAch.self.getDataFolder(),"YamlDB.yml"));
                cache = new HashMap<String,HashSet<String>>();
	}

	
	public HashSet<String> getPlayersAchievements(String player) {
		// TODO Auto-generated method stub
                List<String> ach = (List<String>)db.getList("db." + player);
		HashSet<String> d = new HashSet<String>();
                for(String a : ach){
                  d.add(a);
                }
                //add to cache
                cache.put(player,d);
		//d.add("test");
		return d;
	}

	public void setPlayersAchievements(String player,
			String achievement) {
		// TODO Auto-generated method stub
		//BeardAch.printCon("[" + player + "] stored "+ achievement);
		cache.get(player).add(achievement);

	}

	public void flush() {
		// TODO Auto-generated method stub
	        List<String> l;

		for(Entry<String, HashSet<String>> entry : cache.entrySet()){
		  l = new LinkedList<String>();

		  for(String a : entry.getValue()){
              	    l.add(a);
              	  }
		  db.set("db."+entry.getKey(),l);
		  l = null;
		}
                try {
					db.save(new File(BeardAch.self.getDataFolder(),"YamlDB.yml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public void clearAchievements(String player) {
		// TODO Auto-generated method stub
		cache.put(player,new HashSet<String>());

	}

}
