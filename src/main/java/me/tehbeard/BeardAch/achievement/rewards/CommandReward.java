package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandReward extends Reward{

	String command = "";
	public static IReward newInstance(String config) {
		CommandReward n = new CommandReward();
		n.command = config;
		return (IReward) n;
	}

	@Override
	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
		BeardAch.printCon(command.replace("<PLAYER>", player.getName()));
	}

}
