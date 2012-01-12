package me.tehbeard.BeardAch.dataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

public class YamlDataSource extends AbstractDataSource {

	YamlConfiguration db;
	File dbFile;
	public YamlDataSource(Plugin plugin){
		dbFile = new File(plugin.getDataFolder(),"database.yml");
		db = YamlConfiguration.loadConfiguration(dbFile);
	}
	
	public void dumpFancy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashSet<AchievementPlayerLink> getPlayersAchievements(String player) {
		HashSet<AchievementPlayerLink> ret = new HashSet<AchievementPlayerLink>();
		ConfigurationSection playerData = db.getConfigurationSection("database."+player);
		if(playerData != null){
			Set<String> keys = playerData.getKeys(false);
			if(keys != null){
				for(String key : keys){
					long unlock = playerData.getLong(key);
					ret.add(new AchievementPlayerLink(key,new Timestamp(unlock)));
				}
			}
		}
		return ret;
	}

	@Override
	public void setPlayersAchievements(String player, String achievement) {
		// TODO Auto-generated method stub
		db.set("database." + player + "."+achievement,System.currentTimeMillis());
	}

	@Override
	public void flush() {
		
		try {
			db.save(dbFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void clearAchievements(String player) {
		// TODO Auto-generated method stub
		db.set("database."+player, null);
	}

}
