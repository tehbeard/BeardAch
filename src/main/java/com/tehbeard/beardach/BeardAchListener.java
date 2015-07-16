package com.tehbeard.beardach;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.utils.cuboid.ChunkCache;
import com.tehbeard.utils.cuboid.Cuboid;
import com.tehbeard.utils.cuboid.CuboidEntry;
import com.tehbeard.utils.sponge.SpongeUtils;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.event.entity.player.PlayerMoveEvent;

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
        BeardAch.getAchievementManager().loadPlayersAchievements(event.getEntity().getUniqueId());
    }
    @Subscribe(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if (event.getNewLocation().getBlockPosition().getX() != event.getOldLocation().getBlockPosition().getX() || event.getNewLocation().getBlockPosition().getY() != event.getOldLocation().getBlockPosition().getY() || event.getNewLocation().getBlockPosition().getZ() != event.getOldLocation().getBlockPosition().getZ()) {
            for (CuboidEntry<Achievement> entry : cache.getEntries(SpongeUtils.vectorToVec3(event.getNewLocation().getPosition()),event.getNewLocation().getExtent().toString())) {
                entry.getEntry().checkAchievement(event.getEntity());
            }
        }
    }
}
