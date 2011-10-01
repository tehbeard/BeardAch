package me.tehbeard.BeardAch.dataSource;


import org.bukkit.util.config.ConfigurationNode;

public interface IStashDataSource {
	public ConfigurationNode getPlayerStash(String Player);

	public void setPlayerStash(String player,ConfigurationNode stash);
}
