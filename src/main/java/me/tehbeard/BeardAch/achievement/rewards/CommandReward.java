package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Configurable(tag="comm")
@Usage(arguments=@Argument(name="command",desc="The command to execute, executed on the console. <PLAYER> is replaced it the players name"),
packageName="base",
blurb="Executes a command on the console")
public class CommandReward implements IReward{

	String command = "";
	public void configure(Achievement Ach,String config) {
		command = config;
		
	}

	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
	}

}
