package com.tehbeard.beardach.achievement.triggers.ach;



import java.util.List;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;
import com.tehbeard.beardach.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a player has a certain number of achievements
 * achcount|disc|20 would trigger when 20 achievements starting with the prefix disc are active
 * achcount|*|10 would trigger if you have 10 achievements (any name)
 * @author James
 *
 */
@ComponentHelpDescription(description = "Counts number of achievements with a specific id prefix", name = "Achievement count", type = ComponentType.TRIGGER)
@Configurable(tag="achcount",name="Achievement count")
public class AchCountTrigger implements ITrigger {

    @ComponentValueDescription(description="Amount of achievements player must exceed, (Triggers if count > this value)")
    @Expose
    @EditorField(alias="achievement count threshold")
	int threshold;
    @ComponentValueDescription(description="The prefix of the ids of achievements to count",examples={"discovery_","pvpArena"})
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
		//if player has an achievement
		List<AchievementPlayerLink> achs = BeardAch.instance().getAchievementManager().getAchievements(player.getName());
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
