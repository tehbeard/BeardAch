package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Configurable(tag="comm")
@Usage(arguments="command|command to execute on console, replaces <PLAYER> with playername",packageName="base",blurb="Executes a command on the console")
public class CommandReward implements IReward{

	String command = "";
	public void configure(Achievement Ach,String config) {
		command = config;
		
	}

	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
		BeardAch.printCon(command.replace("<PLAYER>", player.getName()));
	}

}
