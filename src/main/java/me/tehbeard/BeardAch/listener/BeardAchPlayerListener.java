package me.tehbeard.BeardAch.listener;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.ChunkCache;
import me.tehbeard.BeardAch.achievement.AchievementManager;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.event.player.PlayerMoveEvent;

public class BeardAchPlayerListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent event){
		if(event.getTo().getBlockX() != event.getFrom().getBlockX() ||
				event.getTo().getBlockY() != event.getFrom().getBlockY() ||	
				event.getTo().getBlockZ() != event.getFrom().getBlockZ()
				){
			BeardAch.printDebugCon("Player moved, checking chunk Cache");
			ChunkCache.checkLocation(event.getPlayer());
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		// TODO Auto-generated method stub
		AchievementManager.loadPlayersAchievements(event.getPlayer().getName());
		
	}
	
	

	
	
}
