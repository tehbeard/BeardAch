package me.tehbeard.BeardAch.achievement.triggers.env;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

@Configurable(tag="storm",name="Is it stormy?")
public class StormTrigger implements ITrigger {

    @Expose
    @EditorField(alias="Storm currently?",type=EditorFieldType.bool)
    private boolean isStormy;
    
    public void configure(Achievement arg0, String config) {
        if(config.equalsIgnoreCase("true")){
            isStormy = true;
        }
        
        if(config.equalsIgnoreCase("false")){
            isStormy = false;
        }
        throw new IllegalArgumentException("invalid value for storm");
        
    }

    public boolean checkAchievement(Player p) {
       
        return p.getWorld().hasStorm() == isStormy;
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}

}
