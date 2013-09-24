package me.tehbeard.BeardAch.achievement.triggers.player;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a player has a permission node
 * @author James
 *
 */
@Configurable(tag="perm",name="has permission node")
public class PermCheckTrigger implements ITrigger {

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
