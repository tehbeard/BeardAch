package me.tehbeard.BeardAch.achievement;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.utils.cuboid.CuboidEntry;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AchListener implements Listener{
    AchievementManager manager;
    
    public AchListener(AchievementManager manager){
        this.manager = manager;
    }
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event){
        if(event.getTo().getBlockX() != event.getFrom().getBlockX() ||
                event.getTo().getBlockY() != event.getFrom().getBlockY() || 
                event.getTo().getBlockZ() != event.getFrom().getBlockZ()
                ){
            BeardAch.printDebugCon("Player moved, checking chunk Cache");
            for( CuboidEntry<Achievement> e : manager.chunkCache.getEntries(event.getPlayer())){
                e.getEntry().checkAchievement(event.getPlayer());
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        // TODO Auto-generated method stub
        manager.loadPlayersAchievements(event.getPlayer().getName());

    }
}
