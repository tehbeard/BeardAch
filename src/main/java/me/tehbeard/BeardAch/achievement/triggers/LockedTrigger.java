package me.tehbeard.BeardAch.achievement.triggers;


import java.util.logging.Level;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

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
