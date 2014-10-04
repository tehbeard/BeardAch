package com.tehbeard.beardach.achievement;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.achievement.triggers.meta.MetaTrigger;
import com.tehbeard.beardach.achievement.triggers.spatial.CuboidCheckTrigger;
import com.tehbeard.beardach.achievement.triggers.spatial.SpeedRunTrigger;
import com.tehbeard.beardach.datasource.AchievementLoader;
import com.tehbeard.beardach.datasource.IDataSource;
import com.tehbeard.beardach.datasource.configurable.RunnableTime;
import com.tehbeard.utils.cuboid.ChunkCache;
import com.tehbeard.utils.cuboid.Cuboid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Manages the link between achievements and players
 *
 * @author James
 *
 */
public class AchievementManager {

    private HashMap<UUID, Set<AchievementPlayerLink>> playerHasCache = new HashMap<UUID, Set<AchievementPlayerLink>>();
    private HashMap<Achievement, Set<UUID>> playerCheckCache = new HashMap<Achievement, Set<UUID>>();
    private final IDataSource database;
    //private final ChunkCache<Achievement> chunkCache = new ChunkCache<Achievement>();

    private List<Achievement> achievements = new LinkedList<Achievement>();

    public AchievementManager(IDataSource database) {
        this.database = database;
    }

    public void loadAchievements() {
        // clear cache
        clearAchievements();
        // reset Achievement Id's
        Achievement.resetId();
        // load achievements
        AchievementLoader.loadAchievements();

        // load each players
        //TODO : Load all currently active players, (needed if reloaded while online)
//        for (Player p : Bukkit.getOnlinePlayers()) {
//            loadPlayersAchievements(p);
//        }
    }

    /**
     * Clear the caches.
     */
    public void clearAchievements() {
        database.flush();
        //chunkCache.clearCache();
        achievements = new LinkedList<Achievement>();
        playerCheckCache = new HashMap<Achievement, Set<UUID>>();
        playerHasCache = new HashMap<UUID, Set<AchievementPlayerLink>>();
    }

    /**
     * Return the list of visible achievements
     *
     * @return
     */
    public List<Achievement> getAchievementsList() {
        List<Achievement> ret = new ArrayList<Achievement>();
        for (Achievement a : achievements) {
            if (!a.isHidden()) {
                ret.add(a);
            }
        }
        return ret;
    }

    /**
     * Get a list of all achievements
     *
     * @return
     */
    public List<Achievement> getLoadedAchievements() {
        List<Achievement> ret = new ArrayList<Achievement>();
        for (Achievement a : achievements) {
            ret.add(a);
        }
        return ret;
    }

    public Achievement getAchievementSlug(String slug) {
        for (Achievement a : achievements) {
            if (a.getSlug().equals(slug)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Add achievement to the manager
     *
     * @param ach
     */
    public void addAchievement(Achievement ach) {
        achievements.add(ach);

    }

    /**
     * Load the achievements for a player
     *
     * @param player
     */
    public void loadPlayersAchievements(UUID player) {
        Set<AchievementPlayerLink> got = database.getPlayersAchievements(player);
        // put to cache
        if (got != null) {
            playerHasCache.put(player, got);
            // cycle all loaded achievements
            buildPlayerCheckCache(player);
        } else {
            BeardAch.instance().getLogger().log(Level.SEVERE, "Failed to load for {0}", player);
        }

    }

    private void buildPlayerCheckCache(UUID player) {

        for (Achievement ach : achievements) {

            // if they don't have that achievement, add them to the check cache
            HashSet<String> slugs = new HashSet<String>();
            for (AchievementPlayerLink link : playerHasCache.get(player)) {
                slugs.add(link.getSlug());
            }
            if (!slugs.contains(ach.getSlug())) {
                // push key if it doesn't exist
                if (!playerCheckCache.containsKey(ach)) {
                    playerCheckCache.put(ach, new HashSet<UUID>());
                }
                // add player to cache
                playerCheckCache.get(ach).add(player);
            }
        }
    }

    public boolean playerHas(UUID player, String slug) {
        for (AchievementPlayerLink link : playerHasCache.get(player)) {
            if (link.getSlug().equals(slug)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check all players online
     */
    public void checkPlayers() {

        // wipe players not online
        // for each achievement
        boolean keepChecking = true;
        while (keepChecking) {
            BeardAch.instance().getLogger().finer("Beggining Check player loop");
            keepChecking = false;

            for (Achievement ach : playerCheckCache.keySet()) {

                BeardAch.instance().getLogger().log(Level.FINER, "ach:{0}", ach.getName());
                // loop all players, check them.
                keepChecking = checkAchievement(ach);

            }
            BeardAch.instance().getLogger().finer("Ending Check player loop");
        }

    }

    public List<AchievementPlayerLink> getAchievements(UUID player) {
        if (playerHasCache.containsKey(player)) {
            List<AchievementPlayerLink> l = new LinkedList<AchievementPlayerLink>();

            for (AchievementPlayerLink link : playerHasCache.get(player)) {
                Achievement a = getAchievementSlug(link.getSlug());
                if (a != null) {
                    l.add(link);
                }
            }
            Collections.sort(l, new Comparator<AchievementPlayerLink>() {

                @Override
                public int compare(AchievementPlayerLink o1, AchievementPlayerLink o2) {
                    long res = o1.getDate().getTime() - o2.getDate().getTime();
                    if (res == 0) {
                        return 0;
                    }
                    if (res > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            return l;
        }
        return null;

    }

    public List<Achievement> getAchievementBySlug(String slug) {
        List<Achievement> l = new ArrayList<Achievement>();
        for (Achievement a : achievements) {
            if (a.getSlug().equals(slug)) {
                l.add(a);
            }
        }
        return l;
    }

    /**
     * Mark an achievement slug as completed by a player
     * @param player
     * @param slug 
     */
    public void makeAchievementLink(UUID player, String slug) {
        // push to cache
        playerHasCache.get(player).add(new AchievementPlayerLink(slug));
        // push to DB
        database.setPlayersAchievements(player, slug);
    }

    public boolean checkAchievement(Achievement ach) {

        Iterator<UUID> it = getListOfPlayersToCheck(ach).iterator();
        boolean keepChecking = false;
        //TODO : Get players, check achievement, return if it succeeded
//        Player p;
//        while (it.hasNext()) {
//            UUID ply = it.next();
//
//            p = Bukkit.getPlayer(ply);
//            if (p instanceof Player) {
//                BeardAch.instance().getLogger().log(Level.FINE, "Player {0} online", ply);
//                // check for bleep bloop
//                if(ach.checkAchievement(p)){
//                    keepChecking = true;
//                }
//
//            } else {
//                it.remove();
//            }
//
//        }
        return keepChecking;
    }

    public void removeCheck(Achievement ach, UUID player) {
        playerCheckCache.get(ach).remove(player);
    }

    private Set<UUID> getListOfPlayersToCheck(Achievement ach) {
        return playerCheckCache.get(ach);

    }

    public void flush() {
        database.flush();

    }

    public void dumpFancy() {
        database.dumpFancy();

    }
}
