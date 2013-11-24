package com.tehbeard.beardach.achievement.rewards.command;


import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;
import com.tehbeard.beardach.dataSource.json.help.ComponentValueDescription;

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
