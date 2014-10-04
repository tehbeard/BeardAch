package com.tehbeard.beardach;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.utils.bukkit.BukkitUtils;
import com.tehbeard.utils.cuboid.ChunkCache;
import com.tehbeard.utils.cuboid.Cuboid;
import com.tehbeard.utils.cuboid.CuboidEntry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class BeardAchListener implements Listener {

    private ChunkCache<Achievement> cache = new ChunkCache<Achievement>();

    public void add(Cuboid cuboid, Achievement ach) {
        cache.addEntry(cuboid, ach);
    }
    
    public void clear(){
        cache.clearCache();
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BeardAch.instance().getAchievementManager().loadPlayersAchievements(event.getPlayer().getUniqueId());
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockY() != event.getFrom().getBlockY() || event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
            for (CuboidEntry<Achievement> entry : cache.getEntries(BukkitUtils.fromLocation(event.getTo()),event.getTo().getWorld().getName())) {
                entry.getEntry().checkAchievement(event.getPlayer());
            }
        }
    }
}
