package com.tehbeard.beardach.achievement.triggers;


import java.util.logging.Level;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;

import org.bukkit.entity.Player;

/**
 * Always false
 * @author James
 *
 */
@ComponentHelpDescription(description = "Locks an achievement ensuring it cannot be triggered", name = "Lock Achievement", type = ComponentType.TRIGGER)
@Configurable(tag="locked",name="Lock this achievement")
public class LockedTrigger implements ITrigger {


	public void configure(Achievement ach,String config) {
	    BeardAch.instance().getLogger().log(Level.INFO, "[ALERT] Achievement {0} is locked and will not trigger", ach.getName());
	}


	public boolean checkAchievement(Player player) {

		return false;
	}


    public void configure(Achievement ach) {
       
    }

}
