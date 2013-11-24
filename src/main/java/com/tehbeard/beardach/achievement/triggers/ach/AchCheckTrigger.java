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
 * Checks if a player has a permission node
 * @author James
 *
 */
@ComponentHelpDescription(description = "Checks if the player has an achievement", name = "Achievement check", type = ComponentType.TRIGGER)
@Configurable(tag="ach",name="has achievement")
public class AchCheckTrigger implements ITrigger {

    @ComponentValueDescription(description="The achievement id (slug) to check for")
    @Expose
    @EditorField(alias="achievement slug")
	String ach;

	public void configure(Achievement Ach,String config) {
		ach = config;
	}

	public boolean checkAchievement(Player player) {
		//if player has an acheivement
		List<AchievementPlayerLink> achs = BeardAch.instance().getAchievementManager().getAchievements(player.getName());
		if(achs !=null){
			for(AchievementPlayerLink a: achs){
				if(a.getSlug().equals(ach)){return true;}
			}
			
		}
		return false;
	}

    public void configure(Achievement ach) {
        
    }

}
