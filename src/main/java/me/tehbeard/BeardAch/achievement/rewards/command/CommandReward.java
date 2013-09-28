package me.tehbeard.BeardAch.achievement.rewards.command;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description="Execute a command as the console",name="Console command",type=ComponentType.REWARD)
@Configurable(tag="comm",name="Execute console command")
public class CommandReward implements IReward{

    @ComponentValueDescription(description="Command to execute, <PLAYER> is replaced with the player's name")
    @Expose
    @EditorField(alias="Command")
	String command = "";
	public void configure(Achievement Ach,String config) {
		command = config;
		
	}

	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
	}

    public void configure(Achievement ach) {
        
    }

}
