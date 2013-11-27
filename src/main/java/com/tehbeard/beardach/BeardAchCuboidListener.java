package com.tehbeard.beardach;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.utils.cuboid.ChunkCache;
import com.tehbeard.utils.cuboid.Cuboid;
import com.tehbeard.utils.cuboid.CuboidEntry;

public class BeardAchCuboidListener implements Listener {

    private ChunkCache<Achievement> cache = new ChunkCache<Achievement>();

    public void add(Cuboid cuboid, Achievement ach) {
        cache.addEntry(cuboid, ach);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockY() != event.getFrom().getBlockY() || event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
            for (CuboidEntry<Achievement> entry : cache.getEntries(event.getTo())) {
                entry.getEntry().checkAchievement(event.getPlayer());
            }
        }
    }
}
