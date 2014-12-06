package com.tehbeard.beardach;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.utils.cuboid.ChunkCache;
import com.tehbeard.utils.cuboid.Cuboid;
import com.tehbeard.utils.cuboid.CuboidEntry;
import com.tehbeard.utils.sponge.SpongeUtils;
import org.spongepowered.api.event.player.PlayerJoinEvent;
import org.spongepowered.api.event.player.PlayerMoveEvent;
import org.spongepowered.api.util.event.Subscribe;


public class BeardAchListener {

    private ChunkCache<Achievement> cache = new ChunkCache<Achievement>();

    BeardAchListener() {
    }

    public void add(Cuboid cuboid, Achievement ach) {
        cache.addEntry(cuboid, ach);
    }
    
    public void clear(){
        cache.clearCache();
    }
    
    @Subscribe
    public void onPlayerJoin(PlayerJoinEvent event) {
        BeardAch.getAchievementManager().loadPlayersAchievements(event.getPlayer().getUniqueId());
    }
    @Subscribe(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if (event.getNewLocation().getBlock().getX() != event.getOldLocation().getBlock().getX() || event.getNewLocation().getBlock().getY() != event.getOldLocation().getBlock().getY() || event.getNewLocation().getBlock().getZ() != event.getOldLocation().getBlock().getZ()) {
            for (CuboidEntry<Achievement> entry : cache.getEntries(SpongeUtils.vectorToVec3(event.getNewLocation().getPosition()),event.getNewLocation().getExtent().toString())) {
                entry.getEntry().checkAchievement(event.getPlayer());
            }
        }
    }
}
