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
 * Checks if a player has a permission node
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Checks if the player has an achievement")
@Configurable(tag = "ach", name = "has achievement")
public class AchCheckTrigger implements ITrigger {

    @ComponentValueDescription(description = "The achievement id (slug) to check for")
    @Expose
    @EditorField(alias = "achievement slug")
    String ach;

    @Override
    public boolean checkAchievement(Player player) {
        // if player has an acheivement
        List<AchievementPlayerLink> achs = BeardAch.instance().getAchievementManager().getAchievements(player.getName());
        if (achs != null) {
            for (AchievementPlayerLink a : achs) {
                if (a.getSlug().equals(ach))
                    return true;
            }

        }
        return false;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
