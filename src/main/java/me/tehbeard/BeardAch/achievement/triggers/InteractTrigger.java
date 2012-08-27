package me.tehbeard.BeardAch.achievement.triggers;

import org.bukkit.entity.Player;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

//TODO Interact block trigger
@Configurable(tag="interact")
public class InteractTrigger implements ITrigger {

    public void configure(Achievement ach, String config) {
        
        
    }

    public boolean checkAchievement(Player player) {

        return false;
    }

}
