package me.tehbeard.BeardAch.achievement.triggers;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Usage;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Always false
 * @author James
 *
 */
@Configurable(tag="locked",name="Lock this achievement")
@Usage(arguments={},packageName="base",blurb="Prevents an achievement from firing")
public class LockedTrigger implements ITrigger {


	public void configure(Achievement ach,String config) {
	    BeardAch.printCon("[ALERT] Achievement " + ach.getName() + " is locked and will not trigger");
	}


	public boolean checkAchievement(Player player) {

		return false;
	}


    public void configure(Achievement ach) {
       
    }

}
