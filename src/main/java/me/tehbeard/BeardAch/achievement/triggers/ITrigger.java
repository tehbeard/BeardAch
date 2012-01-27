package me.tehbeard.BeardAch.achievement.triggers;

import me.tehbeard.BeardAch.achievement.IConfigurable;

import org.bukkit.entity.Player;

public interface ITrigger extends IConfigurable{

	public boolean checkAchievement(Player player);
}
