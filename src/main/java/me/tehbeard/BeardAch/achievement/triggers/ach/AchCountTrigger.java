package me.tehbeard.BeardAch.achievement.triggers.ach;



import java.util.List;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a player has a certain number of achievements
 * achcount|disc|20 would trigger when 20 achievements starting with the prefix disc are active
 * achcount|*|10 would trigger if you have 10 achievements (any name)
 * @author James
 *
 */
@Configurable(tag="achcount",name="Achievement count")
public class AchCountTrigger implements ITrigger {

    @Expose
    @EditorField(alias="achievement count threshold")
	int threshold;
    @Expose
    @EditorField(alias="achievement slug prefix")
	String prefix;

	public void configure(Achievement Ach,String config) {
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
				if(a.getSlug().startsWith(prefix)||prefix.equals("*")){count+=1;}
			}
		}
		return (count > threshold);
	}

    public void configure(Achievement ach) {
        
    }

}
