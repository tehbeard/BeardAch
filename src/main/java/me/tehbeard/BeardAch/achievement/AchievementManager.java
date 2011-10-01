package me.tehbeard.BeardAch.achievement;
import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Bukkit;

import me.tehbeard.BeardAch.dataSource.IDataSource;
/**
 * Manages the link between achievements and players
 * @author James
 *
 */
public class AchievementManager {

	private static HashMap<String,HashSet<String>> playerHasCache = new  HashMap<String,HashSet<String>>();
	private static HashMap<Achievement,HashSet<String>> playerCheckCache = new  HashMap<Achievement,HashSet<String>>();


	private static HashMap<String,Achievement> achievements = new HashMap<String,Achievement>();
	private static IDataSource database = null;
	/**
	 * Add achievement to the manager
	 * @param ach
	 */
	static void addAchievement(Achievement ach){
		achievements.put(ach.getName(),ach);
	}

	/**
	 * Load the achievements for a player
	 * @param player
	 */
	static void loadAchievements(String player){
		HashSet<String> got = database.getPlayersAchievements(player);
		//put to cache
		playerHasCache.put(player,got);
		//cycle all loaded achievements
		for(Achievement ach :achievements.values()){

			//if they don't have that achievement, add them to the check cache
			if(!got.contains(ach.getName())){
				//push key if it doesn't exist
				if(!playerCheckCache.containsKey(ach)){
					playerCheckCache.put(ach, new HashSet<String>());
				}
				//add player to cache
				playerCheckCache.get(ach).add(player);


			}
		}
	}

	static void checkPlayers(){
		for( Entry<Achievement, HashSet<String>> entry : playerCheckCache.entrySet()){
			Iterator<String> it = entry.getValue().iterator();
			String ply;
			while(it.hasNext()){
				ply = it.next();
				if(entry.getKey().checkAchievement(Bukkit.getPlayer(ply))){
					it.remove();
				}
			}


		}
	}

}
