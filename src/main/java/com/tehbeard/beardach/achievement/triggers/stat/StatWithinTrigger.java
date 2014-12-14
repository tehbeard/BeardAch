package com.tehbeard.beardach.achievement.triggers.stat;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import com.tehbeard.beardstat.manager.EntityStatManager;

/**
 * Checks if a players stat is above certain threshold, then triggers.
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Triggers if statistic is between two values",dependencies="BeardStat",categories = "stat")
@Configurable(tag = "statwithin", name = "Stat within boundaries")
public class StatWithinTrigger implements ITrigger {

    @ComponentFieldDescription(value = "domain to check, for the regular stats use default as the value")
    @Expose
    @EditorField(alias = "Domain")
    String domain = "default";
    @ComponentFieldDescription(value = "World to check stats for (* matches all worlds)")
    @Expose
    @EditorField(alias = "World")
    String world = "*";
    @ComponentFieldDescription(value = "statistic category to check")
    @Expose
    @EditorField(alias = "Category")
    String cat;
    @ComponentFieldDescription(value = "Statistic name to check")
    @Expose
    @EditorField(alias = "Statistic")
    String stat;
    @ComponentFieldDescription(value = "Threshold statistic must equal or greater than")
    @Expose
    @EditorField(alias = "Lower bound threshold")
    int lowerThreshold;
    @ComponentFieldDescription(value = "Threshold statistic must equal or be less than")
    @Expose
    @EditorField(alias = "Upper bound threshold")
    int upperThreshold;

    private static EntityStatManager manager = null;

    @Override
    public boolean checkAchievement(Player player) {
        if (manager != null) {
            // if player has stat
            int value = manager.getPlayer(player.getUniqueId()).getValue().getStat(domain, world, cat, stat).getValue();
            return value >= lowerThreshold && value <= upperThreshold;
        }
        return false;
    }

    @Override
    public void configure(Achievement ach) {
        if (manager == null) {
            manager = BeardAch.getStats();
        }

    }
}
