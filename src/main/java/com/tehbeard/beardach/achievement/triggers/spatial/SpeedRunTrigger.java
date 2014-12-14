package com.tehbeard.beardach.achievement.triggers.spatial;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import com.tehbeard.utils.bukkit.BukkitUtils;
import com.tehbeard.utils.cuboid.Cuboid;

//TODO: Redo as worldguard based
@ComponentDescription(description = "Triggers if player gets between two points within the given time",categories = "spatial")
@Configurable(tag = "speedrun", name = "Speed run")
public class SpeedRunTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Area player enters to start cuboid. NOTE: Entry into this area starts the timer, so for race areas with gates, place the cuboid immediately after the starting gate")
    @Expose
    @EditorField(alias = "start cuboid", type = EditorFieldType.cuboid)
    private Cuboid startCuboid = new Cuboid();

    @ComponentFieldDescription(value = "Area player must enter to finish the race")
    @Expose
    @EditorField(alias = "end cuboid", type = EditorFieldType.cuboid)
    private Cuboid endCuboid = new Cuboid();

    @ComponentFieldDescription(value = "Time player must beat in order to trigger this achievement")
    @Expose
    @EditorField(alias = "time to beat (seconds)")
    long timing = 0L;

    Map<String, Long> states = new HashMap<String, Long>();

    public Cuboid getStartCuboid() {
        return startCuboid;
    }

    public Cuboid getEndCuboid() {
        return endCuboid;
    }

    @Override
    public boolean checkAchievement(Player player) {

        // if inside start cuboid and state does not exist, create record
        if (startCuboid.isInside(BukkitUtils.fromLocation(player.getLocation()))) {
            startTimer(player.getName());
            return false;
        }
        // if inside end cuboid, and state exists, check and return value
        if (endCuboid.isInside(BukkitUtils.fromLocation(player.getLocation())) && inTime(player.getName())) {
            clearTimer(player.getName());
            return true;
        }
        return false;
    }

    /**
     * Start timer for a player
     * 
     * @param player
     */
    private void startTimer(String player) {
        states.put(player, System.currentTimeMillis());
    }

    /**
     * Clear timer for a player
     * 
     * @param player
     */
    private void clearTimer(String player) {
        states.remove(player);
    }

    /**
     * Has the players time expired?
     * 
     * @param player
     * @return
     */
    private boolean inTime(String player) {
        if (hasTime(player))
            return (System.currentTimeMillis() - states.get(player)) / 1000L <= timing;
        return false;

    }

    /**
     * Do they have a time in the system
     * 
     * @param player
     * @return
     */
    private boolean hasTime(String player) {
        return states.containsKey(player);
    }

    @Override
    public void configure(Achievement ach) {
        BeardAch.getListener().add(startCuboid, ach);
        BeardAch.getListener().add(endCuboid, ach);
    }

}
