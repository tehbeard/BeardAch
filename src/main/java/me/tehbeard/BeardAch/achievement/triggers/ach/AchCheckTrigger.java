package me.tehbeard.BeardAch.achievement.triggers.ach;



import java.util.List;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

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

    @Expose
    @EditorField(alias="achievement slug")
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

    public void configure(Achievement ach) {
        
    }

}
