package me.tehbeard.BeardAch.dataSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;

@DataSourceDescriptor(tag="file",version="1.4")
public class YamlDataSource implements IDataSource {

	YamlConfiguration db;
	File dbFile;
	File fancy;
	public YamlDataSource(){
	    Plugin plugin = BeardAch.self;
		dbFile = new File(plugin.getDataFolder(),"database.yml");
		fancy = new File(plugin.getDataFolder(),"ach.html");
		db = YamlConfiguration.loadConfiguration(dbFile);
	}

	public void dumpFancy() {

		try {
			FileWriter fwrite = new FileWriter(fancy);
			fwrite.write("<html>\n" +
					"<head>\n" +
					"<title>Achievements</title>\n" +
					"</head>\n" +
					"<body>\n");
			ConfigurationSection dbb = db.getConfigurationSection("database");
			if(dbb==null){}
			Set<String> players = dbb.getKeys(false);
			for(String player : players){
				Set<AchievementPlayerLink> achs = getPlayersAchievements(player);
				if(achs.size() > 0){
					fwrite.write("<h2>" +
							player + 
						    "</h2>\n");
					fwrite.write("<table>\n" +
							"<tr><th>Achievement</th><th>Date unlocked</th></tr\n");
					for(AchievementPlayerLink link : achs){
						fwrite.write("<tr>\n" +
								"<td>" + link.getAch().getName() + "</td>\n" +
								"<td>" + link.getDate() + "</td>\n" +
								"</tr>\n");	
					}
					fwrite.write("</table>\n");
				}
			}

			fwrite.write("</body></html>\n");
			fwrite.close();
		} catch (IOException e) {
			System.out.println("I/O error occured while trying to write out to file");
		}

	}

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

	public void setPlayersAchievements(String player, String achievement) {
		// TODO Auto-generated method stub
		db.set("database." + player + "."+achievement,System.currentTimeMillis());
	}

	public void flush() {

		try {
			db.save(dbFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void clearAchievements(String player) {
		// TODO Auto-generated method stub
		db.set("database."+player, null);
	}

}
