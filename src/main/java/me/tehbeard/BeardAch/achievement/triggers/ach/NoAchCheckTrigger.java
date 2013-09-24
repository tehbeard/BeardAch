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
 * Checks if a player does not have an achievement
 * @author James
 *
 */
@Configurable(tag="noach",name="Does not have achievement")
public class NoAchCheckTrigger implements ITrigger {

    @Expose
    @EditorField(alias="achievement slug")
	String ach;

	public void configure(Achievement ach,String config) {
		this.ach = config;
	}

	public boolean checkAchievement(Player player) {
		//if player has an acheivement, return false
		List<AchievementPlayerLink> achs = BeardAch.self.getAchievementManager().getAchievements(player.getName());
		boolean result = true;
		if(achs !=null){
			
			for(AchievementPlayerLink a: achs){
				if(a.getSlug().equals(ach)){result=false;}
			}
			
		}
		return result;
	}

    public void configure(Achievement ach) {
        
    }

}
