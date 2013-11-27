package com.tehbeard.beardach.achievement.triggers.stat;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.beardstat.dataproviders.IStatDataProvider;
import com.tehbeard.beardstat.manager.EntityStatManager;

/**
 * Checks if a players stat is between certain threshold, then triggers.
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Value of statistic is atleast provided value. This trigger supports regex values", dependencies = "BeardStat")
@Configurable(tag = "stat", name = "stat above threshold")
public class StatCheckTrigger implements ITrigger {

    @ComponentValueDescription(description = "domain to check, for the regular stats use default as the value")
    @Expose
    @EditorField(alias = "Domain")
    String domain = "default";
    @ComponentValueDescription(description = "World to check stats for (* matches all worlds)")
    @Expose
    @EditorField(alias = "World")
    String world = "*";
    @ComponentValueDescription(description = "statistic category to check")
    @Expose
    @EditorField(alias = "Category")
    String cat;
    @ComponentValueDescription(description = "Statistic name to check")
    @Expose
    @EditorField(alias = "Statistic")
    String stat;
    @ComponentValueDescription(description = "Threshold statistic must equal or beat")
    @Expose
    @EditorField(alias = "Lower threshold")
    int threshold;
    private static EntityStatManager manager = null;

    private static boolean warningLock = false;

    @Override
    public boolean checkAchievement(Player player) {
        // if player has stat
        if (manager != null)
            // if player exceeds threshold
            return manager.getBlobByNameType(player.getName(), IStatDataProvider.PLAYER_TYPE).getValue().getStats(domain, world, cat, stat).getValue() >= threshold;
        else {
            if (!warningLock) {
                BeardAch.instance().getLogger().warning("BeardStat was not loaded, stat check failed.");
                warningLock = true;
            }
        }
        return false;
    }

    @Override
    public void configure(Achievement ach) {
        if (manager == null) {
            manager = BeardAch.instance().getStats();
        }

    }

}
