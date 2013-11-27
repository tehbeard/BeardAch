package com.tehbeard.beardach.achievement.triggers;

import java.util.logging.Level;

import org.bukkit.entity.Player;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

/**
 * Always false
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Locks an achievement ensuring it cannot be triggered")
@Configurable(tag = "locked", name = "Lock this achievement")
public class LockedTrigger implements ITrigger {

    @Override
    public void configure(Achievement ach, String config) {
        BeardAch.instance().getLogger().log(Level.INFO, "[ALERT] Achievement {0} is locked and will not trigger", ach.getName());
    }

    @Override
    public boolean checkAchievement(Player player) {

        return false;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
