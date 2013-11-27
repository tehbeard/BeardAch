package com.tehbeard.beardach.achievement.triggers.ach;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * Checks if a player does not have an achievement
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Triggers only if the player does not have this achievement")
@Configurable(tag = "noach", name = "Does not have achievement")
public class NoAchCheckTrigger implements ITrigger {

    @ComponentValueDescription(description = "achievement slug to check for")
    @Expose
    @EditorField(alias = "achievement slug")
    String ach;

    @Override
    public void configure(Achievement ach, String config) {
        this.ach = config;
    }

    @Override
    public boolean checkAchievement(Player player) {
        // if player has an acheivement, return false
        List<AchievementPlayerLink> achs = BeardAch.instance().getAchievementManager().getAchievements(player.getName());
        boolean result = true;
        if (achs != null) {

            for (AchievementPlayerLink a : achs) {
                if (a.getSlug().equals(ach)) {
                    result = false;
                }
            }

        }
        return result;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
