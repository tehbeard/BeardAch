package me.tehbeard.BeardAch.achievement.triggers.player;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

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

    @ComponentValueDescription(description="Permission node to check, does not have to one declared by a plugin",examples={"meta.group.veteran","madeup.check.for.something"})
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
