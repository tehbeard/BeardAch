package me.tehbeard.BeardAch.achievement.triggers;



import java.util.List;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

/**
 * Checks if a player has a certain number of achievements
 * achcount|disc|20 would trigger when 20 achievements starting with the prefix disc are active
 * @author James
 *
 */
@Configurable(tag="achcount")
public class AchCountTrigger implements ITrigger {

	int threshold;
	String prefix;

	public void configure(String config) {
	    String[] con = config.split("\\:");
	    if(con.length==2){
	        prefix = con[0];
	        threshold = Integer.parseInt(con[1]);
	    }
	    
	}

	public boolean checkAchievement(Player player) {
		//if player has an acheivement
		List<AchievementPlayerLink> achs = BeardAch.self.getAchievementManager().getAchievements(player.getName());
		int count = 0;
		if(achs !=null){
			for(AchievementPlayerLink a: achs){
				if(a.getSlug().startsWith(prefix)){count+=1;}
			}
		}
		return (count > threshold);
	}

}
