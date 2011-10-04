package me.tehbeard.BeardAch.achievement;
import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.IDataSource;
import me.tehbeard.BeardAch.dataSource.NullDataSource;
/**
 * Manages the link between achievements and players
 * @author James
 *
 */
public class AchievementManager {

	private static HashMap<String,HashSet<String>> playerHasCache = new  HashMap<String,HashSet<String>>();
	private static HashMap<Achievement,HashSet<String>> playerCheckCache = new  HashMap<Achievement,HashSet<String>>();


	private static HashMap<String,Achievement> achievements = new HashMap<String,Achievement>();
	private static IDataSource database = new NullDataSource();
	
	
	/**
	 * Add achievement to the manager
	 * @param ach
	 */
	public static void addAchievement(Achievement ach){
		achievements.put(ach.getName(),ach);
	}

	/**
	 * Load the achievements for a player
	 * @param player
	 */
	public static void loadAchievements(String player){
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



	/**
	 * Check all players online
	 */
	public static void checkPlayers(){

		//wipe players not online
		//for each achievement
		for( Entry<Achievement, HashSet<String>> entry : playerCheckCache.entrySet()){
			BeardAch.printDebugCon("ach:"+entry.getKey().getName());
			//loop all players, check them.
			Iterator<String> it = entry.getValue().iterator();
			String ply;
			Player p;
			while(it.hasNext()){
				ply = it.next();

				//get player object for offline detection
				p =Bukkit.getPlayer(ply);
				if(p instanceof Player){
					BeardAch.printDebugCon("Player "+ply+" online");
					//check for bleep bloop
					if(entry.getKey().checkAchievement(p)){
						BeardAch.printDebugCon("Achievement Get! " + ply + "=>" + entry.getKey().getName());
						playerHasCache.get(ply).add(entry.getKey().getName());
						it.remove();
					}

				}

			}


		}
	}



	/**
	 * Unload players who are not on
	 */
	public static void unloadOfflinePlayers(){
		for(Entry<String, HashSet<String>> e :playerHasCache.entrySet()){
			if(Bukkit.getPlayer(e.getKey()) == null){
				database.setPlayersAchievements(e.getKey(),e.getValue());
			}
		}
	}

}