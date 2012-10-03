package me.tehbeard.BeardAch.achievement.triggers;



import java.util.List;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

import org.bukkit.entity.Player;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
@Configurable(tag="ach")
@Usage(arguments = { "achievement|Slug for achievement" }, packageName = "base")
public class AchCheckTrigger implements ITrigger {

	String ach;

	public void configure(Achievement Ach,String config) {
		ach = config;
	}

	public boolean checkAchievement(Player player) {
		//if player has an acheivement
		List<AchievementPlayerLink> achs = BeardAch.self.getAchievementManager().getAchievements(player.getName());
		if(achs !=null){
			for(AchievementPlayerLink a: achs){
				if(a.getSlug().equals(ach)){return true;}
			}
			
		}
		return false;
	}

}
