package com.tehbeard.beardach.achievement.triggers;



import org.spongepowered.api.entity.player.Player;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;

/**
 * Always false
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Locks an achievement ensuring it cannot be triggered")
@Configurable(tag = "locked", name = "Lock this achievement")
public class LockedTrigger implements ITrigger {

    @Override
    public boolean checkAchievement(Player player) {

        return false;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
