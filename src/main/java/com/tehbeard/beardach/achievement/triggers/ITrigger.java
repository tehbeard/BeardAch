package com.tehbeard.beardach.achievement.triggers;

import com.tehbeard.beardach.dataSource.configurable.IConfigurable;

import org.bukkit.entity.Player;

public interface ITrigger extends IConfigurable{

	public boolean checkAchievement(Player player);
}
