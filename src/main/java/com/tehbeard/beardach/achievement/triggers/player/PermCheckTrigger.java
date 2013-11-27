package com.tehbeard.beardach.achievement.triggers.player;


import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
@ComponentHelpDescription(description = "Does the player have a permission node", name = "Permission node check", type = ComponentType.TRIGGER)
@Configurable(tag="perm",name="has permission node")
public class PermCheckTrigger implements ITrigger {

    @ComponentValueDescription(description="Permission node to check, does not have to one declared by a plugin")
    @Expose
    @EditorField(alias="permission node")
	String perm;

	public void configure(Achievement ach,String config) {
		perm = config;
	}

	
	public boolean checkAchievement(Player player) {
		//if player has stat
		return player.hasPermission(perm);
	}


    public void configure(Achievement ach) {
        
    }

}
