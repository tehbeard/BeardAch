package me.tehbeard.BeardAch.achievement.triggers;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
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
@Usage(arguments={
        @Argument(name="permission",desc="permission node to check")
        },packageName="base",blurb="Fires if player has that permission")
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
