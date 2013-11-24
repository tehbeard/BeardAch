package com.tehbeard.beardach.achievement.triggers.env;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.editor.EditorFieldType;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;
import com.tehbeard.beardach.dataSource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Triggers if world a player in is stormy", name = "Storm check", type = ComponentType.TRIGGER)
@Configurable(tag="storm",name="Is it stormy?")
public class StormTrigger implements ITrigger {

    @ComponentValueDescription(description = "Set true for should have a storm, false for should be clear")
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
